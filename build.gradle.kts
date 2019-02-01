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
    jar {
        archiveClassifier.set("jar")
    }
}
dependencies {
    implementation(kotlin("stdlib", Versions.kotlinVersion)) // Required for Kotlin integration
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.springfox:springfox-swagger2:${Versions.swagger}")
    implementation("io.springfox:springfox-swagger-ui:${Versions.swagger}")
    implementation("com.natpryce:konfig:1.6.10.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
}

allprojects {
    group = "com.pswiderski"
    version = "1.0.0"
}
