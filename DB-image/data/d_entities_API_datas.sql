SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE entityDB;

--
-- Dumping data for tables screening_entity, cinema_entity and movie_entity
--

SET AUTOCOMMIT=0;
INSERT INTO `cinema_entity` (`name`, `city`, `price`) VALUES 
('Pathe Flon', 'Lausanne1', '20 CHF'),
('Pathe Flon2', 'Lausanne2', '21 CHF'),
('Pathe Flon3', 'Lausanne3', '22 CHF'),
('Pathe Flon4', 'Lausanne4', '23 CHF'),
('Pathe Flon5', 'Lausanne5', '24 CHF'),
('Pathe Flon6', 'Lausanne6', '25 CHF'),
('Pathe Flon7', 'Lausanne7', '26 CHF'),
('Pathe Flon8', 'Lausanne8', '27 CHF'),
('Pathe Flon9', 'Lausanne9', '28 CHF'),
('Pathe Flon10', 'Lausanne10', '29 CHF');

INSERT INTO `movie_entity` (`title`, `release_date`, `category`) VALUES 
('Hancock', '2008', 'Action'),
('Hancock2', '2009', 'Action1'),
('Hancock3', '2010', 'Action2'),
('Hancock4', '2011', 'Action3'),
('Hancock5', '2012', 'Action4'),
('Hancock6', '2013', 'Action5'),
('Hancock7', '2014', 'Action6'),
('Hancock8', '2015', 'Action7'),
('Hancock9', '2016', 'Action8'),
('Hancock10', '2017', 'Action9');

INSERT INTO `screening_entity` (`user_id`, `fk_cinema`, `fk_movie`, `time`, `room`, `property`) VALUES 
(1, 10, 1, '21:00', 'A01', '3D'),
(2, 9, 2, '20:00', 'B01', '2D'),
(1, 8, 3, '19:00', 'C01', '3D'),
(2, 7, 4, '18:00', 'D01', '2D'),
(1, 6, 5, '17:00', 'E01', '3D'),
(2, 5, 6, '16:00', 'F01', '2D'),
(1, 4, 7, '15:00', 'G01', '3D'),
(2, 3, 8, '14:00', 'H01', '2D'),
(1, 2, 9, '13:00', 'I01', '3D'),
(2, 1, 10, '12:00', 'J01', '2D');
COMMIT;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
