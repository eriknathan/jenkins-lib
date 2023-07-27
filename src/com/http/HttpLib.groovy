// src/com/http/HttpLib.groovy

package com.http

class HttpLib {

	def performHealthCheck(Map params) {
		response = httpRequest(url: "${params.Url}", httpMode: 'GET')

		// Exibe o status da resposta HTTP
		"echo Status da resposta: ${response.status}"
	
		// Exibe o corpo da resposta HTTP
		"echo DETALHES DA REQUISIÇÃO HTTP AO SERVIÇO: ${params.serviceName}"
		"curl ${url} -I"
		
		if (response.status == 200) {
			"echo O serviço ${params.serviceName} está funcionando corretamente."
		} else {
			"error O serviço ${params.serviceName} está com problemas. Código de resposta: ${response.status}"
		}
	}
}