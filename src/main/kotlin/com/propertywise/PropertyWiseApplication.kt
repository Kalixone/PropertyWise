package com.propertywise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PropertyWiseApplication

fun main(args: Array<String>) {
	runApplication<PropertyWiseApplication>(*args)
}
