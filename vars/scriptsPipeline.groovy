// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def cleanLib = new com.functions.CleanLib()

	def environmentName = "santacruz-fe"
	def branchName = "develop"

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('Script Shell') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						def scriptbash = libraryResource 'com/scripts/segredos.sh'
						writeFile file: './segredos.sh', text: scriptbash
						sh 'bash ./segredos.sh Erik 21 Masc'

						sh cleanLib.cleanFiles(File: "segredos.sh")
					}
				}
			}

			stage('Script Python') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT PYTHON "
						echo " --------------------------------------------------------------------------------------- "

						def scriptpython = libraryResource 'com/scripts/teste.py'
						writeFile file: './teste.py', text: scriptpython
						sh 'python3 ./teste.py Erik'
						
						sh cleanLib.cleanFiles(File: "teste.py")
					}
				}
			}

			stage('Script Json') {
                steps {
                        
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT JSON "
						echo " --------------------------------------------------------------------------------------- "

						def scriptjson = libraryResource 'com/json/request.json'

						def jsonData = readJSON text: scriptjson
						echo "JSON: ${jsonData}"

						jsonData.pessoas.each { person ->
                        	echo "Nome: ${person.nome}"
                        	echo "Idade: ${person.idade}"
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
						//def projectEnvironments = json.santacruz."santacruz-fe"
						// "JSON: ${projectEnvironments}"

						//projects.each { projectKey, projectEnvironments ->
						//	echo "Project: ${projectKey}"
							
							// projectEnvironments.each { environment ->
							// 	environment.each { envKey, fileId ->
							// 		echo "Branch: ${envKey} - ID: ${fileId}"
							// 		// Realize as ações desejadas com os valores do JSON aqui
							// 	}
							// }
						
						//projectEnvironments.each { environment ->
						//	environment.each { envKey, fileId ->
						//		echo "Branch: ${envKey} - ID: ${fileId}"
								// Realize as ações desejadas com os valores do JSON aqui
						//	}
						//}
						//def santacruzFeDevelop = json.santacruz."santacruz-fe".find { environment -> environment.containsKey("develop") }
						//def fileId = santacruzFeDevelop?.develop

						// Usa o método findResult para buscar o valor do ambiente "develop" no projeto "santacruz-fe"
						def fileId = json.santacruz.environmentName.findResult { environment -> 
							// A expressão condicional verifica se o ambiente possui a chave "develop"
							// Se sim, retorna o valor do ambiente "develop", senão retorna null
							environment.containsKey(branchName)
                   		}
						
						if (fileId) {
							echo "File ID for develop in santacruz-fe: ${fileId}"
							configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
						}
					}
				}
			}
		}
	}
}
