package no.njm

class GreetingJoiner(private val greeting: Greeting) {

    // The ? indicates possible null-values.
    private val names = ArrayList<String?>()

    fun addName(name: String?) {
        names.add(name)
    }

    fun joinGreetings(): String {
        return "${greeting.greeting} ${names.filterNotNull().joinToString(separator = ", ")}"
    }
}