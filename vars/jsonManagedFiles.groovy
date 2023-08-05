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
						def fileId = json.santacruz."santacruz-fe".findResult { environment -> 
							// A expressão condicional verifica se o ambiente possui a chave "develop"
							// Se sim, retorna o valor do ambiente "develop", senão retorna null
							environment.containsKey("develop") ? environment.develop : null
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


