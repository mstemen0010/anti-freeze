-- ----------------------------------------------------------------------
-- MySQL Migration Toolkit
-- SQL Create Script
-- ----------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `AntiFrieze`
  CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `AntiFrieze`;
-- -------------------------------------
-- Tables

DROP TABLE IF EXISTS `AntiFrieze`.`Add`;
CREATE TABLE `AntiFrieze`.`Add` (
  `ADDCODE` VARCHAR(255) NULL,
  `DESC` VARCHAR(255) NULL,
  INDEX `ADDCODE` (`ADDCODE`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`JobLvl1`;
CREATE TABLE `AntiFrieze`.`JobLvl1` (
  `Lvl1Code` VARCHAR(2) NOT NULL,
  `Lvl2Code` VARCHAR(2) NOT NULL,
  `JobDesc` VARCHAR(50) NULL,
  PRIMARY KEY (`Lvl1Code`, `Lvl2Code`),
  INDEX `Lvl1Code` (`Lvl1Code`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Make`;
CREATE TABLE `AntiFrieze`.`Make` (
  `makeid` INT(10) NOT NULL AUTO_INCREMENT,
  `LVL12` VARCHAR(255) NULL,
  `MAKE` VARCHAR(80) NULL,
  PRIMARY KEY (`makeid`),
  INDEX `LVL12` (`LVL12`),
  INDEX `MAKE` (`MAKE`),
  INDEX `makeid` (`makeid`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`SalesTax`;
CREATE TABLE `AntiFrieze`.`SalesTax` (
  `TaxTypeId` INT(10) NOT NULL AUTO_INCREMENT,
  `TypeDesc` VARCHAR(50) NULL,
  `TaxRate` DOUBLE(7, 2) NULL,
  PRIMARY KEY (`TaxTypeId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Year`;
CREATE TABLE `AntiFrieze`.`Year` (
  `Year` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`Year`)
)
ENGINE = INNODB;



SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------------
-- EOF

