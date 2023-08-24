# Resouces

[](https://typealias.com/concepts/function-reference/)
[](https://antonioleiva.com/function-references-kotlin/)
[](https://blog.jdriven.com/2019/10/kotlin-method-reference-to-companion-object-function/)

[](https://docs.w3cub.com/kotlin/docs/reference/lambdas)
[](https://kotlinlang.org/docs/lambdas.html)
[](https://typealias.com/start/kotlin-receivers-and-extensions/)

## Function Types

Kotlin uses function types or declarations that deal with functions. The notation  corresponds to the signatures of the functions, the functions parameters and return values:

```kt6
(Int) -> String
```

The function type notation can optionally include names for the function parameters:

```kt
(x: Int, y: Int) -> Point
```

Function types can optionally have an additional receiver type, which is specified before the dot in the notation:

```kt
// Represents functions that can be called on a receiver object A.
A.(B) -> C
```

With `suspend` mdifier:

```kt
suspend () -> Unit
```

To specify that a function type is nullable, wrap the function type in parentheses and add a question mark:

```kt
((Int, Int) -> Int)?
```

Function types can be combined:

```kt
(Int) -> ((Int) -> Unit)
```

### Higher-Order Functions

Kotlin functions are first-class, meaning they can be stored in variables and data structures, and can be passed as arguments to and returned from other higher-order functions. A higher-order function is a function that takes functions as parameters, or returns a function.

Taking a function type as a parameter:

```kt
// A function that returns the integer returned by the provided function.
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

// Declaring a function that sums two integers.
fun sum(x: Int, y: Int) = x + y

// Calling the higher-order function with sum as operation, references by name
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

### Lambda Functions

Lambda expressions and anonymous functions are function literals. Function literals are functions that are not declared but are passed immediately as an expression.

Lambda expression is always surrounded by curly braces and the last expression inside the lambda body is treated as the return value.

```kt
// The full syntactic form, with all explicit types, of a lambda expression.
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

// However, lambdas can be denoted very concisely in many cases thanks to type inference and the implicit "it" variable.
val upperCase: (String) -> String = { it.uppercase() }

// Function pointers can be used if the lambda consists of a single function call.
val upperCase: (String) -> String = String::uppercase

// If the last parameter of a function is a function, then a lambda expression can be placed outside 
// the parentheses, also known as a trailing lambda.
val sum = listOf(1, 2, 3).fold(1) { acc, e -> acc * e }

// If the lambda is the only argument in that call, the parentheses can be omitted entirely.
listOf("first,", "second").forEach { println(it.name.uppercase())
```

### Inline Functions

Using higher-order functions imposes a runtime penalty since each function is an object and it captures a closure. A closure is a scope of variables that can be accessed in the body of the function. Memory allocations and virtual calls causes the runtime overhead. In many cases this kind of overhead can be eliminated by inlining the lambda function.

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

It is also possible to inline properties (that don't have backing fields), or the properties accessors:

```kt
// Inline anb individual property accessor.
var bar: Foo
    get() = ...
    inline set(v) { ... }

// Inline the entire property, which marks both of its accessors as inline.
inline var bar: Bar
    get() = ...
    set(v) { ... }
```