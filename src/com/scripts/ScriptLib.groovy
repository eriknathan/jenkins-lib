#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def testScript(Map params) {
		"echo ${params.Script} ${params.Nome} ${params.Day}"
		def scriptbash = libraryResource "com/scripts/${params.Script}.sh"
		writeFile file: "./${params.Script}.sh", text: scriptbash
		"bash ./${params.Script}.sh ${params.Nome} ${params.Day}"
	}
}


