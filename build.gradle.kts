import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

group = "image"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

    manifest {
        attributes["Manifest-Version"] = "1.0"
        attributes["Main-Class"] = "AppKt"
    }
}