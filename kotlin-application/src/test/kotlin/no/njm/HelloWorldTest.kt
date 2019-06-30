package no.njm

import org.junit.Test
import kotlin.test.assertEquals

class HelloWorldTest {

    @Test
    fun testHelloWorld() {
        assertEquals("Hello, World", getGreeting())
    }
}