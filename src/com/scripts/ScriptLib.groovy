#!/usr/bin/env bash

package com.scripts

class ScriptLib {

    def scritpsSh(){
		("./resource/segredos.sh > segredo.sh;"+
		 "chmod +x segredo.sh;"+
		 "./segredo.sh")

    }
}


