DROP DATABASE IF EXISTS springblog_db;
CREATE DATABASE IF NOT EXISTS springblog_db;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'europa';
GRANT ALL ON springblog_db.* TO 'admin'@'localhost';
USE springblog_db;
# CREATE TABLE IF NOT EXISTS users(
#     id BIGINT NOT NULL AUTO_INCREMENT,
#     username VARCHAR(255) NOT NULL,
#     email VARCHAR(255) NOT NULL,
#     password VARCHAR(255) NOT NULL,
#     PRIMARY KEY (id),
#     UNIQUE (username, email)
# );
