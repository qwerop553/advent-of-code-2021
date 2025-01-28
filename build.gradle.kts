plugins {
    kotlin("jvm") version "1.8.0"
}

repositories {
    mavenCentral()
    jcenter()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
