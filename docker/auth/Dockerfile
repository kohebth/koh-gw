FROM gradle:8.1.1-jdk11 AS GradleBuilder

LABEL authors="koh"

ARG USER_NAME=gradle
ARG USER_UID=1000
ARG USER_GID=$USER_UID

USER $USER_NAME
COPY auth /home/auth
COPY core /home/core
WORKDIR /home/$USER_NAME

RUN gradle build jar

FROM openjdk:11-jdk-slim-buster
