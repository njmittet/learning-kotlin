# kotlin-application 

A [Kotlin](https://kotlinlang.org) JVM application build with [Gradle](https://gradle.org/). Uses the [Kotlin
DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) for the build.gradle.kts file.

`./gradle build` does not produce an executable Jar, but the application can be started with:

```
./gradlew run
```

To build an executable Jar, run `./gradle build` with one of the alternative build files:

## Uberjar

Because of deprecations in Gradle, copy the Gradle build file to the main directory before building and running 
the application. 

```sh
cp ./buildfiles/uberjar.gradle.kts ./build.gradle.kts
./gradlew clean build
java -jar build/libs/kotlin-application.jar
```

## Shadow

Because of deprecations in Gradle, copy the Gradle build file to the main directory before building and running
the application.

```sh
cp ./buildfiles/shadowjar.gradle.kts ./build.gradle.kts
./gradlew clean build
./gradlew runShadow
```

## Resources

* [Creating a Fat Jar in Gradle](https://www.baeldung.com/gradle-fat-jar)
