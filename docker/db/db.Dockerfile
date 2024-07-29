FROM mariadb:10.6.8

COPY projects/datahub/src/main/resources/init/* /docker-entrypoint-initdb.d