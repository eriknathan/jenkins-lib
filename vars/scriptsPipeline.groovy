// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.scripts.ScriptLib()
    
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
					sh scriptLib.scritpsSh()

				}
			}
		}
	}
}