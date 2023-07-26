// vars/httpPipeline.groovy

def call (Map pipelineParams) {
    
    def httpLib = new com.http.HttpLib()

    pipelineParams.service = "Google"
    pipelineParams.serviceURL = "www.google.com"
    
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
						// Chamando a função de health check
						httpLib.performHealthCheck(ServiceName: pipelineParams.service,
										   		  ServiceURL: pipelineParams.serviceURL)
					}
				}
            }
        }
    }
}