plugins {
    kotlin("jvm") version "2.2.21"
    id("dev.zacsweers.metro") version "0.8.2"
}

group = "dev.jonnya"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}