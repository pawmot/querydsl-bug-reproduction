plugins {
    java
    id("org.springframework.boot") version "2.0.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

group = "com.pawmot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.querydsl:querydsl-sql-spring:4.2.1")
    implementation("com.querydsl:querydsl-sql-codegen:4.2.1")

    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql:42.2.4")
    implementation("org.testcontainers:postgresql:1.8.1")

    compileOnly("org.projectlombok:lombok:1.18.2")
    testCompileOnly("org.projectlombok:lombok:1.18.2")

    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.10.0")
}

java.sourceSets["main"].java {
    srcDir("src/gen/java")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.9"
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(arrayOf("--add-modules", "java.xml.bind,java.xml.ws,java.xml.ws.annotation"))
}

tasks.withType<Test> {
    jvmArgs?.addAll(arrayOf("--add-modules", "java.xml.bind,java.xml.ws,java.xml.ws.annotation"))
}
