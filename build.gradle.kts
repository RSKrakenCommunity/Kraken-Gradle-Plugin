plugins {
    kotlin("jvm") version "1.6.10"
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.rshub.gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("KrakenPlugin") {
            id = "com.rshub.gradle"
            implementationClass = "com.rshub.gradle.KrakenGradlePlugin"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin"))
}

publishing {
    repositories {
        mavenLocal()
    }
}