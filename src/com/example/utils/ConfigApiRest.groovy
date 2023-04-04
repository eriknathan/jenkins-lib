// src/com/example/utils/ConfigApiRest.groovy

package com.example.utils

class ConfigApiRest {
    static void configBuild(Map params) {
        // função configBuild aqui
		//def configBuild(Map params){
			//if ("${params.ProjectName}" == "apirestul") {
		if ("${apirestful}" == "apirestful") {
			configFileProvider(
				[configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
			}
			sh "sudo python3 alerta-discord-pipeline.py"
			sh "rm alerta-discord-pipeline.py"
		}          
		//}
    }

    static void configRun(Map params) {
        // função configRun aqui
		//def configRun(Map params){
			//if ("${params.BranchName}"=="main") {
		if ("main"=="main") {
			configFileProvider(
				[configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
			}
			sh "sudo python3 alerta-discord-pipeline.py"
			sh "rm alerta-discord-pipeline.py"
		}
		//}
    }
}