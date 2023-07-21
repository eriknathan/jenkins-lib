// src/com/trivy/TrivyLib.groovy

package com.trivy

class TrivyLib {

    def trivyScanning(Map params){
        ("echo Realizando o Sacanning da Imagem: ${params.DockerImage};" +
         "trivy image ${params.DockerImage};" +
		 "echo $?")
    }
}