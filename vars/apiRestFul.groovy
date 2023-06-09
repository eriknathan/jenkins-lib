// vars/apiRestFul.groovy

def call (Map pipelineParams) {
    
    def dockerLib = new com.docker.DockerLib()

    pipelineParams.dockerImage = "${DOCKER_REGISTRY}/${pipelineParams.projectName}:${BRANCH_NAME}-${BUILD_NUMBER}"
    pipelineParams.dockerfilePath = "Dockerfile"
    pipelineParams.dockerContext = "."
    
    pipeline {
        agent any

        stages {
            
            stage('Build') {
                steps {
                    script {
                        echo "Fazendo o BUILD da imagem! ${JOB_NAME} | ${pipelineParams.dockerImage}"

                        sh configBuild(ProjectName: pipelineParams."${BRANCH_NAME}")                        

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

def configBuild(Map params){
    if ("${params.BranchName}" == 'main') {
        "echo Branch: ${params.BranchName}"
        configFileProvider([configFile(fileId: '8bae9a15-6b79-4050-afe0-3b6bcc125c78', targetLocation: '.env')]) {}
    }
}