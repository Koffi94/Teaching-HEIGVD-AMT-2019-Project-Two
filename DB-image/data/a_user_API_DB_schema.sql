SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS userDB;
CREATE SCHEMA userDB;
USE userDB;

--
-- Table structure for table `user_entity`
--

CREATE TABLE user_entity (
  user_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR(45) DEFAULT NULL,
  lastname VARCHAR(45) DEFAULT NULL,
  username VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(512) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  role VARCHAR(45) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
