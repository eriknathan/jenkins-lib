#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def scritpsSh(){
		'''
			NOME="Erik"
			NOME_2="Erik"

			[ "$NOME" = "$NOME_2" ] && echo "Nomes são Iguais!"
		'''
    }
}


