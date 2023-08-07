// src/com/functions/ConfigFile.groovy

package com.functions

class ConfigFileLib {
	def copyFiles(Map params) {
		def envjson = libraryResource 'com/json/projectsFilesList.json'
		def json = readJSON text: envjson

		def fileId = json.santacruz."${params.ProjectName}".findResult { environment -> environment["${params.BranchName}"] }

		if (fileId) {
			echo "ID branch ${params.BranchName} do projeto ${params.ProjectName}: ${fileId}"
			configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
		} else {
			echo "Não foi encontrando o Id da branch ${params.BranchName} no projeto ${params.ProjectName}."
		}
	} 
}

