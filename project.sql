CREATE DATABASE coins;
USE coins;
CREATE USER 'coin'@'localhost' IDENTIFIED BY 'coin';
CREATE TABLE IF NOT EXISTS`account` (
           `name` varchar(36) NOT NULL,
           `coin` int DEFAULT 0,
            PRIMARY KEY (`name`)
)
