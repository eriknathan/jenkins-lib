// src/com/trivy/TrivyLib.groovy

package com.docker

class DockerLib {

    def trivyScanning(Map params){
        ("echo Realizando o Sacanning da Imagem: ${params.DockerImage};" +
         "trivy image ${params.DockerImage}")
    }
}