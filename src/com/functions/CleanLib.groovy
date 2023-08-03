// src/com/http/HttpLib.groovy

package com.functions

class CleanLib {
	def cleanFiles(Map params) {
		("echo Limpando arquivo temporário: ${params.File};" +
         "sudo rm ${params.File}")
	}
}