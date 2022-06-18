package no.njm

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application : CommandLineRunner {

    companion object : Logging

    override fun run(vararg args: String?) {
        logger.debug("Running.")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
