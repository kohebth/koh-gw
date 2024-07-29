#!/bin/bash

#Copy script từ host lên docker
docker cp ./_CreateMySQLDatabase.sh mysql:./_CreateMySQLDatabase.sh

#Thay đổi mode hoạt động
docker exec -it mysql chmod +x ./_CreateMySQLDatabase.sh

#Chạy script trên docker
docker exec -it mysql ./_CreateMySQLDatabase.sh