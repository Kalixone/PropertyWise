package com.propertywise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PropertyWiseApplication

fun main(args: Array<String>) {
	runApplication<PropertyWiseApplication>(*args)
}
