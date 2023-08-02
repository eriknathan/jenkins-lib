// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.scripts.ScriptLib()

	pipelineParams.name1 = "Erik"
	pipelineParams.name2 = "Erik"
    
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
						
						sh scriptLib.scritpsSh(Name1: pipelineParams.name1, Name2: pipelineParams.name2)
					}

				}
			}
		}
	}
}