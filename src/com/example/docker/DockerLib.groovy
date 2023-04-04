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
}