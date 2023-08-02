// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

	def scriptLib = new com.scripts.ScriptLib()
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {

			stage('Preparação') {
				steps {
					// Aqui, copiamos o arquivo segredo.sh para o diretório de trabalho do Jenkins
					copyArtifacts filter: 'segredo.sh', fingerprintArtifacts: true, projectName: 'TesteSH', selector: lastSuccessful()
				}
			}

			stage('Teste SH') {
                steps {
                    script {
						echo "Conteúdo do diretório de trabalho:"
                		sh 'ls -al'
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O TESTE DO SCRIPT SH "
						echo " --------------------------------------------------------------------------------------- "
						
						sh './segredo.sh'
					}

				}
			}
		}
	}
}