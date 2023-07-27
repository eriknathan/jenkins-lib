// vars/httpPipeline.groovy

def call (Map pipelineParams) {
    
    def httpLib = new com.http.HttpLib()

    pipelineParams.serviceURL = "https://dev.frevo.isitics.com/"
    pipelineParams.serviceName = "Dev - Frevo - FE"
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

        stages {
            stage('Helth Check') {
                steps {
					script {	
						echo " --------------------------------------------------------------------------------------- "
                        echo " HEALTH CHECK"
                        echo " --------------------------------------------------------------------------------------- "									
						
						response = httpRequest "${pipelineParams.serviceURL}"
						sh httpLib.performHealthCheck(Url: pipelineParams.serviceURL,
                                                   	  ServiceName: pipelineParams.serviceName)
					}
				}
            }
			stage('Teste Script SH') {
				steps {
					// Exemplo de execução de um script em shell
					sh '''
						echo "Iniciando a execução do script em shell..."
						ls -la
						echo "Fim da execução do script em shell."
					'''
				}
			}
        }
    }
}