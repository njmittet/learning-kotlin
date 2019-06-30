package no.njm

import org.apache.logging.log4j.kotlin.logger

fun getGreeting(): String {
    val words = mutableListOf<String>()
    words.add("Hello")
    words.add("World")
    return words.joinToString(separator = ", ")
}

fun main() {
    logger("no.njm.Main").info(getGreeting())
}