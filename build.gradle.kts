import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    kotlin("jvm") version "1.4.21"
}

object Properties {
    const val jacksonVersion="2.12.0"
    const val jupiterVersion = "5.7.0"
    const val jvmVersion = "1.8"
    const val hamcrestVersion="2.2"
    const val mockkVersion="1.10.3"
    const val kotlinCoroutinesVersion="1.3.9"
}

allprojects {
    group = "com.example"
    version = "1.0.0"

    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = Properties.jvmVersion
        targetCompatibility = Properties.jvmVersion
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = Properties.jvmVersion
        }
    }
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    apply {
        plugin("java")
        plugin("idea")
        plugin("kotlin")
    }

    dependencies {
        implementation("com.fasterxml.jackson.core:jackson-databind:${Properties.jacksonVersion}")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Properties.kotlinCoroutinesVersion}")

        testImplementation("io.mockk:mockk:${Properties.mockkVersion}")
        testImplementation("org.junit.jupiter:junit-jupiter-api:${Properties.jupiterVersion}")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}
