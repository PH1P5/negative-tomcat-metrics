package de.phitho.tomcatmetrics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TomcatMetricsApplication

fun main(args: Array<String>) {
	runApplication<TomcatMetricsApplication>(*args)
}
