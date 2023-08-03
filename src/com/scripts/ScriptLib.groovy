#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def testScript(Map params) {
		def scriptbash = libraryResource "com/scripts/${params.Script}.sh"
		"echo ${params.Script} ${params.Nome} ${params.Day}"
		writeFile file: "./${params.Script}.sh", text: scriptbash
		"bash ./${params.Script}.sh ${params.Nome} ${params.Day}"
	}
}


