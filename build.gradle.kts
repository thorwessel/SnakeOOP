import com.soywiz.korge.gradle.*

plugins {
    `java-library`
}

buildscript {
    repositories {
        mavenLocal()
        maven { url = uri("https://dl.bintray.com/soywiz/soywiz") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
    }
    dependencies {
        classpath("com.soywiz:korge-gradle-plugin:1.2.0")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

apply(plugin = "korge")

korge {
    id = "com.sample.demo"
}
