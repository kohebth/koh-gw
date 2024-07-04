plugins {
    id("java")
    id("java-library")
}

group = "koh.core"
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

dependencies {

    implementation("org.eclipse.jetty:jetty-server:11.0.21")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.21")

    implementation("jakarta.ws.rs:jakarta.ws.rs-api:2.1.6")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-log4j12:2.0.13")

    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    implementation("org.slf4j:slf4j-api:1.7.32")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}



tasks.test {
    useJUnitPlatform()
}