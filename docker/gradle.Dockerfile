FROM gradle:8.1.1-jdk11 AS gradle-cache
LABEL authors="koh"

COPY projects/auth/build.gradle.kts auth/
COPY projects/auth/settings.gradle.kts auth/

COPY projects/core/build.gradle.kts core/
COPY projects/core/settings.gradle.kts core/

COPY projects/datahub/build.gradle.kts datahub/
COPY projects/datahub/settings.gradle.kts datahub/

COPY projects/mail/build.gradle.kts mail/
COPY projects/mail/settings.gradle.kts mail/

COPY projects/vps/build.gradle.kts vps/
COPY projects/vps/settings.gradle.kts vps/

COPY projects/settings.gradle.kts .

RUN gradle build --info --build-cache