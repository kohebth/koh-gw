plugins {
    id("java")
}

group = "koh.service"
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
    implementation(project(":core"))

    implementation("jakarta.mail:jakarta.mail-api:2.1.2")

    implementation("org.slf4j:slf4j-api:1.7.32")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}