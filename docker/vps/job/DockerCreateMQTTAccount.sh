#!/bin/bash

#Xác định vị trí đặt file mật khẩu trong docker
PASSWORD_FILE_PATH="/mosquitto/config/password.txt"

#Xac dinh docker nao dang chay mqtt
MQTT_CONTAINER_NAME=$(sudo docker container ls | awk '{printf("%s\n", $NF)}' | grep mosqui)
echo "MQTT Docker Name="$MQTT_CONTAINER_NAME

#Lựa chọn MQTT Account
read -p "Tên tài khoản MQTT? " ACCOUNT_NAME </dev/tty

#Lựa chọn MQTT Password
read -p "Mật khẩu truy cập MQTT? " ACCOUNT_PASS </dev/tty


# Tạo tài khoản trong file cấu hình
docker exec -it $MQTT_CONTAINER_NAME sh -c "echo $ACCOUNT_NAME:$ACCOUNT_PASS >> $PASSWORD_FILE_PATH"

# Mã hóa file mật khẩu
docker exec -it $MQTT_CONTAINER_NAME mosquitto_passwd -U $PASSWORD_FILE_PATH

#Hiển thị thông tin
docker exec -it $MQTT_CONTAINER_NAME cat $PASSWORD_FILE_PATH
PUBLIC_MQTT_PORT=$(sudo docker port $MQTT_CONTAINER_NAME | grep 0.0.0.0 | head -n1 | cut -d ':' -f2)
echo
echo -------------------------------------------------------------
echo Cách để truy cập vào mqtt   là    mqtt://dev.techlinkvn.com:$PUBLIC_MQTT_PORT
echo account: $ACCOUNT_NAME
echo password: $ACCOUNT_PASS
echo -------------------------------------------------------------

exit