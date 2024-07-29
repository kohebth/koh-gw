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
    implementation("com.github.docker-java:docker-java:3.2.13")
//    implementation("com.github.docker-java:docker-java-core:3.4.0")
//    implementation("com.github.docker-java:docker-java-api:3.4.0")
//    implementation("com.github.docker-java:docker-java-transport-httpclient5:3.4.0")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
