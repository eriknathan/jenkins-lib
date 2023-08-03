// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptbash = libraryResource 'com/scripts/segredos.sh'

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('ExecShellScritp') {
                steps {
                        

					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "

						sh script: scriptbash, label: 'ExecShellScritp'
					}
				}
			}
		}
	}
}