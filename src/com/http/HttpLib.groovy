// src/com/http/HttpLib.groovy

package com.http

class HttpLib {

	def performHealthCheck(Map params) {
		def response = httpRequest(
			url: ${params.serviceURL},
			validResponseCodes: '200',
			validResponseContent: ${params.ServiceName}
		)

		if (response.status == 200) {
			echo "O serviço ${params.ServiceName} está funcionando corretamente."
		} else {
			error "O serviço ${params.ServiceName} está com problemas. Código de resposta: ${response.status}"
		}
	}
}