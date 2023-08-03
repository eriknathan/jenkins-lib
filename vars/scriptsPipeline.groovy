// vars/exemploPipeline.groovy

def call (Map pipelineParams) {
    
	def my_script = com.scripts.ScriptLib

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('Teste SH') {
                steps {
					// def script_bash = libraryResource 'resource/scripts/segredos.sh'
					// writeFile file: './segredos.sh', text: script_bash
					// sh 'bash ./segredos.sh'
                    script {
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						my_script.my_function("192.168.1.1", "scriptargumentvalue")
					}
				}
			}
		}
	}
}