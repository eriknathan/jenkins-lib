// vars/exemploPipeline.groovy

def call (Map pipelineParams) {

    pipeline {
        agent { 
            label 'ubuntu'
        }

		environment {
			DOCKER_IMAGE = "${DOCKER_HUB}/${projectName}:${BRANCH_NAME}-${BUILD_NUMBER}"
			BRANCH_NAME = "prod"
			PROJECT_NAME = "santacruz"
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

			stage('Nome do Estágio') {
				when {
					expression {
						return PROJECT_NAME == 'santacruz' && (BRANCH_NAME in ['develop', 'prod', 'homolog'])
					}
				}
				agent {
					label determineAgent(env.BRANCH_NAME)
				}
				steps {
					echo "Pegou"
				}
			}
			
		}
	}
}

def determineAgent(branchName) {
    switch (branchName) {
        case 'develop':
            return 'teste'
        case 'prod':
            return 'servidor'
        case 'homolog':
            return 'ubuntu'
        default:
            return 'agente_padrao' // Agente padrão para outras branches
    }
}