import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.10"
}

repositories {
    jcenter()
}

java.sourceCompatibility = JavaVersion.VERSION_11

val log4jVersion = "2.14.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
}

application {
    mainClass.set("no.njm.HelloWorldKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "no.njm.HelloWorldKt"
        // Required to handle multi-release jars. In this case the log4j-* dependencies.
        attributes["Multi-Release"] = true
    }

    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })

    // Required in order to mute Gradle deprecation warning.
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
