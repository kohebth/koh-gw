plugins {
    id("java")
}

group = "koh.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.eclipse.jetty:jetty-server:11.0.14")
    implementation("org.eclipse.jetty:jetty-server:11.0.21")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.21")

    implementation("jakarta.ws.rs:jakarta.ws.rs-api:2.1.6")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-log4j12:2.0.13")

    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

//    implementation("org.slf4j:slf4j-simple:2.0.7")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

//    api("com.sun.faces:jsf-api:2.2.20")
//    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
//    implementation("org.apache.tomcat:tomcat-catalina:10.1.20")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}