// vars/apiRestFul.groovy
import com.example.utils.ConfigApiRest

def call (Map pipelineParams) {
    
    def dockerLib = new com.example.docker.DockerLib()

    pipelineParams.dockerImage = "${DOCKER_REGISTRY}/${pipelineParams.projectName}:${BRANCH_NAME}-${BUILD_NUMBER}"
    
    pipeline {
        agent any

        stages {
            
            stage('Build') {
                steps {
                    script {
                        echo "Fazendo o BUILD da imagem! ${JOB_NAME} | ${pipelineParams.dockerImage}"

                        //configBuild(ProjectName: pipelineParams.projectName)
                        ConfigApiRest.configBuild(pipelineParams)
                        sh dockerLib.imgBuildPhase(DockerfilePath: pipelineParams.dockerfilePath,
                                                   DockerImage: pipelineParams.dockerImage,
                                                   DockerContext: pipelineParams.dockerContext,
                                                   ProjectName: pipelineParams.projectName)
                    }
                }
            }

            stage('Push') {
                steps {
                    script {
                        echo "Fazendo o PUSH da imagem!"
                        sh dockerLib.imgPushPhase(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }

            stage('Run') {
                steps {
                    script {
                        echo "Fazendo o RUN da imagem para Rodar no nó host!"
                        
                        //configRun(BranchName: pipelineParams."${BRANCH_NAME}")
                        ConfigApiRest.configBuild(pipelineParams)
                        sh dockerLib.imgRunPhase(DockerImage: pipelineParams.dockerImage,
                                                 ProjectName: pipelineParams.projectName,
                                                 BranchName: pipelineParams."${BRANCH_NAME}")
                    }
                    deleteDir()
                }
            }
        }
    }
}