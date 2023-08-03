// vars/exemploPipeline.groovy

def call (Map pipelineParams) {
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('Teste SH') {
                steps {
                    script {
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
					}
					// def script_bash = libraryResource 'resource/scripts/segredos.sh'
					// writeFile file: './segredos.sh', text: script_bash
					// sh 'bash ./segredos.sh'

					def my_function(string serverip, string scriptargument) {
						def script_content = libraryresource 'my_scripts/test.sh'
						// create a file with script_bash content
						writefile file: './test.sh', text: script_content
						echo "execute remote script test.sh..."
						def sshcommand = "ssh username@${serverip} \'bash -xs\' < ./test.sh ${scriptargument}"
						echo "ssh command is: ${sshcommand}"
						sh(sshcommand)
					}

					my_script.my_function("192.168.1.1", "scriptargumentvalue")
				}
			}
		}
	}
}