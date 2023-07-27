// src/com/http/HttpLib.groovy

package com.http

class HttpLib {

	def performHealthCheck(Map params) {
		def serviceName = "Dev - Frevo - FE"
		def url = 'https://dev.frevo.isitics.com/' 
		def response = httpRequest(url: url, httpMode: 'GET')

		// Exibe o status da resposta HTTP
		echo "Status da resposta: ${response.status}"
	
		// Exibe o corpo da resposta HTTP
		echo "DETALHES DA REQUISIÇÃO HTTP AO SERVIÇO: ${serviceName}"
		"curl ${url} -I"
		
		if (response.status == 200) {
			echo "O serviço ${serviceName} está funcionando corretamente."
		} else {
			error "O serviço ${serviceName} está com problemas. Código de resposta: ${response.status}"
		}
	}
}