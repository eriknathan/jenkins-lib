#!/usr/bin/env bash

function soma () {
	local result=$(($1+$2))
	echo $result
}

soma 15 15