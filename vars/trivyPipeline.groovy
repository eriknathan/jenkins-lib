// vars/exemploPipeline.groovy

def call (Map pipelineParams) {
    
    def dockerLib = new com.docker.DockerLib()
    def trivyLib = new com.trivy.TrivyLib()

    //pipelineParams.dockerImage = "${DOCKER_REGISTRY}/${pipelineParams.projectName}:${BRANCH_NAME}-${BUILD_NUMBER}"
    pipelineParams.dockerImage = "nginx:latest"
    pipelineParams.dockerfilePath = "Dockerfile"
    pipelineParams.dockerContext = "."
    
    pipeline {
        agent { 
            label 'ubuntu'
        }

        stages {

            stage('Image Pull') {
                steps {
                    script {
                        echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O Pull DA IMAGEM: ${pipelineParams.dockerImage} "
						echo " --------------------------------------------------------------------------------------- "

                        sh dockerLib.imgPullPhase(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }

            stage('Trivy Scanning') {
                steps {
                    script {
						echo " --------------------------------------------------------------------------------------- "
						echo " INICIANDO O PUSH DA IMAGEM: ${pipelineParams.dockerImage} "
						echo " --------------------------------------------------------------------------------------- "
                        sh trivyLib.trivyScanning(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }

        }
    }
}