// src/com/http/HttpLib.groovy

package com.http

class HttpLib {
	def performHealthCheck(String serviceName, String serviceURL) {
		def response = httpRequest(
			url: serviceURL,
			validResponseCodes: '200',
			validResponseContent: serviceName
		)

		if (response.status == 200) {
			echo "O serviço ${serviceName} está funcionando corretamente."
		} else {
			error "O serviço ${serviceName} está com problemas. Código de resposta: ${response.status}"
		}
	}
}