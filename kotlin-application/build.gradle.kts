plugins {
    application
    kotlin("jvm") version "1.3.40"
}

repositories {
    jcenter()
}

val log4jVersion = "2.11.2"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}