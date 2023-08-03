// vars/exemploPipeline.groovy

def call (Map pipelineParams) {
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			stage('Listar conteúdo da biblioteca compartilhada') {
				steps {
					script {
						sh "ls -R ${libraryResource('.')}"
					}
				}
			}

			stage('Teste SH') {
                steps {
                    script {
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						def segredosScript = libraryResource('segredos.sh')
            			sh "bash ${segredosScript}" 
					}

				}
			}
		}
	}
}