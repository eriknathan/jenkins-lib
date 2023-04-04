// src/com/example/docker/DockerLib.groovy

package com.example.docker

class DockerLib {

    def imgBuildPhase(Map params){
        "docker build -t ${params.DockerImage} --no-cache -f ${params.DockerfilePath} ${params.DockerContext}"
    }

    def imgPushPhase(Map params){
        "docker push ${params.DockerImage}"
    }

    def imgRunPhase(Map params){
        "echo DOCKER_IMAGE=${params.DockerImage} >> .env"
        "echo CONTAINER_NAME=${params.ProjectName}-${params.BranchName} >> .env"
        
        "docker image pull ${params.DockerImage}"
        //"docker-compose -f docker-compose.yml -p ${params.ProjectName}-${params.BranchName} up -d"
        //"docker run -p 8000:8000 ${params.DockerImage}"
    }

	def configBuild(Map params) {
		if ("${params.ProjectName}" == "apirestful") {
			configFileProvider(
				[configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
			}
			sh "sudo python3 alerta-discord-pipeline.py"
		}          
	}
}