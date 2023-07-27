// src/com/http/HttpLib.groovy

package com.http

class HttpLib {

	def performHealthCheck(Map params) {
		serviceName = "Dev - Frevo - FE"
		url = 'https://dev.frevo.isitics.com/' 
		response = httpRequest(url: url, httpMode: 'GET')

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