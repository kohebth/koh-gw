#!/bin/bash

#Liệt kê các docker đang chạy
docker container ls | awk '{printf("%12s \t %30s \t%s\n", $1, $2,$NF)}'

#Lựa chọn Docker Container
read -p "Chọn container NAME sẽ tạo tài khoản: " CONTAINER_NAME </dev/tty

#Lựa chọn Docker Account
read -p "Tên tài khoản đăng nhập container? " ACCOUNT_NAME </dev/tty

# Tạo tài khoản trên máy container
docker exec -it $CONTAINER_NAME useradd $ACCOUNT_NAME -m -s /bin/bash
# Đặt mật khẩu cho account đó
docker exec -it $CONTAINER_NAME passwd $ACCOUNT_NAME
# Cài đặt để ghi log dòng lênh
docker exec -it $CONTAINER_NAME sh -c 'echo  export HISTTIMEFORMAT=\"%h/%d - %H:%M:%S \"  >> /home/'$ACCOUNT_NAME'/.bashrc'

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#~~ Cấp quyền tài khoản sudo
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
#Bo sung vao nhom Admin de co quyen sudo
docker exec -it $CONTAINER_NAME usermod -a -G sudo $ACCOUNT_NAME

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#~~ Tài khoản vật lý  cho phép ssh và winscp bằng cổng 22
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
read -p "Container $CONTAINER_NAME là sử dụng image httpd để làm website và Tài khoản $ACCOUNT_NAME có website riêng không? (y/N)" HaveWebsite </dev/tty
ROOT_DOCUMENT="/usr/local/apache2/htdocs"
case "$HaveWebsite" in
  "y" | "Y")
      #Tạo thư mục web trong thư mục của apache
      sudo docker exec -it $CONTAINER_NAME  mkdir -p $ROOT_DOCUMENT/$ACCOUNT_NAME
      #Chuyển thư mục home của tài khoản về chỗ mới
      sudo docker exec -it $CONTAINER_NAME usermod  -d $ROOT_DOCUMENT/$ACCOUNT_NAME $ACCOUNT_NAME
      # Gia nhập vào nhóm FTP để có thể copy file lên server qua giao thức này
      sudo docker exec -it $CONTAINER_NAME chown -R $ACCOUNT_NAME:$FTPGROUP $ROOT_DOCUMENT/$ACCOUNT_NAME
      #Thông báo ra màn hình console cho admin biết
      sudo docker exec -it $CONTAINER_NAME sh -c 'echo "Chao ban '$ACCOUNT_NAME'! Day la noi bat dau mot website"  >> '$ROOT_DOCUMENT'/'$ACCOUNT_NAME'/index.html'
      ;;
  *);;
esac      
echo "Kết thúc"