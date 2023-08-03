#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def my_function(Map params) {
		def script_content = libraryresource 'my_scripts/test.sh'
		// create a file with script_bash content
		writefile file: './test.sh', text: script_content
		echo "execute remote script test.sh..."
		def sshcommand = "echo ip ${params.serverip} and name ${params.scriptargument}"
		echo "ssh command is: ${sshcommand}"
		sh(sshcommand)
	}
}


