# learning-kotlin

Notes taken while learing [Kotlin](https://kotlinlang.org). Examples taken from [Kotlin by Example](https://play.kotlinlang.org/byExample/) and
[The Kotlin Documentation](https://kotlinlang.org/docs/home.html).

## Language Features

Package specification is optional: If you don't specify a package in a source file, its content goes to the default package.

```kt

package org.kotlinlang.play

// The main functon may or may not take arguments.
fun main() {
    println("Hello, World!")
}

fun main(args: Array<String>) {
    println("Hello, World!")
}
```

### This-expressions

If `this` has no qualifiers, it refers to the innermost scope. To refer to `this` in other scopes, use label qualifiers:

```kt
class A {
    inner class B {
        fun Int.foo() {
            // A's this
            val a = this@A
            // B's this
            val b = this@B
            // foo()'s receiver, an Int
            val c = this 

            val myFunction = lambda@ fun String.() {
                // myFunction's receiver, a String
                val d = this 
            }

            val myLambda = { s: String ->
                // foo()'s receiver, since enclosing lambda expression doesn't have any receiver
                val d1 = this
            }
        }
    }
}            
```

When calling a member function, `this` can be skipped, unless there exists a non-member function with the same name.

```kt
fun printLine() { }

class A {
    fun printLine() { }

    fun invokePrintLine()  { 
        // Calls outer function
        printLine()
        // Calls member function
        this.printLine()
    }
}
```

### String Templates

String templates allows inclusion of variable references and expressions into strings.

```kt
val name = "Name"

// Variable reference
println("Hello $name")  
// Expressions enclosed in curly braces
println("Hello ${name.toUpperCase()}")
```

### Destructuring

A `destructuring declaration` creates multiple variables at once by destructuring an object into a number of variables:

```kt
val (name, age) = person
```

A destructuring declaration is compiled down to the following code:

```kt
val name = person.component1()
val age = person.component2()
```

Anything can be on the right-hand side of a destructuring declaration, as long as the required number of component functions can be called on it.

Array:

```kt
val (x, y, z) = arrayOf(5, 10, 15) 
```

maps, note thte useage of `ifiox` funsion `to` returning the built-in `Pair`

```kt
val map = mapOf("Alice" to 21, "Bob" to 25)
for ((name, age) in map) {                                      
    println("$name is $age years old")          
}

The built-in `Pair` and `Triple` types support destructuring too, even as return values from functions:

```kt
// Destructuring a Pair returned from a method
val (min, max) = findMinMax(listOf(100, 90, 50, 98, 76, 83))

// Destructuring a Tripple. Skipp one or more values by using underscore. Avoids complaints about unused variable from the compiler.
val (_, lastname, email) = Tripple("Firstname", "Lastname", "firstname@lastname.com")
```

#### Pair

Represents a generic pair of two values. There is no meaning attached to values in this class, it can be used for any purpose.

Two Pairs are equal if all three components are equal.

```kt

val pair = Pair(1, "x")

// Destructuring
val (a, b) = Pair(1, "x")
```

The infix functopn `to` can be used to create a Pair.

```kt
val pair = 2 to 4
```

Pair implements the functions `toString()` and `toList()`.

#### Tripple

Represents a generic triad of values. There is no meaning attached to values in this class, it can be used for any purpose.

Two triples are equal if all three components are equal.

Unlike Pair, no `inline infix` function for creating a Tripple exists.

```kt
val triple = Triple("John", 20, true)

// Destructuring a tripple 
val (name, age, isMediumMember) = triple
```

Tripple implements the functions `toString()` and `toList()`.

### Null Safety

Variable types in Kotlin don't allow the assignment of `null`. If one needs a variable that can be null, it must be declared with an `?` at the end of its type.
The same goes for return values from functions.

```kt
var neverNull: String = "Can never be null"

var nullable: String? = null

fun myFunction(input: String): String {
    // Neither the the input variable or the return value are nullable.
}

fun myFunction(input: String?): String {
    // The input variable is nullable but the function never returns null.
}

fun myFunction(input: String?): String? {
    // Both the input variable and the return value are nullable.
}
```

### Variables

Kotlin has type inference, so its usually not necessary to explicitly declare the type of a variable. Use `val` over `var` since immutability is reccomended.

Mutable variables can be declared without being initialized, but the compiler will complain if that variable is tried read before it's initialized. Immutable variables must always be initialized.

```kt
// Mutable initialized variable with explicit type.
var a: String = "Value"
// Imutable initialized variable with explicit type.
val b: Int = 1
// Imutable initialized variable, where the type us inferd by the compiler.
val c = 3
```

Late initialization of a variable:

```kt
val value: Int

if (someCondition()) {
    value = 1
} else {
    value = 2
}

// The variable can be read without the compiler complaining.
println()
```

### Smart Casts

The Kotlin compiler performs type casts automatically, including casts from nullable types to their non-nullable counterparts and casts from a supertype to a subtype.

```kt
// Declares a nullable variable.
val map: Map<String, String>? = buildMyMap()

if (map != null) {
    // Smart-cast to non-nullable, allowing the call to .size
    println(map.size)
}

// Smart-cast inside a condition, becuse of short-circuiting.
if (map != null && map.isNotEmpty()) {
    println(map.size)
}


if (map is HashMap) {
    // Smart-cast to the subtype HashMap.
    println(map["key-1"])
}

fun buildMyMap(): Map<String, String>? {
    return mapOf("key-1" to "value-1")
}
```

## Functions

Kotlin functions are declared using the `fun` keyword.

A function returning a value must have an explicit return if the function body has more than a single expression:

```kt
fun sum(x: Int, y: Int): Int {
    val i = 10
    return (x + y) + 1
}
```

An explicit return can be omitted if a function only has a single expression:

```kt
fun multiply(x: Int, y: Int) = x * y


### Unit & Nothing

The `Unit` type is used to maintain Void interoperability with Java. By default, Java's `void` is mapped to Unit type in Kotlin:

```kt
fun printMessage(message: String): Unit
    println(message)
}
```

`Unit` is the return type even if it is omitted:

```kt
fun printMessage(message: String) {
    println(message)
}
```

`Nothing` is a special type in Kotlin that is used to represent a value that never exists:

```kt
fun alwaysThrowException(): Nothing {
    throw IllegalArgumentException()
}
```

`Nothing` protects from unwarranted code. When functions returning `Nothing` are invoked, the compiler will not execute beyond the function call and omit a warning:

```kt
fun invokeANothingOnlyFunction() {
    alwaysThrowException()
    // The compiler will warn about unreachable code.
    anotherFunctionCall()
}
```

### Default Values

Functions can have default values:

```kt
fun printMessageWithDefaultPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}
```

### Named Arguments

Named arguments allows ignoring a functions parameter order:

```kt
printMessageWithDefaultPrefix(prefix = "Log", message = "Hello")
```

### Varargs

Varargs allow you to pass any number of arguments to a funviton by separating them with commas:

val myMap = mapOf(
    "key-1" to "value-1",
    "key-1" to "value-1",
    "key-1" to "value-1"
)

A function accepting varargs uses the `vararg` keyword:

```kt
fun printAll(vararg messages: String) {
    messages.forEach { message -> println(message) }
}
```

Using the spread operator to expand an array to varargs arguments:

```kt
val entries = arrayOf("En", "To", "Tre")
printAll(*entries)
```

### Infix Functions

Functions marked with the `infix` keyword can be called while omitting the dot and the parentheses normally used, but they:

1. Must be member function or extension functions.
2. Have a single parameter only.
3. Can not be a `varargs` function.
4. Must not have default values.

Also, infix functions always require both the receiver and the parameter to be specified.

Using `to` to create a map of `Pairs`.

```kt
val myMap = map(
  1 to "one",
  2 to "two",
  3 to "three"
)
```

Defining an extension function as an infix function:

```kt
infix fun Int.plus(x: Int): Int {

}

val four = 2 plus 2
```

Defining amember function as an infix function:

```kt
class Rules {
    private val rules = mutableListOf<String>()

    infix fun add(string: String) {
        rules.add(string)
    }

    fun build() {
        this add "abc"
        add("abc")
    }
}

val rules = Rules()
rules.build()
rules add "Rule"
```

### Function Scope

Kotlin functions can be declared at the top level in a file. In addition, functions can also be declared locally as member functions and extension functions. Kotlin also supports local functions, which are functions inside other functions:

A local function can access local variables of outer functions (the closure);

```kt
fun login(firstValue: String, secondValue: String) {
    var errorMessages = emptyList()
    fun validate(value: String){
        // ...
    }
    validate(firstValue)
    validate(secondValue)
}
```

A member function is a function that is defined inside a class or object:

```kt
class MyClass {

    fun foo() { 
        // ...
    }
}

// Functions are called with `.dot` notation.
Sample().foo()
```

### Generic Functions

Functions can be generified if their logic is independent of a specific type.

```kt
fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

// Letting the compiler infer the generic type when calling the generic function.
val intList = singletonList(1)
val stringList = singletonList("String")
```

## Extension Functions

Kotlin lets you add new functions to any class. Extension function look a lot like normal functions, but the type they extend must be specified:

```kt
data class Order(val items: Collection<Item>)
data class Item(val name: String, val price: Int)

// Creting the extension function.
fun Order.priceOfMostExpensiveItem() = items.maxByOrNull { it.price }?.price ?: 0

val orderList = Order(
    listOf(
        Item("First", 25),
        Item("Second", 29),
        Item("Third", 12)
    )
)
val emptyList = Order(emptyList())
```

It is even possible to add extension functions to null references:

```kt
fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"
```

### Extension Properties

It is also possible to add extensions properties to classes:

```kt
val Order.commaDelimitedItemNames: String
    // A custom getter for the extension property.
    get() = items.joinToString { it.name }
```

## Higher-Order Functions

Kotlin functions are first-class, which means they can be stored in variables and data structures, and can be passed as arguments to and returned from other higher-order functions. A higher-order function is a function that takes functions as parameters, or returns a function.

Taking a function (lambda) as a parameter:

```kt
// A function that returns the integer returned by the provided function.
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

// Declaring a function that sums two integers.
fun sum(x: Int, y: Int) = x + y   

// Calling the higher-order  function with sum as operation, references by name
val sumResult = calculate(4, 5, ::sum)

// Calling the higher-order r function with a lambda as the operation.
val multiplicationResult = calculate(4, 5) { a, b -> a * b }
```

Returning a function:

```kt
// Declares a higher-order function that returns a function. (Int) -> Int represents the parameters and return type of returned function.
fun createOperation(): (Int) -> Int {
    return ::square
    // Or the lambda equivalent.
    // return { x: Int -> square(x) }
}

fun square(x: Int) = x * x

val operation = createOperation()
val result = operation(4)
```

## Lambda Functions

Lambda expressions and anonymous functions are function literals. Function literals are functions that are not declared but are passed immediately as an expression.

Lambda expression is always surrounded by curly braces and the last expression inside the lambda body is treated as the return value.

```kt
// The full syntactic form, with all explicit types, of a lambda expression.
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

// However, lambdas can be denoted very concisely in many cases thanks to type inference and the implicit "it" variable.
val upperCase: (String) -> String = { it.uppercase() }

// Function pointers can be used it the lambda consists of a single function call.
val upperCase6: (String) -> String = String::uppercase

// If the last parameter of a function is a function, then a lambda expression can be placed outside 
// the parentheses, also known as  a trailing lambda.
val sum = listOf(1, 2, 3).fold(1) { acc, e -> acc * e }

// If the lambda is the only argument in that call, the parentheses can be omitted entirely.
listOf("first,", "second").forEach { println(it.name.uppercase())
```

## Inline Functions

Using higher-order functions imposes a runtime penalty since each function is an object, and it captures a closure. A closure is a scope of variables that can be accessed in the body of the function. Memory allocations and virtual calls causes theruntime overhead. In many cases this kind of overhead can be eliminated by inlining the lambda expressions.

```kt
fun <T> withLock(lock: Lock, operation: () -> T): T {
    lock.lock()
    val result = operation()
    lock.unlock()
    return result
}

val result = withLock(ReentrantLock()) {"Locked".uppercase()}
}
```

Consider the above example: instead of creating a function object for the parameter and generating a call, the compiler could emit the following code:

```kt
lock.lock()
return try {
    operation()
} finally {
    lock.unlock()
}
```

To make the compiler do this, mark the lock() function with the `inline` modifier:

```kt
inline fun <T> withLock(lock: Lock, operation: () -> T): T {
    lock.lock()
    val result = operation()
    lock.unlock()
    return result
}

val result = withLock(ReentrantLock()) {"Locked".uppercase()}
}
```

The inline modifier affects both the inlined function and the lambdas passed to it: all of those will be inlined. To avoid all lambdas passed to an inline function to be inlined, mark some of your function parameters with the `noinline` modifier.

Inlining will pay off in performance, especially inside loops, but it might cause the generated code to grow.

### Returns and Jumps (Labels)

Kotlin has three structural jump expressions:

1. `return` by default returns from the *nearest enclosing function* (or anonymous function).
2. `break` terminates the *nearest enclosing loop*.
3. `continue` proceeds to the next step of the *nearest enclosing loop*.

Any expression may be marked with a label, such as `abc@`. The label can be used to qualify a `break`, `continue` or `return`.

A qualified `break` exits the labeled loop:

```kt
loop@ for (i in 1..10) {
    for (j in 1..10) {
        // Terminates the outer loop.
        if (j == 5) break@loop
        println("$i:$j")
    }
}
```

A `continue` jumps to the next iteration of the labeled loop.

loop@ for (i in 1..10) {
    for (j in 1..10) {
        // Continues on the next outer loop iteration.
        if (j == 5) continue@loop
        println("$i:$j")
    }
}

## Operator Functions (Operator Overloading)

See the [complete list of operator symbols](https://kotlinlang.org/docs/operator-overloading.html#in-operator).

Kotlin allows custom implementations for predefined set of operators on types:

```kt
// This infix function can be converted to an operator function by replacing infix with operator.
infix fun Int.times(string: String) = string.repeat(this)
println(2 times "Bye ")

// The corresponsing operator function. Since the operator symbol for times() is * the function call can be changed to.
operator fun Int.times(str: String) = str.repeat(this)
println(2 * "Bye ")
```

The `Collection` class provides the operator function `plus()`, which can be used to join two lists.

```kt
val firstList = listOf("One", "Two")
val secondList = listOf("Three")

// Using the plus method directly.

val plusResult = firstList.plus(secondList)

// Using the operator overloaded method.
val operatorResult = firstList + secondList
```

The `get` operator symbol allows bracket access:

```kt
operator fun String.get(range: IntRange) = substring(range)
val str = "A very long string"
println(str[0..5])
```

### Tail Recursive Functions

### Reification

## Scope Functions

The Kotlin standard library contains several functions whose purpose is to execute a block of code within the context of an object. When such a function is called on an object with a lambda expression provided, it forms a temporary scope. Within this scope, the object can access without its name. These functions do the same: execute a block of code on an object. Chosing the right one for your case can be a bit tricky, but difference between them is how the object is accessed inside the block and what the return value is. Scope functions do not introduce any new technical capabilities, but they can make code readable.

### let

`let` is an `extension function` that executes a code block and returns the result the last expression. The context object is accessed with the `lambda argument` `it`. The return values is the `lambda result`.

```kt
val isEmpty = "myString".let {
    customPrint(it)
    it.isEmpty()
}
```

`let` can also be used to invoke one or more functions on results of a call chains:

```kt
val numbers = listOf("one", "two", "three", "four", "five")
numbers.map { it.length }.filter { it > 3 }.let {
    println(it)
}
```

If the code block contains a single function with `it` as an argument, the method reference (::) can be used instead of a lambda function:

```kt
numbers.map { it.length }.filter { it > 3 }.let(::println)
```

`let` is often used for executing a code block only with non-null values:

```kt
val str: String? = "Hello"
// Uses safe call, so let and its code block will only be executed only for non-null values.
// Calling processString(it) outside of let would cause a compile error.
val length = str?.let {
    processString(it)
    it.length
}
```

### run

`run` is a `non-extension function` that executes a code block and returns the result the last expression. The context object is accessed with the `object reference` `this` which is useful when you want to call the object's methods instead of passing it as an argument. The return value is the `lambda result`.

```kt
val str: String? = "Hello"

val length = str?.run {
    if (isEmpty()) {
        // ...
    }
    length
}
```

`run` can also be called without the context object:

```kt
val email = run {
    val prefix = "my-email"
    val domain("mail.com")

    Email(prefix, domain)
}
```

### with

`with` is a `non-extension function` that can access members of its argument. The context object is passed as an argument and can be accessed with the `object reference` `this`. The return value is the `lambda result`, but the result can be omitted as `with` is commonly used to call functions on the context object without providing a result.

`with` can be read as "with this object, do ”:

```kt
val numbers = listOf(1, 2, 3)
with(numbers) {
    saveSum(sum())
}
```

Another use case for `with` is calculating variables:

```kt
val firstAndLast = with(numbers) {
    first() to last()
}
```

### apply

`apply` is an `extension function` that executes a code block and returns the context object itself. The context object is accessed with the `object reference` `this`. The function is handy for initializing objects, which is the common use case.

`apply` can be read as "apply the following assignments to the object”:

```kt
val person = Person()
    .apply {
        name = "Firstname Lastname"
        age = 30
    }
```

### also

also works like apply: it executes a given block and returns the object called. Inside the block, the object is referenced by it, so it's easier to pass it as an argument.

`also` is an `extension function` that returns the context object itself. `also` works like `apply`, but the context object is accessed with the `lambda argument` `it`, which makes it easier to pass on as an argument. `also` is handy for embedding additional actions:

```kt
val person = Person()
    .also {
        save(it)
    }
```

## Delegation

## Colletions

## Interfaces

## Classes

### Generics

### Accessors

### Inheritance

### Data Classes

### Enums

### Sealed Classes

## Objects

### Companion Objects

## Control Flow

### Comparison

## Stream Operations

### When

### Loops

### Ranges

### Conditional Expressions

### Equality Checks

## Collections

## Asynchronous Programming

### Threads

### Callbacks

### Futures

### Reactive Extensions

## Coroutines

## Builders (DSL)

## Conventions

Noteworthy conventions taken from [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html).

### File Conventions

- If a file contains a single class, or with related top-level declarations, the name file should be named after the class.
- If a file contains multiple classes, or only top-level declarations, choose a name describing what the file contains (use upper camel case with an uppercase first letter (e.g. ProcessDeclarations.kt).
- Placing multiple declarations (classes, top-level functions or properties) in the same Kotlin source file is encouraged as long as these declarations are closely related to each other.
- When defining extension functions for a class which are relevant for all clients of this class, put them in the same file with the class itself.
- When defining extension functions that make sense only for a specific client, put them next to the code of that client.
- Avoid creating files just to hold all extensions of some class.

### Class Conventions

The contents of a class should go in the following order:

1. Property declarations and initializer blocks
2. Secondary constructors
3. Method declarations
4. Companion object

- Always put overloads next to each other in a class.
- Do not separate regular methods from extension methods.
- Put nested classes next to the code that uses those classes.
- If the classes are intended to be used externally, put them in the end, after the companion objects.
- Use method names with spaces enclosed in backtick in tests classes (only).
- Place annotations on separate lines before the declaration to which they are attached,

Constants (properties marked with `const`, or top-level or object `val` properties should be uppercase and underscore-separated:

```kt
const val MAX_COUNT = 8
val USER_NAME_FIELD = "UserName"
```

Names of properties holding references to singleton objects can use the same naming style as object declarations:

```kt
val PersonComparator: Comparator<Person> = {}
```

Classes with few primary constructor parameters can be written in a single line:

```kt
class Person(id: Int, name: String)
```

Classes with multiple primary constructor parameters should have each parameter on a separate line:

```kt
class Person(
    id: Int,
    name: String,
    surname: String
) : Human(id, name) {}
```

Prefer using an expression body for functions with the body consisting of a single expression:

```kt
fun securityConfig() = mapOf(
    CommonClientConfigs.SECURITY_PROTOCOL_CONFIG to kafkaSecurityProtocol,
)
```

If the function has an expression body whose first line doesn't fit on the same line as the declaration, put the `=` sign on the first line and the expression body on the next line (indented):

```kt
fun f(x: String, y: String, z: String) =
    veryLongFunctionCallWithManyWords(andLongParametersToo(), x, y, z)
```

When using factory functions for classes, avoid giving it the same name as the class itself. Prefer using a name that describes the behavior of the factory function:

```kt
class Point(val x: Double, val y: Double) {

    companion object {
        fun fromPolar(angle: Double, radius: Double) = Point(x: angle, y: radius)
    }
}
```

### Code Conventions

- Use trailing commas after the last item in lists.
- Omit semicolons whenever possible.
- Use the named arguments when a method takes multiple parameters of the same primitive type or for Boolean parameters.

Prefer using the expression form of `try`, `if`, and `when`:

```kt
return when(x) {
    0 -> "zero"
    else -> "nonzero"
}
```

Prefer using `if` for binary conditions instead of `when`.
Prefer using `when` if there are three or more options.

### Lambda Conventions

- For short and not nested lambdas, use the `it` convention instead of declaring the parameter explicitly.
- Always declare parameters explicitly in nested lambdas with parameters.
- Avoid using multiple labeled returns in a lambda. Restructure lambdas to have a single exit point.

## String Conventions

Prefer string templates to string concatenation.
Prefer multiline strings to embedding `\n` escape sequences into regular string literals.

Extension Functions:

- Use extension functions liberally. Every time a function works primarily on an object, consider making it an extension function accepting that object as a receiver.
- Restrict the visibility of extension functions as much as it makes sense.
- As necessary, use local extension functions, member extension functions, or top-level extension functions with private visibility.

### Infix Conventions

- Do not declare a method as `infix` if it mutates the receiver object.
- Only declare a function as `infix` when it works on two objects which play a similar role. Typially `and` or `to` used when creating thing.

The below code creates a tuple of type `Pair` from this and `that`, useful for for creating Map literals:

```kt
public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)

val myMap = mapOf(
    key to value
)
```

## Idioms

Noteworthy idioms taken from [Frequently Used Kotlin Idioms](https://kotlinlang.org/docs/idioms.html).
