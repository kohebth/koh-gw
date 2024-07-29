import org.jooq.meta.jaxb.Configuration

plugins {
    application
    java
    id("nu.studer.jooq") version "7.1.1"
}

group = "koh.datahub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("koh.db.hub.DatabaseSourceFactoryImpl")
}

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.jooq:jooq:3.16.8")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")

    jooqGenerator("org.jooq:jooq-meta:3.16.8")
    jooqGenerator("org.jooq:jooq-codegen:3.16.8")
    jooqGenerator("org.mariadb.jdbc:mariadb-java-client:3.3.3")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbcConfig()
                generatorConfig()
            }
        }
    }
}

sourceSets {
    main {
        java.srcDir("src/generated")
        java.srcDir("src/main")
    }
}

fun Configuration.jdbcConfig() {
    val DB_URL: String = "jdbc:mariadb://localhost:3306/vps_management";
    val DB_USER: String = "root";
    val DB_PASSWORD: String = "dev@123";
    jdbc.apply {
        driver = "org.mariadb.jdbc.Driver"
        url = DB_URL// System.getenv("DB_URL")
        user = DB_USER//System.getenv("DB_USER")
        password = DB_PASSWORD //System.getenv("DB_PASSWORD")
    }
}

fun Configuration.generatorConfig() {
    generator.apply {
        name = "org.jooq.codegen.DefaultGenerator"
        database.apply {
            name = "org.jooq.meta.mariadb.MariaDBDatabase"
            inputSchema = "vps_management"
        }
        generate.apply {
            isDeprecated = false
            isRecords = false
            isImmutablePojos = false
            isPojos = true
            isDaos = true
            isFluentSetters = false
        }
        target.apply {
            packageName = "koh.db.hub"
            directory = "src/generated"
        }
        strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
    }
}