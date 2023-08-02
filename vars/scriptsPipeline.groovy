// vars/exemploPipeline.groovy

def call (Map pipelineParams) {
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

		stages {
			stage('Meu Stage') {
				steps {
					sh '/home/eriknathan/Estudos/Jenkins/jenkins-lib/src/com/scripts/segredo.sh'
				}
			}
		}
    }
}