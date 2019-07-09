package no.njm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

data class Greeting(val id: Long, val content: String)

@RestController
class GreetingController {

    val counter = AtomicLong()

    // Expression body function.
    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    // Block body functions requires an explicit return type and a "return" statement.
    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "Friend") name: String): Greeting {
        return Greeting(counter.incrementAndGet(), "Greeting, $name")
    }
}