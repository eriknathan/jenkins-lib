// src/com/functions/ConfigLib.groovy

package com.functions

class ConfigLib {
	def copyFiles() {
		def envjson = libraryResource 'com/json/projectsFilesList.json'
		def json = readJSON text: envjson

		def fileId = json.santacruz.santacruz-fe.findResult { environment -> environment[homolog] }

		if (fileId) {
			echo "ID branch homolog do projeto santacruz-fe: ${fileId}"
			configFileProvider([configFile(fileId: fileId, targetLocation: '.env')]) {}
		} else {
			echo "Não foi encontrando o Id da branch homolog no projeto santacruz-fe."
		}
	} 
}