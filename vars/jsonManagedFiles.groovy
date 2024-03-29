// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

    pipeline {
        agent { 
            label 'ubuntu'
        }

		environment {
			BRANCH_NAME = 'qa'
		}

		stages {

			stage('All JSON') {
				steps {
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " ALL JSON "
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def projects = json.santacruz
						projects.each { project, projectIdEnvs ->
							echo "Project: ${project}"
							
							projectIdEnvs.each { environment ->
								environment.each { envKey, fileId ->
									echo " Branch: ${envKey} - ID: ${fileId}"
								}
							}
						}
					}
				}
			}

			stage('One JSON Project') {
				steps {
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " ONDE JSON PROJECT: [${pipelineParams.projectName}]"
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def santacruzFeEnvs = json.santacruz."${pipelineParams.projectName}"
						
						santacruzFeEnvs.each { environment ->
							environment.each { envKey, fileId ->
								echo " Branch: ${envKey} - ID: ${fileId}"
							}
						}
					}
				}
			}

			stage('Read JSON Projects') {
				steps {
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " READ JSON PROJECT: $BRANCH_NAME"
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def fileId = json.santacruz."${pipelineParams.projectName}".find { environment -> environment[BRANCH_NAME] }
						
						if (fileId) {
							echo "ID da branch $BRANCH_NAME do projeto ${pipelineParams.projectName}: ${fileId}"
						} else {
							echo "Não foi encontrando o Id da branch $BRANCH_NAME no projeto ${pipelineParams.projectName}."
						}
					}
				}
			}
			
			stage('JSON Managed Files') {
				steps {
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " JSON MANAGED FILES "
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def fileId = json.santacruz."${pipelineParams.projectName}".findResult { environment -> environment[BRANCH_NAME] }
						
						if (fileId) {
							echo "ID da branch $BRANCH_NAME do projeto ${pipelineParams.projectName}: ${fileId}"
							configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
						} else {
							echo "Não foi encontrando o Id da branch $BRANCH_NAME no projeto ${pipelineParams.projectName}."
						}
					}
				}
			}

			stage('Copy Files') {
				steps {
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " COPY FILES - JSON MANAGED FILES "
						echo " --------------------------------------------------------------------------------------- "

						copyFiles(ProjectName: pipelineParams.projectName, BranchName: BRANCH_NAME)
					}
				}
			}
		}
	}
}

def copyFiles(Map params) {
	def envjson = libraryResource 'com/json/projectsFilesList.json'
	def json = readJSON text: envjson

	def fileId = json.santacruz."${params.ProjectName}".findResult { environment -> environment["${params.BranchName}"] }

	if (fileId) {
		echo "ID branch ${params.BranchName} do projeto ${params.ProjectName}: ${fileId}"
		configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
	} else {
		echo "Não foi encontrando o Id da branch ${params.BranchName} no projeto ${params.ProjectName}."
	}
} 
