rootProject.name = "koh"
include("core", "auth", "mail")
project(":core").projectDir = file("core")
project(":auth").projectDir = file("auth")
project(":mail").projectDir = file("mail")
