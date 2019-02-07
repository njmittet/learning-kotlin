package no.njm

import kotlin.test.Test
import kotlin.test.assertTrue

class LibraryTest {

    @Test
    fun testLibraryMethod() {
        val classUnderTest = Library()
        assertTrue(classUnderTest.libraryMethod(), "libraryMethod should return 'true'")
    }
}
