#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def testScript(Map params) {
		"bash ./${params.Script}.sh ${params.Nome} ${params.Day}"
	}
}


