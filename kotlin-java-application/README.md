# kotlin-java-application 

A minimal application mixing Java and [Kotlin](https://kotlinlang.org). Build with [Gradle](https://gradle.org/). Uses the [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) for the build.gradle.kts file.

`./gradle build` does not produce an executable Jar, but the application can be started with:

```
./gradlew run
```

Uses log4j2 for logging instead of the logger provided by Spring Boot.

## Resources
* [Kotlin Gradle Documentation](https://kotlinlang.org/docs/reference/using-gradle.html)
* [Mixing Java and Kotlin](https://www.jetbrains.com/help/idea/mixing-java-and-kotlin-in-one-project.html)
* [Kotlin Packages and Imports](https://kotlinlang.org/docs/tutorials/kotlin-for-py/packages-and-imports.html)
* [mixed-java-kotlin-hello-world](https://github.com/JetBrains/kotlin-examples/tree/master/gradle/mixed-java-kotlin-hello-world)
