// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def branchName = "homolog"

    pipeline {
        agent { 
            label 'ubuntu'
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
						echo " READ JSON PROJECT: ${branchName} "
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def fileId = json.santacruz."${pipelineParams.projectName}".find { environment -> environment[branchName] }
						
						if (fileId) {
							echo "ID branch ${branchName} do projeto ${pipelineParams.projectName}: ${santacruzFeDevelop}"
						} else {
							echo "Não foi encontrando o Id da branch ${branchName} no projeto ${pipelineParams.projectName}."
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
						// Usa o método findResult para buscar o valor do ambiente "develop" no projeto "santacruz-fe"
						def fileId = json.santacruz."${pipelineParams.projectName}".findResult { environment ->
                        // A expressão condicional verifica se o ambiente possui a chave "develop"
                        // Se sim, retorna o valor do ambiente "develop", senão retorna null
                        environment[branchName] }
						
						// Verifica se o fileId foi encontrado
						if (fileId) {
							echo "ID branch ${branchName} do projeto ${pipelineParams.projectName}: ${fileId}"
							configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
						} else {
							echo "Não foi encontrando o Id da branch ${branchName} no projeto ${pipelineParams.projectName}."
						}
					}
				}
			}
		}
	}
}

def copyFiles(Map params, Map configFileMap){
	def envjson = libraryResource 'com/json/projectsFilesList.json'
	def json = readJSON text: envjson

	def keyToFind = 'homolog' // Troque para 'develop', 'qa', etc. conforme necessário
	def fileId = json.santacruz."santacruz-fe".findResult { environment ->
		environment[keyToFind]
	}
} 
