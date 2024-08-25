FROM openjdk:11-jdk-slim-buster

ENV APP=gateway

RUN apt update
RUN apt install curl lsof procps -y

COPY build/distributions/${APP}-1.0-SNAPSHOT.tar /opt/dist.tar
COPY docker/key/https_keystore.p12 /root/.cert/https.p12

WORKDIR /
RUN tar -xf /opt/dist.tar
RUN mv ${APP}-1.0-SNAPSHOT/bin/* /bin
RUN mv ${APP}-1.0-SNAPSHOT/lib/* /lib
RUN rm /opt/dist.tar

ENTRYPOINT [ "gateway", "koh.service.gateway.App" ]
