// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('ExecShellScritp') {
                steps {
                        
            		def scriptbash = libraryResource 'com/scripts'

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