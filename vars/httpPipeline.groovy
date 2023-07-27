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

						def serviceName = 'YouTube' 
						def serviceURL = 'https://www.isitics.com/' 
						
						def response = httpRequest "${serviceURL}"
						
						if (response.status == 200) {
							echo "O serviço ${serviceName} está funcionando corretamente."
						} else {
							error "O serviço ${serviceName} está com problemas. Código de resposta: ${response.status}"
							echo "curl ${serviceURL} -I"
						}
					}
				}
            }
        }
    }
}