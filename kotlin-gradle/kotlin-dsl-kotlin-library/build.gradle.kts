plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.40")
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
