-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.5.25a - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2016-01-08 00:18:11
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for generictest_1
CREATE DATABASE IF NOT EXISTS `generictest_1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `generictest_1`;


-- Dumping structure for table generictest_1.table1
CREATE TABLE IF NOT EXISTS `table1` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) DEFAULT NULL,
  `COGNOME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table generictest_1.table1: ~2 rows (approximately)
/*!40000 ALTER TABLE `table1` DISABLE KEYS */;
REPLACE INTO `table1` (`ID`, `NOME`, `COGNOME`) VALUES
	(1, 'daniele', 'peruzzi'),
	(2, 'anna', 'benedetti');
/*!40000 ALTER TABLE `table1` ENABLE KEYS */;


-- Dumping structure for table generictest_1.table2
CREATE TABLE IF NOT EXISTS `table2` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `INDIRIZZO` varchar(255) DEFAULT NULL,
  `CITTA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table generictest_1.table2: ~2 rows (approximately)
/*!40000 ALTER TABLE `table2` DISABLE KEYS */;
REPLACE INTO `table2` (`ID`, `INDIRIZZO`, `CITTA`) VALUES
	(3, 'VIA FIRENZE', 'ROMA'),
	(4, 'VIA ROMA', 'PRATO');
/*!40000 ALTER TABLE `table2` ENABLE KEYS */;


-- Dumping structure for view generictest_1.vista1
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `vista1` (
	`ID` INT(10) NOT NULL DEFAULT '0',
	`NOME` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`COGNOME` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;


-- Dumping structure for view generictest_1.vista1
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `vista1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` VIEW `vista1` AS SELECT * FROM TABLE1 ;


-- Dumping database structure for generictest_2
CREATE DATABASE IF NOT EXISTS `generictest_2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `generictest_2`;


-- Dumping structure for table generictest_2.table_gen_1
CREATE TABLE IF NOT EXISTS `table_gen_1` (
  `ID_GEN` int(10) NOT NULL AUTO_INCREMENT,
  `CODICE` varchar(10) DEFAULT NULL,
  `DESCRIZIONE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_GEN`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table generictest_2.table_gen_1: ~0 rows (approximately)
/*!40000 ALTER TABLE `table_gen_1` DISABLE KEYS */;
REPLACE INTO `table_gen_1` (`ID_GEN`, `CODICE`, `DESCRIZIONE`) VALUES
	(1, 'A', 'DES1'),
	(2, 'B', 'DES2');
/*!40000 ALTER TABLE `table_gen_1` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
