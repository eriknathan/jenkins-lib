// vars/httpPipeline.groovy

def call (Map pipelineParams) {
    
    def httpLib = new com.http.HttpLib()

    pipelineParams.service = "Google"
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

        stages {
            stage('Trivy Scanning') {
                steps {
					script {
						def serviceName = 'Google'
						def serviceURL = 'www.google.com'
						
						// Chamando a função de health check
						performHealthCheck(serviceName, serviceURL)
					}
				}
            }
        }
    }
}