#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def my_function(string serverip, string scriptargument) {
		def script_content = libraryresource 'my_scripts/test.sh'
		// create a file with script_bash content
		writefile file: './test.sh', text: script_content
		echo "execute remote script test.sh..."
		def sshcommand = "ssh username@${serverip} \'bash -xs\' < ./test.sh ${scriptargument}"
		echo "ssh command is: ${sshcommand}"
		sh(sshcommand)
	}
}


