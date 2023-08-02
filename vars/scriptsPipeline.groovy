// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.scripts.ScriptLib()

	pipelineParams.nameOne = "Erik"
	pipelineParams.nameTwo = "Erik"
    
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
						
						sh scriptLib.scritpsSh(Name1: pipelineParams.nameOne, Name2: pipelineParams.nameTwo)
					}

				}
			}
		}
	}
}