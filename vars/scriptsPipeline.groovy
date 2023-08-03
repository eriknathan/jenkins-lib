// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.script.ScriptLib()

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
						
						sh scriptLib.testScript(Nome:"Erik", Day:"Quinta")
					}
				}
			}
		}
	}
}