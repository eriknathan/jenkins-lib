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
                        echo "Fazendo o BUILD da imagem! ${JOB_NAME} | ${pipelineParams.dockerImage}"

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
                        echo "Fazendo o PUSH da imagem!"
                        //sh dockerLib.imgPushPhase(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }

            stage('Image Pull') {
                steps {
                    script {
                        echo "Fazendo o Pull da imagem para Rodar no nó host!"
                                                
                        //sh dockerLib.imgPullPhase(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }
        }
    }
}