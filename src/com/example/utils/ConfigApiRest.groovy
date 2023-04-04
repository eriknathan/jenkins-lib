// src/com/example/utils/ConfigApiRest.groovy
package com.example.utils

import org.jenkinsci.plugins.configfiles.ConfigFile;

class ConfigApiRest {
	
    static void configBuild(Map params) {
		if ("apirestful" == "apirestful") {
			configFileProvider(
				[configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
			}
			sh "sudo python3 alerta-discord-pipeline.py"
		}          
    }

    static void configRun(Map params) {
		if ("main"=="main") {
			configFileProvider(
				[configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
			}
			sh "sudo python3 alerta-discord-pipeline.py"
		}
    }
}