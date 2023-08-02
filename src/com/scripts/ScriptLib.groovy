#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def scritpsSh(Map params){
		'''
			NOME="${params.Name1}"
			NOME_2="${params.Name2}"

			[ "$NOME" = "$NOME_2" ] && echo "Nomes são Iguais!"
		'''
    }
}


