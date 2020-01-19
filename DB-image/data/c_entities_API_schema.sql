SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS entityDB;
CREATE SCHEMA entityDB;
USE entityDB;

--
-- Table structure for table `cinema_entity`
--

CREATE TABLE IF NOT EXISTS `cinema_entity` (
  `cinema_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cinema_id`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `movie_entity`
--

CREATE TABLE IF NOT EXISTS `movie_entity` (
  `movie_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `release_date` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`movie_id`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `screening_entity`
--

CREATE TABLE IF NOT EXISTS `screening_entity` (
  `screening_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT UNSIGNED NOT NULL,
  `fk_cinema` INT UNSIGNED NOT NULL,
  `fk_movie` INT UNSIGNED NOT NULL,
  `time` VARCHAR(45) DEFAULT NULL,
  `room` VARCHAR(45) NOT NULL,
  `property` VARCHAR(45) NOT NULL,
  KEY `fk_screening_cinema_idx` (`fk_cinema`),
  KEY `fk_screening_movie_idx` (`fk_movie`),
  CONSTRAINT `fk_screening_cinema_idx` FOREIGN KEY (`fk_cinema`) REFERENCES `cinema_entity` (`cinema_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_screening_movie_idx` FOREIGN KEY (`fk_movie`) REFERENCES `movie_entity` (`movie_id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


