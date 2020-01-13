SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE myDB;

--
-- Dumping data for table user
--

SET AUTOCOMMIT=0;
INSERT INTO user_entity VALUES
(1,'Olivier','Koffi','Oki','$2a$10$PrMAlhuNgb.R/Yx9KIzc1eGUX6d2H.1AsSguP1qUHXnybYXBMCYGG','oki@mail.com', 'admin'),
(2,'Nath','Miz','Nmiz','$2a$10$PrMAlhuNgb.R/Yx9KIzc1eGUX6d2H.1AsSguP1qUHXnybYXBMCYGG','nmi@mail.com', 'user');
COMMIT;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;