// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			
			stage('Hello World') {
                steps {  
					script {
						echo " --------------------------------------------------------------------------------------- "
						echo " HELLO! WORLD. "
						echo " --------------------------------------------------------------------------------------- "
					}
				}
			}
		}
	}
}


