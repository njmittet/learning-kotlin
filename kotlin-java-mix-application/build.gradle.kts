plugins {
    application
    kotlin("jvm") version "1.3.40"
}

repositories {
    jcenter()
}


application {
    mainClassName = "no.njm.App"
}

val log4j2Version = "2.11.2"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.logging.log4j:log4j-api:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
}