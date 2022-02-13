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

### This Expression

### String Templates

### Destructuring

### Smart Casts

### Null Safty

### Variables

## Functions

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

Named arguments allows for ignoring a functions parameter order:

```kt
printMessageWithDefaultPrefix(prefix = "Log", message = "Hello")
```

### Function Return

An explicit return can be omitted if a function only has a single expression:

```kt
fun multiply(x: Int, y: Int) = x * y

A function returning a value must have an explicit return if the function body has more than a single expression:

```kt
fun sum(x: Int, y: Int): Int {
    return x + y
}
```

### Varargs

### Infix Functions

### Function Scope

- Local Functions
- Member Functions

### Generic Functions

### Tail Recursive Functions

## Operator Functions (Operator Overloading)

## Higher Order Functions

## Lambda Functions

## Extension Functions

### Extension Properties

## Inline Functions

### Non-local Return

### Reification

## Scope Functions

The Kotlin standard library contains several functions whose purpose is to execute a block of code within the context of an object. When such a function is called on an object with a lambda expression provided, it forms a temporary scope. Within this scope, the object can access without its name. These functions do the same: execute a block of code on an object. Choosing the right one for your case can be a bit tricky, but difference between them is how the object is accessed inside the block and what the return value is. Scope functions do not introduce any new technical capabilities, but they can make code readable.

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
person.apply {
    name = "Firstname Lastname"
    age = 30
}
```

### also

### Comparison

## Stream Operations

## Classes

### Inheritance

### Data Classes

### Enums

### Sealed Classes

## Objects

### Companion Objects

### Delegation

## Control Flow

### Jumps

### Labels

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
