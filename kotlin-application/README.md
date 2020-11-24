# kotlin-application 

A [Kotlin](https://kotlinlang.org) JVM application build with [Gradle](https://gradle.org/). Uses the [Kotlin
DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) for the build.gradle.kts file.

`./gradle build` does not produce an executable Jar, but the application can be started with:

```
./gradlew run
```

To build an executable Jar, run `./gradle build` with one of the alternative build files:

Create an executable fat-jar file:

```
./gradlew -b buildfiles/uberjar.gradle.kts
```

Create an executable uber-jar file using the [Gradle Shadow Plugin](https://imperceptiblethoughts.com/shadow/):

```
./gradlew -b buildfiles/shadowjar.gradle.kts
```

Execute the Jar:

```
java -jar build/libs/kotlin-application.jar
```

## Resources

* [Creating a Fat Jar in Gradle](https://www.baeldung.com/gradle-fat-jar)
