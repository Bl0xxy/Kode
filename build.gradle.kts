plugins {
    kotlin("jvm") version "2.3.0"
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
