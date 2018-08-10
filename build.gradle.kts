plugins {
    java
}

group = "com.pawmot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.querydsl:querydsl-sql-codegen:4.2.1")

    implementation("org.postgresql:postgresql:42.2.4")
    implementation("org.testcontainers:postgresql:1.8.1")

    compileOnly("org.projectlombok:lombok:1.18.2")
    testCompileOnly("org.projectlombok:lombok:1.18.2")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
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

tasks.withType<Test> {
    useJUnitPlatform()
}