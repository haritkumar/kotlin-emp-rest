package com.emp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmpRestWsApplication

fun main(args: Array<String>) {
	runApplication<EmpRestWsApplication>(*args)
}
