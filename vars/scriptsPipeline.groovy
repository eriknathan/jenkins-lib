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
						
						def script_bash = libraryResource 'resource/scripts/segredos.sh'
						writeFile file: './test.sh', text: script_bash
            			sh 'bash ./test.sh'
					}
				}
			}
		}
	}
}