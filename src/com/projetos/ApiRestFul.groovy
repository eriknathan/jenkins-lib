// src/com/projetos/ApiRestFul.groovy

package com.projetos

class ApiRestFul {

	def build(Map params) {
		if ("${params.ProjectName}" == 'apirestful') {
			configBuild()
		}
	}

	def run(Map params) {
		if ("${params.BranchName}"=="main") {
			configRun()
		}
	}
}