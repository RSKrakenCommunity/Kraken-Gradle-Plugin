plugins {
    kotlin("jvm") version "1.5.31"
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
}

group = "com.rshub.gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("krakenPlugin") {
            id = "kraken.community.plugin"
            implementationClass = "com.rshub.gradle.KrakenGradlePlugin"
            description = "Gradle Configuration Plugin for Kraken Plugins"
            version = "1.0-SNAPSHOT"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    repositories {
        mavenLocal()
    }
}