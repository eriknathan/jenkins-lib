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

                        sh "sudo /home/eriknathan/Estudos/Jenkins/jenkins-lib/src/com/scripts/segredo.sh"
					}
				}
			}
		}
	}
}