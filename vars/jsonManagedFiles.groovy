// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

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
						echo " ONDE JSON PROJECT: [santacruz-fe]"
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def santacruzFeEnvs = json.santacruz."santacruz-fe"
						
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
						echo " READ JSON PROJECT DEVELOP "
						echo " --------------------------------------------------------------------------------------- "

						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def santacruzFeDevelop = json.santacruz."santacruz-fe".find { environment -> environment.containsKey("develop") }
						def fileId = santacruzFeDevelop?.develop
						
						if (fileId) {
							echo "ID branch develop do projeto santacruz-fe: ${fileId}"
						} else {
							echo "Não foi encontrando o Id da branch develop no projeto santacruz-fe."
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
						def fileId = json.santacruz."santacruz-fe".findResult { environment -> 
							// A expressão condicional verifica se o ambiente possui a chave "develop"
							// Se sim, retorna o valor do ambiente "develop", senão retorna null
							environment.containsKey("develop") ? environment.develop : null
                   		}
						
						if (fileId) {
							echo "ID branch develop do projeto santacruz-fe: ${fileId}"
							configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
						} else {
							echo "Não foi encontrando o Id da branch develop no projeto santacruz-fe."
						}
					}
				}
			}
		}
	}
}


