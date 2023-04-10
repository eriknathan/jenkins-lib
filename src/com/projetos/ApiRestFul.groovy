// src/com/projetos/ApiRestFul.groovy

package com.projetos

class ApiRestFul {

	def Build(Map params) {
		if ("${params.ProjectName}" == 'apirestful') {
			configBuild()
		}
	}
}