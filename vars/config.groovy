def configBuild(Map params) {
		if ("${params.ProjectName}" == "apirestful") {
			configFileProvider(
				[configFile(fileId: '9b574e66-ecee-4080-a3b0-890227ab7314', targetLocation: "alerta-discord-pipeline.py")]) {
			}
			sh "sudo python3 alerta-discord-pipeline.py"
		}          
	}