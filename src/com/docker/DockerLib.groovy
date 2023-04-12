// src/com/docker/DockerLib.groovy

package com.docker

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
    }

    def configBuild(Map params) {
        if ("${params.BranchName}" == 'main') {
            "echo Branch: ${params.BranchName}"
            configFileProvider([configFile(fileId: '8bae9a15-6b79-4050-afe0-3b6bcc125c78', targetLocation: '.env')]) {}
        }
    }
}