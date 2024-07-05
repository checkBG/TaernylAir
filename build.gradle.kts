plugins {
    kotlin("jvm") version "2.0.0"
    id("org.jetbrains.kotlinx.atomicfu") version "0.25.0"
    application
}

application {
    mainClass = "com.learn.coroutines.FlightWatcherKt"
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-client-core:2.3.12")
    implementation("io.ktor:ktor-client-cio:2.3.12")
    implementation("org.slf4j:slf4j-api:1.6.1")
    implementation("org.slf4j:slf4j-simple:1.6.1")
    implementation("org.jetbrains.kotlinx:atomicfu:0.16.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}