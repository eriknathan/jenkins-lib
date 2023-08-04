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

						def scriptjson = libraryResource 'com/scripts/request.json'

						def jsonData = readJSON text: scriptjson
						echo "JSON: ${jsonData}"

						//assert jsonData['name'] == 'Nathan'
						//echo "Nome: ${jsonData.name}"
						//echo "Idade: ${jsonData.age}"

						jsonData.people.each { person ->
                        	echo "Name: ${person.name}"
                        	echo "Age: ${person.age}"
						}
					}
				}
			}
		}
	}
}