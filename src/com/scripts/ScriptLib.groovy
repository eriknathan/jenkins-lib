#!/usr/bin/env bash

package com.scrits

class ScriptLib {

    def scritpsSh(Map params){
		'''
			function soma () {
				local result=$(($1+$2))
				echo $result
			}

			soma 15 15
		'''
    }
}


