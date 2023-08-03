#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def testScript(Map params) {
		def scriptbash = libraryResource 'com/scripts/'

		writeFile file: './segredos.sh', text: scriptbash

		"bash ./${params.Script}.sh ${params.Nome} ${params.Day}"
	}
}


