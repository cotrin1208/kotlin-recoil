plugins {
    kotlin("multiplatform") version "1.9.20"
    id("maven-publish")
    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.2.0"
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "io.github.cotrin1208"
version = "0.5.0"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        binaries.executable()
        browser()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.682")
                implementation(npm("recoil", "0.7.7"))
            }
        }
    }
}

kotlin {
    val publicationsFromMainHost = listOf(js()).map { it.name } + "kotlinMultiplatform"

    publishing {
        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>().matching {
                    it.publication == targetPublication
                }.configureEach { onlyIf { findProperty("isMainHost") == true } }
            }
        }

        repositories {
            maven {
                url = uri("https://asia-northeast2-maven.pkg.dev/cotrin-libs/kotlin-recoil")
                credentials {
                    username = "_json_key_base64"
                    password = System.getenv("ARTIFACT_REGISTRY_MAVEN_SECRET")
                }
                authentication {
                    create("basic", BasicAuthentication::class.java)
                }
            }
        }
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(file("build/dokka"))
}
