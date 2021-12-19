# learning-kotlin

Notes taken while learing [Kotlin](https://kotlinlang.org). Examples taken from [Kotlin by Example](https://play.kotlinlang.org/byExample/) and
[The Kotlin Documentation](https://kotlinlang.org/docs/home.html).





## Language Features

### This Expression

### String Templates

### Destructuring

### Smart Casts

### Null Safty

### Variables

## Functions

### Default Parameters

### Named Arguments

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

- let
- run
- with
- apply
- also

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
