rootProject.name = "koh"
include("core", "auth", "mail", "datahub", "vps")
project(":core").projectDir = file("core")
project(":auth").projectDir = file("auth")
project(":mail").projectDir = file("mail")
project(":datahub").projectDir = file("datahub")
project(":vps").projectDir = file("vps")
