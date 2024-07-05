#!/bin/bash

#===========================================================================
#    Tự động tách đuôi và chạy script 
# Xác định tên file đang chạy
MY_SHELL=$(readlink -f $0)

# Tìm chỉ số dòng chứa cụm từ phân tách SPLITTER
START_LINE=$(sed = $MY_SHELL | sed 'N;s/\n/\t/' |  sed -n 's/\(#SPLITTER\)/\1/p' | tail -n1 | awk '{print $1}')

# Tìm số dòng của file
NUM_LINE=$(wc -l $MY_SHELL | awk '{print $1}')

# Sinh tên file tạm
#tmpfile=$(mktemp ./script.XXXXXX)
tmpfile=./temp.sql

# Trích ra phần cuối file và đưa vào file tạm
tail -n$(($NUM_LINE - $START_LINE)) $MY_SHELL > $tmpfile
#=====================================================================================
#Chương trình chính

read -p "Tên tài khoản đăng nhập MySQL? " MySQLUserName </dev/tty
read -p "Mật khẩu đăng nhập MySQL? " MySQLPassword </dev/tty
read -p "Tên Database Name? " MySQLDatabaseName </dev/tty


#SPLITTER===========================================================================
#Xoa file
echo "" > $tmpfile

#Tao database name
echo "CREATE DATABASE $MySQLDatabaseName;" >> $tmpfile

#Tao username
echo "CREATE USER '$MySQLUserName'@'%' IDENTIFIED BY '$MySQLPassword';" >> $tmpfile

#Cấp quyền cho database này 
echo "GRANT ALL PRIVILEGES ON $MySQLDatabaseName. * TO '$MySQLUserName'@'%';" >> $tmpfile

mysql -uroot -p92a87b47d5c47ccbb5592cde3a207dd3 -h 127.0.0.1 -P 3306 < $tmpfile

#Thông báo chi tiết
echo "Done."
echo "--------------------------------------------------------------------"
echo "You can access to this database with the configuration as below:"
echo "IP: mysql.toolhub.app:3306"
echo "DBName: "$MySQLDatabaseName
echo "account: "$MySQLUserName
echo "password: "$MySQLPassword
echo "--------------------------------------------------------------------"
exit 

