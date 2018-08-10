-- CREATE DATABASE IF NOT EXISTS alfaform$$
-- GRANT ALL ON alfaform.* TO 'user'@'localhost' IDENTIFIED BY 'password'$$
ALTER DATABASE alfaform
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci$$

USE alfaform$$
DROP TABLE IF EXISTS customers$$
CREATE TABLE IF NOT EXISTS customers (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(70) NOT NULL CHECK (email <> ''),
  password VARCHAR(70) NOT NULL CHECK (password <> ''),
  first_name VARCHAR(70) NOT NULL CHECK (first_name <> ''),
  last_name VARCHAR(70) NOT NULL CHECK (last_name <> ''),
  patronymic VARCHAR(70),
  birth_date DATE NOT NULL,
  sex ENUM ('MALE','FEMALE') NOT NULL,
  address VARCHAR(200),
  inn VARCHAR(12) NOT NULL
--   inn BIGINT(12) ZEROFILL NOT NULL
) engine=InnoDB$$

CREATE TRIGGER inn_length BEFORE INSERT ON customers
FOR EACH ROW
  BEGIN
    DECLARE innLength integer;
    SET innLength = (SELECT CHAR_LENGTH(NEW.inn));
    IF (innLength ) <> 12 THEN
      SIGNAL SQLSTATE '45000' set message_text='Error: Inn length must be 12';
    END IF;
  END
  $$