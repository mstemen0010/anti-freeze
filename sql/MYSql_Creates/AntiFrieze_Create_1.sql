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

DROP TABLE IF EXISTS `AntiFrieze`.`AddOpt`;
CREATE TABLE `AntiFrieze`.`AddOpt` (
  `AddOptID` INT(10) NOT NULL AUTO_INCREMENT,
  `ShortDesc` VARCHAR(40) NULL,
  `ApplyTo` VARCHAR(40) NULL,
  `PctLaborUnder` DOUBLE(7, 2) NULL,
  `PctLaborOver` DOUBLE(7, 2) NULL,
  `AmtOver` DECIMAL(19, 4) NULL,
  `AmtUnder` DECIMAL(19, 4) NULL,
  `PctTotParts` DOUBLE(7, 2) NULL,
  `MinAmt` DECIMAL(19, 4) NULL,
  `MaxAmt` DECIMAL(19, 4) NULL,
  `IsTaxable` TINYINT(1) NOT NULL,
  `UseWhen` VARCHAR(3) NULL,
  `UseLabor` TINYINT(1) NOT NULL,
  `UsePartTot` TINYINT(1) NOT NULL,
  `UseMinAmt` TINYINT(1) NOT NULL,
  `UseMaxAmt` TINYINT(1) NOT NULL,
  PRIMARY KEY (`AddOptID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Aging`;
CREATE TABLE `AntiFrieze`.`Aging` (
  `StatementID` INT(10) NOT NULL AUTO_INCREMENT,
  `StatementNum` DOUBLE(15, 5) NULL,
  `YearMonth` INT(10) NULL,
  `CusID` INT(10) NULL,
  `ARNUM` VARCHAR(25) NULL,
  `CusLastName` VARCHAR(50) NULL,
  `CusFirstName` VARCHAR(20) NULL,
  `CusStreet1` VARCHAR(50) NULL,
  `CusCity` VARCHAR(30) NULL,
  `CusState` VARCHAR(20) NULL,
  `CusZip` VARCHAR(20) NULL,
  `PreviousDue` DECIMAL(19, 4) NULL,
  `TotalCharges` DECIMAL(19, 4) NULL,
  `TotalPayments` DECIMAL(19, 4) NULL,
  `BalanceDue` DECIMAL(19, 4) NULL,
  `AR30` DECIMAL(19, 4) NULL,
  `AR60` DECIMAL(19, 4) NULL,
  `AR90` DECIMAL(19, 4) NULL,
  `AR120` DECIMAL(19, 4) NULL,
  `ItemCount` INT(10) NULL,
  `ClosedDate` DATETIME NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`StatementID`),
  UNIQUE INDEX `StatementNum` (`StatementID`),
  INDEX `ARNUM` (`ARNUM`),
  INDEX `CusID` (`CusID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`ARItem`;
CREATE TABLE `AntiFrieze`.`ARItem` (
  `ARItemNUm` INT(10) NOT NULL AUTO_INCREMENT,
  `StatementNum` DOUBLE(15, 5) NULL,
  `YearMonth` INT(10) NULL,
  `Date` DATETIME NULL,
  `Description` VARCHAR(255) NULL,
  `InvoiceNum` INT(10) NULL,
  `Amount` DECIMAL(19, 4) NULL,
  `CusId` INT(10) NULL,
  `Closed` TINYINT(1) NOT NULL,
  `ARType` VARCHAR(7) NULL,
  `MgrID` INT(10) NULL,
  `EstNum` INT(10) NULL,
  PRIMARY KEY (`ARItemNUm`),
  UNIQUE INDEX `EstNum` (`EstNum`),
  INDEX `ARItemNUm` (`ARItemNUm`),
  INDEX `ARStatementNum` (`StatementNum`),
  INDEX `CusId` (`CusId`),
  INDEX `MonthYear` (`YearMonth`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Car`;
CREATE TABLE `AntiFrieze`.`Car` (
  `CarId` INT(10) NOT NULL AUTO_INCREMENT,
  `CusId` INT(10) NULL,
  `ModId` VARCHAR(8) NULL,
  `ModYear` VARCHAR(4) NULL,
  `Tag` VARCHAR(20) NULL,
  `Mileage` INT(10) NULL,
  `VIN` VARCHAR(50) NULL,
  `Truck` VARCHAR(14) NULL,
  `CarDesc` VARCHAR(65) NULL,
  `EngDesc` VARCHAR(50) NULL,
  `EngCode` VARCHAR(8) NULL,
  `CarNotInSys` INT(10) NULL,
  `Make` VARCHAR(50) NULL,
  `Model` VARCHAR(50) NULL,
  `AE_CusId` INT(10) NULL,
  `AE_CarId` INT(10) NULL,
  PRIMARY KEY (`CarId`),
  INDEX `AE_CarId` (`AE_CarId`),
  INDEX `AE_CusId` (`AE_CusId`),
  INDEX `CarId` (`CarId`),
  INDEX `CusId` (`CusId`),
  INDEX `EngCode` (`EngCode`),
  INDEX `ModId` (`ModId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Comment`;
CREATE TABLE `AntiFrieze`.`Comment` (
  `ComNum` INT(10) NOT NULL AUTO_INCREMENT,
  `ShortDesc` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Rank` SMALLINT(5) NULL,
  `Group` VARCHAR(15) NULL,
  PRIMARY KEY (`ComNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Cus`;
CREATE TABLE `AntiFrieze`.`Cus` (
  `CusID` INT(10) NOT NULL AUTO_INCREMENT,
  `CusLastName` VARCHAR(50) NULL,
  `CusFirstName` VARCHAR(20) NULL,
  `CusMI` VARCHAR(3) NULL,
  `CusOldName` VARCHAR(20) NULL,
  `CusStreet1` VARCHAR(50) NULL,
  `CusStreet2` VARCHAR(50) NULL,
  `CusCity` VARCHAR(30) NULL,
  `CusState` VARCHAR(20) NULL,
  `CusZip` VARCHAR(20) NULL,
  `CusHome` VARCHAR(30) NULL,
  `CusWork` VARCHAR(30) NULL,
  `CusCell` VARCHAR(30) NULL,
  `CusFax` VARCHAR(30) NULL,
  `CusNotes` LONGTEXT NULL,
  `CusLastVisit` DATETIME NULL,
  `AE_CusId` SMALLINT(5) NULL,
  `Mail` TINYINT(1) NOT NULL,
  `Remind` SMALLINT(5) NULL,
  `ARNum` VARCHAR(25) NULL,
  `ARStatus` VARCHAR(25) NULL,
  `ARNow` TINYINT(1) NOT NULL,
  `AREver` TINYINT(1) NOT NULL,
  `ARDate` DATETIME NULL,
  `BalanceDue` DECIMAL(19, 4) NULL,
  `PreviousDue` DECIMAL(19, 4) NULL,
  `AR30` DECIMAL(19, 4) NULL,
  `AR60` DECIMAL(19, 4) NULL,
  `AR90` DECIMAL(19, 4) NULL,
  `AR120` DECIMAL(19, 4) NULL,
  `Contact` VARCHAR(20) NULL,
  `NonTaxable` TINYINT(1) NOT NULL,
  `Limit` DECIMAL(19, 4) NULL,
  `Company` TINYINT(1) NOT NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`CusID`),
  INDEX `AE_CusId` (`AE_CusId`),
  INDEX `ARNum` (`ARNum`),
  INDEX `CusID` (`CusID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`EstAddOn`;
CREATE TABLE `AntiFrieze`.`EstAddOn` (
  `EstAddOnId` INT(10) NOT NULL AUTO_INCREMENT,
  `EstNum` INT(10) NULL,
  `EstItemNum` INT(10) NULL,
  `IsChecked` TINYINT(1) NOT NULL,
  `ShortDesc` VARCHAR(40) NULL,
  `ApplyTo` VARCHAR(50) NULL,
  `Amt` DECIMAL(19, 4) NULL,
  `AddOptId` INT(10) NULL,
  `UseWhen` VARCHAR(3) NULL,
  `BasePartId` INT(10) NULL,
  `IsTaxable` TINYINT(1) NOT NULL,
  PRIMARY KEY (`EstAddOnId`),
  INDEX `AddOptId` (`AddOptId`),
  INDEX `EstAddOnId` (`EstAddOnId`),
  INDEX `EstItemNum` (`EstItemNum`),
  INDEX `EstNum` (`EstNum`),
  INDEX `PartId` (`BasePartId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Estimate`;
CREATE TABLE `AntiFrieze`.`Estimate` (
  `EstNum` INT(10) NOT NULL AUTO_INCREMENT,
  `EstDesc` VARCHAR(50) NULL,
  `EstDate` DATETIME NULL,
  `Make` VARCHAR(50) NULL,
  `Model` VARCHAR(50) NULL,
  `ModId` VARCHAR(8) NULL,
  `ModYear` VARCHAR(4) NULL,
  `PayType` VARCHAR(20) NULL,
  `SalesTaxAmt` DECIMAL(19, 4) NULL,
  `SalesTaxTypeId` INT(10) NULL,
  `CarDesc` VARCHAR(65) NULL,
  `CarNotInSys` INT(10) NULL,
  `EngDesc` VARCHAR(50) NULL,
  `EngCode` VARCHAR(8) NULL,
  `Tag` VARCHAR(20) NULL,
  `Mileage` INT(10) NULL,
  `VIN` VARCHAR(50) NULL,
  `Truck` VARCHAR(14) NULL,
  `MgrID` INT(10) NULL,
  `CusID` INT(10) NULL,
  `TaxExempt` SMALLINT(5) NULL,
  `Total` DECIMAL(19, 4) NULL,
  `PartsTotal` DECIMAL(19, 4) NULL,
  `LaborTotal` DECIMAL(19, 4) NULL,
  `MiscTotal` DECIMAL(19, 4) NULL,
  `TaxMisc` DECIMAL(19, 4) NULL,
  `CarId` INT(10) NULL,
  `UserDesc` VARCHAR(60) NULL,
  `LastName` VARCHAR(50) NULL,
  `FirstName` VARCHAR(25) NULL,
  `MI` VARCHAR(3) NULL,
  `Street1` VARCHAR(50) NULL,
  `Street2` VARCHAR(50) NULL,
  `City` VARCHAR(35) NULL,
  `State` VARCHAR(3) NULL,
  `Zip` VARCHAR(20) NULL,
  `Home` VARCHAR(50) NULL,
  `Work` VARCHAR(50) NULL,
  `Cell` VARCHAR(50) NULL,
  `Fax` VARCHAR(50) NULL,
  `PrintedEstNum` INT(10) NULL,
  `UseFLFooter` INT(10) NULL,
  `TicketType` VARCHAR(15) NULL,
  `AE_CarId` INT(10) NULL,
  `AE_CusId` INT(10) NULL,
  `Notes` LONGTEXT NULL,
  `Extra` INT(10) NULL,
  PRIMARY KEY (`EstNum`),
  INDEX `AE_CarId` (`AE_CarId`),
  INDEX `AE_CusId` (`AE_CusId`),
  INDEX `CusID` (`CusID`),
  INDEX `EngCode` (`EngCode`),
  INDEX `EstDesc` (`EstDesc`),
  INDEX `EstId` (`CarId`),
  INDEX `Extra` (`Extra`),
  INDEX `MgrID` (`MgrID`),
  INDEX `ModId` (`ModId`),
  INDEX `PrintedEstNum` (`PrintedEstNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`EstItem`;
CREATE TABLE `AntiFrieze`.`EstItem` (
  `EstItemNum` INT(10) NOT NULL AUTO_INCREMENT,
  `EstNum` INT(10) NULL,
  `JobId` VARCHAR(4) NULL,
  `PartNo` VARCHAR(50) NULL,
  `PartId` INT(10) NULL,
  `JobDesc` VARCHAR(255) NULL,
  `Hours` DOUBLE(15, 5) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `LabRate` DECIMAL(19, 4) NULL,
  `ShopTime` INT(10) NULL,
  `HiTime` DOUBLE(15, 5) NULL,
  `AvgTime` DOUBLE(15, 5) NULL,
  `LoTime` DOUBLE(15, 5) NULL,
  `Total` DECIMAL(19, 4) NULL,
  `IsTaxable` INT(10) NULL,
  `TaxRate` DOUBLE(15, 5) NULL,
  `InvType` VARCHAR(20) NULL,
  `General` VARCHAR(1) NULL,
  `AE_InvId` INT(10) NULL,
  `Tech1` VARCHAR(15) NULL,
  `Tech2` VARCHAR(15) NULL,
  `TechSplit` SMALLINT(5) NULL,
  `CostEach` DECIMAL(19, 4) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `pprofit` DOUBLE(7, 2) NULL,
  `Ignore` TINYINT(1) NOT NULL,
  `MarkupNo` TINYINT(1) NOT NULL,
  `Stock` TINYINT(1) NOT NULL,
  `Stkcount` DOUBLE(15, 5) NULL,
  PRIMARY KEY (`EstItemNum`),
  INDEX `AE_ItemId` (`AE_InvId`),
  INDEX `EstNum` (`EstNum`),
  INDEX `JobId` (`JobId`),
  INDEX `PartId` (`PartId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Events`;
CREATE TABLE `AntiFrieze`.`Events` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Date` DATETIME NULL,
  `Type` VARCHAR(255) NULL,
  `Details` VARCHAR(255) NULL,
  `Partnumber` VARCHAR(50) NULL,
  `Descriptold` VARCHAR(255) NULL,
  `Descriptnew` VARCHAR(255) NULL,
  `Countold` DOUBLE(15, 5) NULL,
  `Countnew` DOUBLE(15, 5) NULL,
  `Vendorold` VARCHAR(50) NULL,
  `Vendornew` VARCHAR(50) NULL,
  `Locold` VARCHAR(50) NULL,
  `Locnew` VARCHAR(50) NULL,
  `Mstklold` DOUBLE(15, 5) NULL,
  `Mstklnew` DOUBLE(15, 5) NULL,
  `Costold` DECIMAL(19, 4) NULL,
  `Costnew` DECIMAL(19, 4) NULL,
  `Priceold` DECIMAL(19, 4) NULL,
  `Pricenew` DECIMAL(19, 4) NULL,
  `Markupold` TINYINT(1) NOT NULL,
  `Markupnew` TINYINT(1) NOT NULL,
  `Coreold` TINYINT(1) NOT NULL,
  `Corenew` TINYINT(1) NOT NULL,
  `Notes1` VARCHAR(255) NULL,
  `Notes2` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`),
  INDEX `Descriptnew` (`Descriptnew`),
  INDEX `TransactionDate` (`Date`),
  INDEX `TransactionsDescription` (`Descriptold`),
  INDEX `TransactionsPartnumber` (`Partnumber`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`ItemTemp`;
CREATE TABLE `AntiFrieze`.`ItemTemp` (
  `EstItemNum` INT(10) NOT NULL AUTO_INCREMENT,
  `EstNum` INT(10) NULL,
  `JobId` VARCHAR(4) NULL,
  `PartNo` VARCHAR(50) NULL,
  `PartId` INT(10) NULL,
  `JobDesc` VARCHAR(255) NULL,
  `Hours` DOUBLE(15, 5) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `LabRate` DECIMAL(19, 4) NULL,
  `ShopTime` INT(10) NULL,
  `HiTime` DOUBLE(15, 5) NULL,
  `AvgTime` DOUBLE(15, 5) NULL,
  `LoTime` DOUBLE(15, 5) NULL,
  `Total` DECIMAL(19, 4) NULL,
  `IsTaxable` INT(10) NULL,
  `TaxRate` DOUBLE(15, 5) NULL,
  `InvType` VARCHAR(20) NULL,
  `General` VARCHAR(1) NULL,
  `AE_InvId` INT(10) NULL,
  `Tech1` VARCHAR(15) NULL,
  `Tech2` VARCHAR(15) NULL,
  `TechSplit` SMALLINT(5) NULL,
  `CostEach` DECIMAL(19, 4) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `pprofit` DOUBLE(7, 2) NULL,
  `Ignore` TINYINT(1) NOT NULL,
  `MarkupNo` TINYINT(1) NOT NULL,
  `Stock` TINYINT(1) NOT NULL,
  `Stkcount` DOUBLE(15, 5) NULL,
  PRIMARY KEY (`EstItemNum`),
  INDEX `AE_ItemId` (`AE_InvId`),
  INDEX `EstNum` (`EstNum`),
  INDEX `JobId` (`JobId`),
  INDEX `PartId` (`PartId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`JobPart`;
CREATE TABLE `AntiFrieze`.`JobPart` (
  `JOBPARTID` INT(10) NOT NULL AUTO_INCREMENT,
  `JOBID` VARCHAR(8) NULL,
  `PARTID` INT(10) NULL,
  `PARTNO` VARCHAR(50) NULL,
  PRIMARY KEY (`JOBPARTID`),
  INDEX `JOBID` (`JOBID`),
  INDEX `PARTID` (`PARTID`),
  INDEX `PARTNO1` (`PARTNO`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Jobs`;
CREATE TABLE `AntiFrieze`.`Jobs` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NULL,
  `Jobid` VARCHAR(50) NULL,
  PRIMARY KEY (`ID`),
  INDEX `Jobid` (`Jobid`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`LaborRates`;
CREATE TABLE `AntiFrieze`.`LaborRates` (
  `LaborId` SMALLINT(5) NOT NULL,
  `Description` VARCHAR(20) NULL,
  `Rate` DECIMAL(19, 4) NULL,
  `Viewable` TINYINT(1) NOT NULL,
  PRIMARY KEY (`LaborId`),
  INDEX `LaborId` (`LaborId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`letterlist`;
CREATE TABLE `AntiFrieze`.`letterlist` (
  `LIndex` INT(10) NOT NULL,
  `LetterNum` INT(10) NOT NULL,
  `ShortDesc` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `AddFactor` INT(10) NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`LetterNum`),
  INDEX `MgrID` (`MgrID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`letters`;
CREATE TABLE `AntiFrieze`.`letters` (
  `LetterId` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `Type` VARCHAR(15) NULL,
  `CusId` INT(10) NULL,
  `LetterNum` INT(10) NULL,
  `Created` DATETIME NULL,
  `SetDate` DATETIME NULL,
  `Completed` DATETIME NULL,
  `AddFactor` SMALLINT(5) NULL,
  `MgrID` INT(10) NULL,
  `BatchName` VARCHAR(30) NULL,
  PRIMARY KEY (`LetterId`),
  INDEX `CusId` (`CusId`),
  INDEX `MgrID` (`MgrID`),
  INDEX `RemindNum` (`LetterNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`lettertemp`;
CREATE TABLE `AntiFrieze`.`lettertemp` (
  `TempId` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `LetterID` INT(10) NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Priority` SMALLINT(5) NULL,
  `Type` VARCHAR(15) NULL,
  `CusId` INT(10) NULL,
  `LetterNum` INT(10) NULL,
  `Created` DATETIME NULL,
  `SetDate` DATETIME NULL,
  `Completed` DATETIME NULL,
  `AddFactor` INT(10) NULL,
  `Status` INT(10) NULL,
  `Rank` INT(10) NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`TempId`),
  INDEX `CusId` (`CusId`),
  INDEX `MgrID` (`MgrID`),
  INDEX `RemindID` (`LetterID`),
  INDEX `RemindNum` (`LetterNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Location`;
CREATE TABLE `AntiFrieze`.`Location` (
  `LocID` INT(10) NOT NULL AUTO_INCREMENT,
  `ShortDesc` VARCHAR(15) NULL,
  `LongDesc` VARCHAR(25) NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Rank` SMALLINT(5) NULL,
  `Group` VARCHAR(15) NULL,
  PRIMARY KEY (`LocID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Mail`;
CREATE TABLE `AntiFrieze`.`Mail` (
  `TempId` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `Type` VARCHAR(15) NULL,
  `CarId` INT(10) NULL,
  `CarDesc` VARCHAR(80) NULL,
  `CusId` INT(10) NULL,
  `CusName` VARCHAR(60) NULL,
  `CusLastName` VARCHAR(50) NULL,
  `IDNum` INT(10) NULL,
  `Created` DATETIME NULL,
  `SetDate` DATETIME NULL,
  `Completed` DATETIME NULL,
  `MgrID` INT(10) NULL,
  `POrder` INT(10) NULL,
  PRIMARY KEY (`TempId`),
  INDEX `CarId` (`CarId`),
  INDEX `CusId` (`CusId`),
  INDEX `MgrID` (`MgrID`),
  INDEX `RemindNum` (`IDNum`)
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

DROP TABLE IF EXISTS `AntiFrieze`.`MgrRate`;
CREATE TABLE `AntiFrieze`.`MgrRate` (
  `MgrID` INT(10) NOT NULL,
  `CompanyName` VARCHAR(50) NULL,
  `Street1` VARCHAR(50) NULL,
  `Street2` VARCHAR(50) NULL,
  `City` VARCHAR(50) NULL,
  `State` VARCHAR(50) NULL,
  `Zip` VARCHAR(50) NULL,
  `Phone` VARCHAR(50) NULL,
  `Additional` VARCHAR(50) NULL,
  `DfOption` TINYINT(1) NOT NULL,
  `DfCity` VARCHAR(50) NULL,
  `DfState` VARCHAR(50) NULL,
  `DfZip` VARCHAR(20) NULL,
  `DfHome` VARCHAR(5) NULL,
  `DfWork` VARCHAR(5) NULL,
  `DfCell` VARCHAR(5) NULL,
  `LaborRate` DECIMAL(19, 4) NULL,
  `DisplayOption` INT(10) NULL,
  `QBVersion` INT(10) NULL,
  `PrintSetupOption` SMALLINT(5) NULL,
  `ShowMenuOption` SMALLINT(5) NULL,
  `Footer` LONGTEXT NULL,
  `FLFooter` LONGTEXT NULL,
  `UseHoursRpt` INT(10) NULL,
  `TaxOption` INT(10) NULL,
  `TaxRate` DOUBLE(15, 5) NULL,
  `UseFLFooter` INT(10) NULL,
  `NextBackupSeqNum` INT(10) NULL,
  `LastBackupDate` DATETIME NULL,
  `DBVersion` VARCHAR(10) NULL,
  `ShowPartsOption` DOUBLE(7, 2) NULL,
  `TTon` TINYINT(1) NOT NULL,
  `TTITEMIZE` TINYINT(1) NOT NULL,
  `TTParts` TINYINT(1) NOT NULL,
  `TTPlus` TINYINT(1) NOT NULL,
  `PTPlus` TINYINT(1) NOT NULL,
  `UsePartsOption` TINYINT(1) NOT NULL,
  `TYAMOUNT` INT(10) NULL,
  `Flag1` INT(10) NULL,
  `Flag2` TINYINT(1) NOT NULL,
  `Flag3` TINYINT(1) NOT NULL,
  `Flag4` TINYINT(1) NOT NULL,
  `Flag5` TINYINT(1) NOT NULL,
  `Flag6` TINYINT(1) NOT NULL,
  `Flag7` TINYINT(1) NOT NULL,
  `Flag8` TINYINT(1) NOT NULL,
  `Flag9` TINYINT(1) NOT NULL,
  `Flag0` TINYINT(1) NOT NULL,
  `FlagA` TINYINT(1) NOT NULL,
  `FlagB` TINYINT(1) NOT NULL,
  `FlagC` TINYINT(1) NOT NULL,
  `FlagD` TINYINT(1) NOT NULL,
  `FlagE` TINYINT(1) NOT NULL,
  `FlagF` TINYINT(1) NOT NULL,
  `FlagG` TINYINT(1) NOT NULL,
  `FlagH` TINYINT(1) NOT NULL,
  `FlagI` TINYINT(1) NOT NULL,
  `FlagJ` TINYINT(1) NOT NULL,
  `FlagK` TINYINT(1) NOT NULL,
  `FlagL` TINYINT(1) NOT NULL,
  `FlagM` TINYINT(1) NOT NULL,
  `FlagN` TINYINT(1) NOT NULL,
  `FlagO` TINYINT(1) NOT NULL,
  `FlagP` TINYINT(1) NOT NULL,
  `FlagQ` TINYINT(1) NOT NULL,
  `FlagR` TINYINT(1) NOT NULL,
  `FlagS` TINYINT(1) NOT NULL,
  `FlagT` TINYINT(1) NOT NULL,
  `FlagU` TINYINT(1) NOT NULL,
  `FlagV` TINYINT(1) NOT NULL,
  `FlagW` TINYINT(1) NOT NULL,
  `FlagX` TINYINT(1) NOT NULL,
  `FlagY` TINYINT(1) NOT NULL,
  `FlagZ` TINYINT(1) NOT NULL,
  `Copies` INT(10) NULL,
  `histsort` INT(10) NULL,
  `comsort` SMALLINT(5) NULL,
  `pjsort` SMALLINT(5) NULL,
  `crsort` SMALLINT(5) NULL,
  `ivsort` SMALLINT(5) NULL,
  `Float1` DOUBLE(15, 5) NULL,
  `StringVal1` VARCHAR(50) NULL,
  `StringVal2` VARCHAR(50) NULL,
  `DateFrom` DATETIME NULL,
  `DateTo` DATETIME NULL,
  `Date1` DATETIME NULL,
  `Date2` DATETIME NULL,
  `AMPon` TINYINT(1) NOT NULL,
  `AMPsingle` TINYINT(1) NOT NULL,
  `AMPTL` DECIMAL(19, 4) NULL,
  `AMPTM` DOUBLE(7, 2) NULL,
  `AMPBM` DOUBLE(7, 2) NULL,
  `AMPCount` DOUBLE(7, 2) NULL,
  `AMPL0` DECIMAL(19, 4) NULL,
  `AMPL1` DECIMAL(19, 4) NULL,
  `AMPL2` DECIMAL(19, 4) NULL,
  `AMPL3` DECIMAL(19, 4) NULL,
  `AMPL4` DECIMAL(19, 4) NULL,
  `AMPL5` DECIMAL(19, 4) NULL,
  `AMPM0` DOUBLE(7, 2) NULL,
  `AMPM1` DOUBLE(7, 2) NULL,
  `AMPM2` DOUBLE(7, 2) NULL,
  `AMPM3` DOUBLE(7, 2) NULL,
  `AMPM4` DOUBLE(7, 2) NULL,
  `AMPM5` DOUBLE(7, 2) NULL,
  `IVStartDate` DATETIME NULL,
  `IVAMP` DOUBLE(7, 2) NULL,
  `IVCoreOn` TINYINT(1) NOT NULL,
  `IVAMPOn` TINYINT(1) NOT NULL,
  `IVAMPsingle` TINYINT(1) NOT NULL,
  `IVAMPTL` DECIMAL(19, 4) NULL,
  `IVAMPTM` DOUBLE(7, 2) NULL,
  `IVAMPBM` DOUBLE(7, 2) NULL,
  `IVAMPCount` DOUBLE(7, 2) NULL,
  `IVAMPL0` DECIMAL(19, 4) NULL,
  `IVAMPL1` DECIMAL(19, 4) NULL,
  `IVAMPL2` DECIMAL(19, 4) NULL,
  `IVAMPL3` DECIMAL(19, 4) NULL,
  `IVAMPL4` DECIMAL(19, 4) NULL,
  `IVAMPL5` DECIMAL(19, 4) NULL,
  `IVAMPM0` DOUBLE(7, 2) NULL,
  `IVAMPM1` DOUBLE(7, 2) NULL,
  `IVAMPM2` DOUBLE(7, 2) NULL,
  `IVAMPM3` DOUBLE(7, 2) NULL,
  `IVAMPM4` DOUBLE(7, 2) NULL,
  `IVAMPM5` DOUBLE(7, 2) NULL,
  `IVSuggestL` DOUBLE(7, 2) NULL,
  `ARDfAcctNum` INT(10) NULL,
  `ARDfCreditLimit` DECIMAL(19, 4) NULL,
  `ARFooter` LONGTEXT NULL,
  PRIMARY KEY (`MgrID`),
  INDEX `MgrID` (`MgrID`),
  INDEX `NextBackupSeqNum` (`NextBackupSeqNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`NewPart`;
CREATE TABLE `AntiFrieze`.`NewPart` (
  `PartId` INT(10) NOT NULL AUTO_INCREMENT,
  `PartNo` VARCHAR(50) NULL,
  `BasePartNo` VARCHAR(50) NULL,
  `AltPartNo` VARCHAR(50) NULL,
  `PartDesc` VARCHAR(128) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `QtyOnHand` INT(10) NULL,
  `VendorId` VARCHAR(50) NULL,
  `General` VARCHAR(1) NULL,
  `Note` LONGTEXT NULL,
  `LastUpdated` DATETIME NULL,
  `ModCode` VARCHAR(10) NULL,
  `JobId` VARCHAR(10) NULL,
  `Year` VARCHAR(4) NULL,
  `EngCode` VARCHAR(10) NULL,
  `IsChecked` TINYINT(1) NOT NULL,
  `Brand` VARCHAR(20) NULL,
  `BasePartId` INT(10) NULL,
  `coreprice` DECIMAL(19, 4) NULL,
  `LocationId` INT(10) NULL,
  `Stocked` TINYINT(1) NOT NULL,
  `MinQty` INT(10) NULL,
  `MaxQty` INT(10) NULL,
  `Units` VARCHAR(10) NULL,
  `APIAANumber` VARCHAR(40) NULL,
  PRIMARY KEY (`PartId`),
  INDEX `AltPartNum` (`AltPartNo`),
  INDEX `BasePartId` (`BasePartId`),
  INDEX `EngCode` (`EngCode`),
  INDEX `General` (`General`),
  INDEX `IsChecked` (`IsChecked`),
  INDEX `JobId` (`JobId`),
  INDEX `LocationId` (`LocationId`),
  INDEX `ModCode` (`ModCode`),
  INDEX `PartDesc` (`PartDesc`),
  INDEX `PartId` (`PartId`),
  INDEX `PartNo` (`PartNo`),
  INDEX `VendorId` (`VendorId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`NowStatement`;
CREATE TABLE `AntiFrieze`.`NowStatement` (
  `StatementID` INT(10) NOT NULL AUTO_INCREMENT,
  `StatementNum` DOUBLE(15, 5) NULL,
  `YearMonth` INT(10) NULL,
  `CusID` INT(10) NULL,
  `ARNUM` VARCHAR(25) NULL,
  `CusLastName` VARCHAR(50) NULL,
  `CusFirstName` VARCHAR(20) NULL,
  `CusStreet1` VARCHAR(50) NULL,
  `CusCity` VARCHAR(30) NULL,
  `CusState` VARCHAR(20) NULL,
  `CusZip` VARCHAR(20) NULL,
  `PreviousDue` DECIMAL(19, 4) NULL,
  `TotalCharges` DECIMAL(19, 4) NULL,
  `TotalPayments` DECIMAL(19, 4) NULL,
  `BalanceDue` DECIMAL(19, 4) NULL,
  `ItemCount` INT(10) NULL,
  `ClosedDate` DATETIME NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`StatementID`),
  UNIQUE INDEX `StatementNum` (`StatementID`),
  UNIQUE INDEX `StatementNum1` (`StatementNum`),
  INDEX `ARNUM` (`ARNUM`),
  INDEX `CusID` (`CusID`),
  INDEX `MonthYear` (`YearMonth`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`OldMail`;
CREATE TABLE `AntiFrieze`.`OldMail` (
  `RemindId` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(80) NULL,
  `Type` VARCHAR(15) NULL,
  `CarId` INT(10) NULL,
  `CusId` INT(10) NULL,
  `Created` DATETIME NULL,
  `SetDate` DATETIME NULL,
  `Completed` DATETIME NULL,
  `AddFactor` SMALLINT(5) NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`RemindId`),
  INDEX `CarId` (`CarId`),
  INDEX `CusId` (`CusId`),
  INDEX `MgrID` (`MgrID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Part`;
CREATE TABLE `AntiFrieze`.`Part` (
  `PartId` INT(10) NOT NULL AUTO_INCREMENT,
  `PartNo` VARCHAR(50) NULL,
  `BasePartNo` VARCHAR(50) NULL,
  `AltPartNo` VARCHAR(50) NULL,
  `PartDesc` VARCHAR(128) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `QtyOnHand` INT(10) NULL,
  `VendorId` VARCHAR(50) NULL,
  `MarkupNo` TINYINT(1) NOT NULL,
  `General` VARCHAR(1) NULL,
  `Note` LONGTEXT NULL,
  `LastUpdated` DATETIME NULL,
  `ModCode` VARCHAR(10) NULL,
  `JobId` VARCHAR(10) NULL,
  `Year` VARCHAR(4) NULL,
  `EngCode` VARCHAR(10) NULL,
  `IsChecked` TINYINT(1) NOT NULL,
  `Brand` VARCHAR(20) NULL,
  `BasePartId` INT(10) NULL,
  `coreprice` DECIMAL(19, 4) NULL,
  `LocationId` INT(10) NULL,
  `Stocked` TINYINT(1) NOT NULL,
  `MinQty` INT(10) NULL,
  `MaxQty` INT(10) NULL,
  `Units` VARCHAR(10) NULL,
  `APIAANumber` VARCHAR(40) NULL,
  PRIMARY KEY (`PartId`),
  INDEX `AltPartNum` (`AltPartNo`),
  INDEX `BasePartId` (`BasePartId`),
  INDEX `BasePartNo` (`BasePartNo`),
  INDEX `EngCode` (`EngCode`),
  INDEX `General` (`General`),
  INDEX `IsChecked` (`IsChecked`),
  INDEX `JobId` (`JobId`),
  INDEX `LocationId` (`LocationId`),
  INDEX `ModCode` (`ModCode`),
  INDEX `PartDesc` (`PartDesc`),
  INDEX `PartId` (`PartId`),
  INDEX `PartNo` (`PartNo`),
  INDEX `VendorId` (`VendorId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Paytype`;
CREATE TABLE `AntiFrieze`.`Paytype` (
  `PayType` INT(10) NOT NULL AUTO_INCREMENT,
  `ShortDesc` VARCHAR(15) NULL,
  `LongDesc` VARCHAR(20) NULL,
  `AcctsRec` TINYINT(1) NOT NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Rank` SMALLINT(5) NULL,
  `Group` VARCHAR(15) NULL,
  PRIMARY KEY (`PayType`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`PJHead`;
CREATE TABLE `AntiFrieze`.`PJHead` (
  `PJHdrID` INT(10) NOT NULL AUTO_INCREMENT,
  `Descript` VARCHAR(50) NULL,
  `PJDate` DATETIME NULL,
  `Count` INT(10) NULL,
  `Notes` LONGTEXT NULL,
  PRIMARY KEY (`PJHdrID`),
  INDEX `EstNum` (`Descript`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`PJItem`;
CREATE TABLE `AntiFrieze`.`PJItem` (
  `PJItemID` INT(10) NOT NULL AUTO_INCREMENT,
  `PJHdrID` INT(10) NULL,
  `PartNo` VARCHAR(50) NULL,
  `JobDesc` VARCHAR(255) NULL,
  `Hours` DOUBLE(15, 5) NULL,
  `Price` DOUBLE(15, 5) NULL,
  `Total` DECIMAL(19, 4) NULL,
  `CostEach` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `MarkupNo` TINYINT(1) NOT NULL,
  `InvType` VARCHAR(20) NULL,
  PRIMARY KEY (`PJItemID`),
  INDEX `EstNum` (`PJHdrID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`PMList`;
CREATE TABLE `AntiFrieze`.`PMList` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(50) NULL,
  `Date` DATETIME NULL,
  `Count` INT(10) NULL,
  PRIMARY KEY (`ID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`PurchaseTemp`;
CREATE TABLE `AntiFrieze`.`PurchaseTemp` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(255) NULL,
  `Date` DATETIME NULL,
  `Partnumber` VARCHAR(50) NULL,
  `Description` VARCHAR(255) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `Quantity` DOUBLE(15, 5) NULL,
  `Amount` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `Catagory` VARCHAR(255) NULL,
  `InvoiceID` INT(10) NULL,
  `PurchaseID` INT(10) NULL,
  `Core` TINYINT(1) NOT NULL,
  `Year` INT(10) NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`ID`),
  INDEX `InvoiceID` (`InvoiceID`),
  INDEX `MgrID` (`MgrID`),
  INDEX `Number` (`PurchaseID`),
  INDEX `TransactionDate` (`Date`),
  INDEX `TransactionsDescription` (`Description`),
  INDEX `TransactionsPartnumber` (`Partnumber`),
  INDEX `Vendor` (`Vendor`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Recent`;
CREATE TABLE `AntiFrieze`.`Recent` (
  `Recentkey` INT(10) NOT NULL AUTO_INCREMENT,
  `Short` VARCHAR(15) NULL,
  `Long` VARCHAR(50) NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Rank` INT(10) NULL,
  `EstNum` INT(10) NULL,
  `Date` DATETIME NULL,
  PRIMARY KEY (`Recentkey`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Reminders`;
CREATE TABLE `AntiFrieze`.`Reminders` (
  `RemindId` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `Type` VARCHAR(15) NULL,
  `CarId` INT(10) NULL,
  `CusId` INT(10) NULL,
  `RemindNum` INT(10) NULL,
  `Created` DATETIME NULL,
  `SetDate` DATETIME NULL,
  `Completed` DATETIME NULL,
  `AddFactor` SMALLINT(5) NULL,
  `MgrID` INT(10) NULL,
  `BatchName` VARCHAR(30) NULL,
  PRIMARY KEY (`RemindId`),
  INDEX `CarId` (`CarId`),
  INDEX `CusId` (`CusId`),
  INDEX `MgrID` (`MgrID`),
  INDEX `RemindNum` (`RemindNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Remindlist`;
CREATE TABLE `AntiFrieze`.`Remindlist` (
  `RIndex` INT(10) NOT NULL,
  `RemindNum` INT(10) NOT NULL,
  `ShortDesc` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `AddFactor` INT(10) NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`RemindNum`),
  INDEX `MgrID` (`MgrID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`RemindTemp`;
CREATE TABLE `AntiFrieze`.`RemindTemp` (
  `TempId` INT(10) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(80) NULL,
  `LongDesc` LONGTEXT NULL,
  `RemindID` INT(10) NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Priority` SMALLINT(5) NULL,
  `Type` VARCHAR(15) NULL,
  `CarId` INT(10) NULL,
  `CusId` INT(10) NULL,
  `RemindNum` INT(10) NULL,
  `Created` DATETIME NULL,
  `SetDate` DATETIME NULL,
  `Completed` DATETIME NULL,
  `AddFactor` INT(10) NULL,
  `Status` INT(10) NULL,
  `Rank` INT(10) NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`TempId`),
  INDEX `CarId` (`CarId`),
  INDEX `CusId` (`CusId`),
  INDEX `MgrID` (`MgrID`),
  INDEX `RemindID` (`RemindID`),
  INDEX `RemindNum` (`RemindNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`ShopTime`;
CREATE TABLE `AntiFrieze`.`ShopTime` (
  `ShopTimeId` INT(10) NOT NULL AUTO_INCREMENT,
  `JobId` VARCHAR(4) NULL,
  `ModelCode` VARCHAR(8) NULL,
  `Year` VARCHAR(4) NULL,
  `EngCode` VARCHAR(8) NULL,
  `ShopTime` DOUBLE(15, 5) NULL,
  `LastUpdated` DATETIME NULL,
  PRIMARY KEY (`ShopTimeId`),
  INDEX `EngCode` (`EngCode`),
  INDEX `JobId` (`JobId`),
  INDEX `ModelCode` (`ModelCode`),
  INDEX `ShopTimeId` (`ShopTimeId`),
  INDEX `Year` (`Year`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`SJobs`;
CREATE TABLE `AntiFrieze`.`SJobs` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NULL,
  `Jobid` VARCHAR(50) NULL,
  PRIMARY KEY (`ID`),
  INDEX `Jobid` (`Jobid`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`SmartPart`;
CREATE TABLE `AntiFrieze`.`SmartPart` (
  `ModPartId` INT(10) NOT NULL AUTO_INCREMENT,
  `ModCode` VARCHAR(10) NULL,
  `JobId` VARCHAR(10) NULL,
  `Year` VARCHAR(4) NULL,
  `EngCode` VARCHAR(10) NULL,
  `PartId` INT(10) NULL,
  `HiYear` VARCHAR(4) NULL,
  `LoYear` VARCHAR(4) NULL,
  PRIMARY KEY (`ModPartId`),
  UNIQUE INDEX `ModPartId` (`ModPartId`),
  INDEX `JobId` (`JobId`),
  INDEX `ModCode` (`ModCode`),
  INDEX `PartId` (`PartId`),
  INDEX `Year` (`Year`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Statement`;
CREATE TABLE `AntiFrieze`.`Statement` (
  `StatementID` INT(10) NOT NULL AUTO_INCREMENT,
  `StatementNum` DOUBLE(15, 5) NULL,
  `YearMonth` INT(10) NULL,
  `CusID` INT(10) NULL,
  `ARNUM` VARCHAR(25) NULL,
  `CusLastName` VARCHAR(50) NULL,
  `CusFirstName` VARCHAR(20) NULL,
  `CusStreet1` VARCHAR(50) NULL,
  `CusCity` VARCHAR(30) NULL,
  `CusState` VARCHAR(20) NULL,
  `CusZip` VARCHAR(20) NULL,
  `PreviousDue` DECIMAL(19, 4) NULL,
  `TotalCharges` DECIMAL(19, 4) NULL,
  `TotalPayments` DECIMAL(19, 4) NULL,
  `BalanceDue` DECIMAL(19, 4) NULL,
  `AR30` DECIMAL(19, 4) NULL,
  `AR60` DECIMAL(19, 4) NULL,
  `AR90` DECIMAL(19, 4) NULL,
  `AR120` DECIMAL(19, 4) NULL,
  `ItemCount` INT(10) NULL,
  `ClosedDate` DATETIME NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`StatementID`),
  UNIQUE INDEX `StatementNum` (`StatementID`),
  UNIQUE INDEX `StatementNum1` (`StatementNum`),
  INDEX `ARNUM` (`ARNUM`),
  INDEX `CusID` (`CusID`),
  INDEX `MonthYear` (`YearMonth`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Stock`;
CREATE TABLE `AntiFrieze`.`Stock` (
  `IDNum` INT(10) NOT NULL AUTO_INCREMENT,
  `Partnumber` VARCHAR(50) NULL,
  `Description` VARCHAR(128) NULL,
  `Count` DOUBLE(15, 5) NULL,
  `Reorder` DOUBLE(15, 5) NULL,
  `UnitsOnOrder` DOUBLE(15, 5) NULL,
  `WAC` DECIMAL(19, 4) NULL,
  `WACDate` DATETIME NULL,
  `LastWAC` DECIMAL(19, 4) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `MarkupNo` TINYINT(1) NOT NULL,
  `Core` TINYINT(1) NOT NULL,
  `Location` VARCHAR(50) NULL,
  `Notes` VARCHAR(254) NULL,
  PRIMARY KEY (`IDNum`),
  UNIQUE INDEX `PartNum` (`Partnumber`),
  INDEX `IDNum` (`IDNum`),
  INDEX `PartDesc` (`Description`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Suggest`;
CREATE TABLE `AntiFrieze`.`Suggest` (
  `suggestNum` VARCHAR(36) NOT NULL,
  `PartNo` VARCHAR(50) NULL,
  `JobDesc` VARCHAR(255) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `CostEach` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `Count` INT(10) NULL,
  `IVItem` TINYINT(1) NOT NULL,
  PRIMARY KEY (`suggestNum`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`TechTrack`;
CREATE TABLE `AntiFrieze`.`TechTrack` (
  `Tech` INT(10) NOT NULL AUTO_INCREMENT,
  `Short` VARCHAR(15) NULL,
  `FullName` VARCHAR(50) NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Rank` SMALLINT(5) NULL,
  `Team` VARCHAR(15) NULL,
  `PayRate` VARCHAR(50) NULL,
  `SS#` INT(10) NULL,
  PRIMARY KEY (`Tech`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`TempAddOpt`;
CREATE TABLE `AntiFrieze`.`TempAddOpt` (
  `TempId` INT(10) NOT NULL AUTO_INCREMENT,
  `MgrId` INT(10) NULL,
  `AddOptId` INT(10) NULL,
  `ShortDesc` VARCHAR(40) NULL,
  `Total` DECIMAL(19, 4) NULL,
  `FromDate` DATETIME NULL,
  `ToDate` DATETIME NULL,
  PRIMARY KEY (`TempId`),
  INDEX `AddOptId` (`AddOptId`),
  INDEX `MgrId` (`MgrId`),
  INDEX `TempId` (`TempId`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Transactions`;
CREATE TABLE `AntiFrieze`.`Transactions` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(255) NULL,
  `Date` DATETIME NULL,
  `Partnumber` VARCHAR(50) NULL,
  `Description` VARCHAR(255) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `Quantity` DOUBLE(15, 5) NULL,
  `Amount` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `InvoiceID` INT(10) NULL,
  `PurchaseID` INT(10) NULL,
  `ReferID` VARCHAR(50) NULL,
  `Core` TINYINT(1) NOT NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`ID`),
  INDEX `InvoiceID` (`InvoiceID`),
  INDEX `MgrID` (`MgrID`),
  INDEX `Number` (`PurchaseID`),
  INDEX `PurchaseID` (`ReferID`),
  INDEX `TransactionDate` (`Date`),
  INDEX `TransactionsDescription` (`Description`),
  INDEX `TransactionsPartnumber` (`Partnumber`),
  INDEX `Vendor` (`Vendor`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`TransTemp`;
CREATE TABLE `AntiFrieze`.`TransTemp` (
  `ID` INT(10) NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(255) NULL,
  `Date` DATETIME NULL,
  `Partnumber` VARCHAR(50) NULL,
  `Description` VARCHAR(255) NULL,
  `Cost` DECIMAL(19, 4) NULL,
  `Price` DECIMAL(19, 4) NULL,
  `Quantity` DOUBLE(15, 5) NULL,
  `Amount` DECIMAL(19, 4) NULL,
  `Vendor` VARCHAR(50) NULL,
  `InvoiceID` INT(10) NULL,
  `PurchaseID` INT(10) NULL,
  `Core` TINYINT(1) NOT NULL,
  `MgrID` INT(10) NULL,
  PRIMARY KEY (`ID`),
  INDEX `InvoiceID` (`InvoiceID`),
  INDEX `MgrID` (`MgrID`),
  INDEX `Number` (`PurchaseID`),
  INDEX `TransactionDate` (`Date`),
  INDEX `TransactionsDescription` (`Description`),
  INDEX `TransactionsPartnumber` (`Partnumber`),
  INDEX `Vendor` (`Vendor`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `AntiFrieze`.`Vendor`;
CREATE TABLE `AntiFrieze`.`Vendor` (
  `VenID` INT(10) NOT NULL AUTO_INCREMENT,
  `ShortDesc` VARCHAR(15) NULL,
  `LongDesc` VARCHAR(20) NULL,
  `AcctsRec` TINYINT(1) NOT NULL,
  `CurrentList` TINYINT(1) NOT NULL,
  `Rank` SMALLINT(5) NULL,
  `Group` VARCHAR(15) NULL,
  PRIMARY KEY (`VenID`)
)
ENGINE = INNODB;



SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------------
-- EOF

