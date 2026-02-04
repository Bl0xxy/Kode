plugins {
    kotlin("jvm") version "2.3.0"
    `java-library`
    `maven-publish`
}

group = "io.bl0xxy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])   // now works
            artifactId = "kode"
        }
    }
    repositories {
        mavenLocal()
    }
}