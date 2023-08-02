#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def scritpsSh(){
		'''
			function soma () {
				local result=$(($1+$2))
				echo $result
			}

			soma 15 15
		'''
    }
}


