-- CREATE DATABASE IF NOT EXISTS alfaform;
-- GRANT ALL ON alfaform.* TO 'user'@'localhost' IDENTIFIED BY 'password';
ALTER DATABASE alfaform
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE alfaform;
DROP TABLE IF EXISTS customers;
CREATE TABLE IF NOT EXISTS customers (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(50),
  password VARCHAR(50),
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  patronymic VARCHAR(50),
  birth_date VARCHAR(50),
  sex VARCHAR(50),
  address VARCHAR(50),
  inn VARCHAR(50)
) engine=InnoDB;
