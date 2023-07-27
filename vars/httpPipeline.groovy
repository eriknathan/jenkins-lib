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

						sh httpLib.performHealthCheck()
					}
				}
            }
        }
    }
}