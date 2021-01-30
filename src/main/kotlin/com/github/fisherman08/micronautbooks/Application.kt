package com.github.fisherman08.micronautbooks

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.example")
		.start()
}

