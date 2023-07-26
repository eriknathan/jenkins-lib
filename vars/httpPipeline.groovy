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
						// echo " --------------------------------------------------------------------------------------- "
                        // echo " HEALTH CHECK"
                        // echo " --------------------------------------------------------------------------------------- "					
						// // Chamando a função de health check
						// httpLib.performHealthCheck(ServiceName: pipelineParams.serviceName,
						// 				   		   ServiceURL: pipelineParams.serviceURL)
						def serviceName = 'Google' // Substitua pelo nome do serviço que você deseja verificar
						def serviceURL = 'www.google.com' // Substitua pela URL do serviço
						
						def response = httpRequest(
							url: serviceURL,
							validResponseCodes: '200', // Códigos de resposta válidos que indicam que o serviço está funcionando
							validResponseContent: serviceName // Conteúdo esperado na resposta para verificar se é o serviço correto
						)
						
						if (response.status == 200) {
							echo "O serviço ${serviceName} está funcionando corretamente."
						} else {
							error "O serviço ${serviceName} está com problemas. Código de resposta: ${response.status}"
						}
					}
				}
            }
        }
    }
}