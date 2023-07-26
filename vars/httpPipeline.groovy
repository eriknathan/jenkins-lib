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
            stage('Trivy Scanning') {
                steps {
					script {						
						// Chamando a função de health check
						httpLib.performHealthCheck(ServiceName: pipelineParams.service,
										   		  ServiceUrl: pipelineParams.serviceURL)
					}
				}
            }
        }
    }
}