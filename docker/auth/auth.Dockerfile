FROM koh/gradle-base as gradle-builder

COPY projects/auth auth/
COPY projects/core core/
COPY projects/datahub datahub/
COPY projects/mail mail/
COPY projects/vps vps/

COPY projects/settings.gradle.kts .

RUN gradle auth:build --info --build-cache

FROM openjdk:11-jdk-slim-buster

COPY --from=gradle-builder /home/gradle/auth/build/distributions/auth-1.0-SNAPSHOT.tar /opt/dist.tar
WORKDIR /
RUN tar -xf /opt/dist.tar --strip-components=1
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=0.0.0.0:8081"
ENTRYPOINT [ "auth", "koh.service.auth.App" ]
