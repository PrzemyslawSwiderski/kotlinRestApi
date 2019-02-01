import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version Versions.kotlinVersion
    kotlin("plugin.allopen") version Versions.kotlinVersion
    kotlin("plugin.spring") version Versions.kotlinVersion
    id("org.springframework.boot") version Versions.springBootVersion
}

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}
tasks {
    withType(KotlinCompile::class.java).all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    jar {
        archiveClassifier.set("jar")
    }

    test {
        useJUnitPlatform()
    }
}
dependencies {
    implementation(kotlin("stdlib", Versions.kotlinVersion)) // Required for Kotlin integration
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.springfox:springfox-swagger2:${Versions.swagger}")
    implementation("io.springfox:springfox-swagger-ui:${Versions.swagger}")
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
}

allprojects {
    group = "com.pswiderski"
    version = "1.0.0"
}
