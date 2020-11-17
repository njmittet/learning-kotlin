package no.njm

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HelloWorldTest {

    @Test
    fun testHelloWorld() {
        assertEquals("Hello, World", getGreeting())
    }
}