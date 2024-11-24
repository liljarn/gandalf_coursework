package ru.liljarn.gandalf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GandalfApplication

fun main(args: Array<String>) {
	runApplication<GandalfApplication>(*args)
}
