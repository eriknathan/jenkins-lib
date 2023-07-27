// src/com/http/HttpLib.groovy

package com.http

class HttpLib {
	def performHealthCheck(Map params) {
		// Exibe o corpo da resposta HTTP
		("echo DETALHES DA REQUISIÇÃO HTTP AO SERVIÇO: ${params.ServiceName};" +
         "curl ${params.Url} -I")
		
		//if (response.status == 200) {
		//	"echo O serviço ${params.serviceName} está funcionando corretamente."
		//} else {
		//	"error O serviço ${params.serviceName} está com problemas. Código de resposta: ${response.status}"
		//}
	}
}