FROM gradle:8.1.1-jdk11 AS gradle-builder

LABEL authors="koh"

ARG USER_NAME=gradle
ARG USER_UID=1000
ARG USER_GID=$USER_UID

WORKDIR /home/$USER_NAME

COPY auth /home/gradle/auth
COPY core /home/gradle/core
COPY settings.gradle.kts /home/gradle/settings.gradle.kts

RUN chmod 777 auth
RUN chmod 777 core
RUN java -version
USER $USER_NAME

RUN gradle buildDependents
#
#FROM openjdk:11-jdk-slim-buster
#
#COPY --from=gradle-builder /home/gradle/auth/build/distributions/auth-1.0-SNAPSHOT.tar /opt/dist.tar
#WORKDIR /
#RUN tar -xf /opt/dist.tar --strip-components=1
#ENTRYPOINT [ "auth", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=0.0.0.0:8081", "koh.service.auth.App" ]
