package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringBootApplication

fun main(args: Array<String>) {
    runApplication<com.example.SpringBootApplication>(*args)
}
