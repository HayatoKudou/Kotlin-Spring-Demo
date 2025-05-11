plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.postgresql:postgresql:42.7.2")

    // OpenAPI準拠のAPI仕様書を生成する
    // docs URL: http://localhost:8081/v3/api-docs.yaml
    // swagger URL：http://localhost:8081/swagger-ui/index.html
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.8.0")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.8.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")

    compileOnly("jakarta.persistence:jakarta.persistence-api:3.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
