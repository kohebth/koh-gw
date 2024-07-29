rootProject.name = "koh"
include("core", "auth", "mail", "datahub")
project(":core").projectDir = file("core")
project(":auth").projectDir = file("auth")
project(":mail").projectDir = file("mail")
project(":datahub").projectDir = file("datahub")
