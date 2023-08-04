// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def cleanLib = new com.functions.CleanLib()
	def projectBaseName = "cybersec-storybook"

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

						//assert jsonData['nome'] == 'Erik'
						//echo "Nome: ${jsonData.nome}"
						//echo "Idade: ${jsonData.idade}"

						jsonData.pessoas.each { person ->
                        	echo "Nome: ${person.nome}"
                        	echo "Idade: ${person.idade}"
						}
					}
					//discordSend description: "Jenkins Pipeline Build", footer: "Footer Text", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: "https://discord.com/api/webhooks/1137143123005419753/Zn7xM6QAv2aS3pk7pUK1QMCmHTLDRCIInUjnniZameDDa4SuuMy49zrfMqr8Lua-o8yK"
				}
			}

			stage('Use JSON Values') {
				steps {
					script {
						def envjson = libraryResource 'com/json/projectsFilesList.json'
						def json = readJSON text: envjson
						def projects = json.santacruz
						echo "JSON: ${projects}"

						projects.each { projectKey, project ->
							echo "Project: ${projectKey}"
							
							project.develop.each { entry ->
								echo "  Develop - File ID: ${entry.fileId}"
\							}
							
							project.qa.each { entry ->
								echo "  QA - File ID: ${entry.fileId}"
							}
							
							project.homolog.each { entry ->
								echo "  Homolog - File ID: ${entry.fileId}"
							}
						}
					}
				}
			}
		}
	}
}


