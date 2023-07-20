// vars/exemploPipeline.groovy

def call (Map pipelineParams) {
    
    def dockerLib = new com.docker.DockerLib()

    pipelineParams.dockerImage = "${DOCKER_REGISTRY}/${pipelineParams.projectName}:${BRANCH_NAME}-${BUILD_NUMBER}"
    pipelineParams.dockerfilePath = "Dockerfile"
    pipelineParams.dockerContext = "."
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

        stages {
            
            stage ('Git Checkout') {
                steps {
                    script {
                        echo " --------------------------------------------------------------------------------------- "
                        echo " INICIANDO O CHECKOUT DO CÓDIGO FONTE DO REPOSITÓRIO GIT"
                        echo " --------------------------------------------------------------------------------------- "
				    }
				    //checkout scm
                }	
			}

            stage('Image Build') {
                steps {
                    script {
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O BUILD DA IMAGEM: ${pipelineParams.dockerImage} "
						echo " --------------------------------------------------------------------------------------- "

                        //sh dockerLib.imgBuildPhase(DockerfilePath: pipelineParams.dockerfilePath,
                                                   //DockerImage: pipelineParams.dockerImage,
                                                   //DockerContext: pipelineParams.dockerContext,
                                                   //ProjectName: pipelineParams.projectName)
                    }
                }
            }

            stage('Image Push') {
                steps {
                    script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O PUSH DA IMAGEM: ${pipelineParams.dockerImage} "
						echo " --------------------------------------------------------------------------------------- "
                        //sh dockerLib.imgPushPhase(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }

            stage('Image Run') {
                steps {
                    script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O RUN DA IMAGEM: ${pipelineParams.dockerImage} "
						echo " --------------------------------------------------------------------------------------- "
                                                
                        //sh dockerLib.imgPullPhase(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }
        }
    }
}