plugins {
    application
    java
}

group = "koh"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("koh.service.vps.App")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":datahub"))

    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.jooq:jooq:3.16.8")
    implementation("org.jooq:jooq-meta:3.16.8")
    implementation("org.jooq:jooq-codegen:3.16.8")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
