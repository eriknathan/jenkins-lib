// vars/apiRestFul.groovy

def call (Map pipelineParams) {
    
    def dockerLib = new com.docker.DockerLib()
    def apiRestFul = new com.projetos.ApiRestFul()

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

                        apiRestFul.config(ProjectName: pipelineParams.projectName)                        

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
                        
                        // configRun(BranchName: pipelineParams."${BRANCH_NAME}")
                        
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
// criar uma função apenas com o configFileProvider, e criar um arquivo separado para essa pipeline de configuções e chamar a função com o configFile... nele.
// def configBuild(Map params) {
//     if ("${params.ProjectName}" == 'apirestful') {
//         configFileProvider(
//             [configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
//             sh "sudo python3 alerta-discord-pipeline.py"
//         }
//     }
// }

def configBuild = {
    configFileProvider(
        [configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
        sh "sudo python3 alerta-discord-pipeline.py"
    }

}

// def configRun(Map params) {
//     if ("${BRANCH_NAME}"=="main") {
//         configFileProvider(
//             [configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
//             sh "sudo python3 alerta-discord-pipeline.py"
//         }
//     }
// }