plugins {
    java
    id("cup.gradle.cup-gradle-plugin") version "1.2"
    id("org.xbib.gradle.plugin.jflex") version "1.2.1"
}

group = "ch.cheorges"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("com.google.truth:truth:1.1.2")
}

tasks.register<JavaExec>("runStory") {
   classpath = sourceSets["main"].runtimeClasspath
   main = "ch.cheorges.Runner"
   args = listOf(System.getProperty("filePath") ?: throw MissingFileArgumentException())
}

class MissingFileArgumentException : RuntimeException("The required argument (-DfilePath=\"...\") was not passed.")
