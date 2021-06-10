import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.5.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.5.10" apply false
    kotlin("plugin.spring") version "1.5.10" apply false
    kotlin("plugin.jpa") version "1.5.10" apply false
    kotlin("kapt") version "1.5.10" apply false
    kotlin("plugin.allopen") version "1.5.10"
    id("application")
}

allOpen {
    //https://spring.io/guides/tutorials/spring-boot-kotlin/
    //persistence with JPA
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

application {
    mainClass.set("com.example.springinaction.tacos.TacoCloudCh6ApplicationKt")
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}
subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("io.spring.dependency-management")
    }
}
