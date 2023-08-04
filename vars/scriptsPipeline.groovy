// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def cleanLib = new com.functions.CleanLib()

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
						sh 'cat "'+scriptjson+'"'
						
						sh cleanLib.cleanFiles(File: "request.json")
					}
				}
			}
		}
		post {
			always {
				echo 'One way or another, I have finished'
				deleteDir() /* clean up our workspace */
			}
			success {
				echo 'I succeeded!'
			}
			unstable {
				echo 'I am unstable :/'
			}
			failure {
				echo 'I failed :('
			}
			changed {
				echo 'Things were different before...'
			}
		}
	}
}