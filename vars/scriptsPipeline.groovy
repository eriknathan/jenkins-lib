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

			stage('Executar script de segredos') {
				steps {
					script {
						def segredosScript = libraryResource('resources/segredos.sh')
						sh "bash ${segredosScript}"
					}
				}
			}
		}
	}
}