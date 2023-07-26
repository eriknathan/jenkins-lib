// vars/httpPipeline.groovy

def call (Map pipelineParams) {
    
    def httpLib = new com.http.HttpLib()
	
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
						httpLib.performHealthCheck(ServiceName: pipelineParams.serviceName,
										   		  ServiceURL: pipelineParams.serviceURL)
					}
				}
            }
        }
    }
}