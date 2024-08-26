plugins {
    id("java")
    application
}

group = "koh.datahub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jetty:jetty-server:11.0.21")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.21")
    implementation("org.eclipse.jetty:jetty-security:11.0.21")
    implementation("org.eclipse.jetty:jetty-util:11.0.21")
    implementation("org.apache.kafka:kafka-clients:3.5.1")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-reload4j:2.0.13")

    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
}

application {
    mainClass.set("koh.service.gateway.App")
}



tasks.test {
    useJUnitPlatform()
}
