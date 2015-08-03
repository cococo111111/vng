-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.77-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema zingcredits
--

CREATE DATABASE IF NOT EXISTS zingcredits;
USE zingcredits;

--
-- Definition of table `zingcredits`.`ApplicationInfo`
--

DROP TABLE IF EXISTS `zingcredits`.`ApplicationInfo`;
CREATE TABLE  `zingcredits`.`ApplicationInfo` (
  `AppID` varchar(50) NOT NULL,
  `AppName` varchar(50) default NULL,
  `AppDes` varchar(255) default NULL,
  `AppURL` varchar(255) NOT NULL,
  `IconPath` varchar(255) default NULL,
  `RestURL` varchar(255) NOT NULL,
  `Key1` varchar(1023) NOT NULL,
  `Key2` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`AppID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`accounts`
--

DROP TABLE IF EXISTS `zingcredits`.`accounts`;
CREATE TABLE  `zingcredits`.`accounts` (
  `userID` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `zingcredits`.`acct_history_bill`
--

DROP TABLE IF EXISTS `zingcredits`.`acct_history_bill`;
CREATE TABLE  `zingcredits`.`acct_history_bill` (
  `userID` varchar(50) NOT NULL,
  `tti` varchar(10) NOT NULL,
  `agentID` varchar(20) NOT NULL,
  `amount` double NOT NULL,
  `txID` varchar(20) NOT NULL,
  `refID` varchar(20) NOT NULL,
  `lastUpdate` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`tti`,`txID`),
  UNIQUE KEY `refID` (`agentID`,`refID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `zingcredits`.`acct_history_push`
--

DROP TABLE IF EXISTS `zingcredits`.`acct_history_push`;
CREATE TABLE  `zingcredits`.`acct_history_push` (
  `userID` varchar(50) NOT NULL,
  `tti` varchar(10) NOT NULL,
  `agentID` varchar(20) NOT NULL,
  `amount` double NOT NULL,
  `txID` varchar(20) NOT NULL,
  `refID` varchar(20) NOT NULL,
  `lastUpdate` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`tti`,`txID`),
  UNIQUE KEY `refID` (`agentID`,`refID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `zingcredits`.`transactionLogs`
--

DROP TABLE IF EXISTS `zingcredits`.`transactionLogs`;
CREATE TABLE  `zingcredits`.`transactionLogs` (
  `txID` varchar(20) NOT NULL,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `tti` varchar(10) NOT NULL,
  `chargeNo` varchar(20) default NULL,
  `approvalCode` varchar(20) default NULL,
  `userID` varchar(50) default NULL,
  `accountNo` varchar(20) default NULL,
  `amount` double(12,2) default NULL,
  `currentBalance` double(12,2) default NULL,
  `txTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `raccountNo` varchar(20) default NULL,
  `ruserID` varchar(50) default NULL,
  `agentID` varchar(20) default NULL,
  `orderNo` varchar(20) NOT NULL,
  `items` varchar(1023) default NULL,
  `prices` varchar(1023) default NULL,
  `quantities` varchar(1023) default NULL,
  `itemNames` varchar(1023) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`txID`,`tti`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`transactions`
--

DROP TABLE IF EXISTS `zingcredits`.`transactions`;
CREATE TABLE  `zingcredits`.`transactions` (
  `txID` varchar(20) NOT NULL,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `tti` varchar(10) NOT NULL,
  `chargeNo` varchar(20) default NULL,
  `approvalCode` varchar(20) default NULL,
  `userID` varchar(50) default NULL,
  `accountNo` varchar(20) default NULL,
  `amount` double(12,2) default NULL,
  `currentBalance` double(12,2) default NULL,
  `txTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `raccountNo` varchar(20) default NULL,
  `ruserID` varchar(50) default NULL,
  `agentID` varchar(20) default NULL,
  `orderNo` varchar(20) default NULL,
  `items` varchar(1023) default NULL,
  `prices` varchar(1023) default NULL,
  `quantities` varchar(1023) default NULL,
  `itemNames` varchar(1023) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`txID`,`tti`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`tx_type`
--

DROP TABLE IF EXISTS `zingcredits`.`tx_type`;
CREATE TABLE  `zingcredits`.`tx_type` (
  `tti` varchar(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`tti`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- -----------------------------------------------------------------------------
-- Predefined data

INSERT INTO `zingcredits`.`ApplicationInfo` (`AppID`,`AppName`,`AppDes`,`AppURL`,`IconPath`,`RestURL`,`Key1`,`Key2`) VALUES 
 ('stbb_staging','Siêu Thị Bạn Bè','Siêu Thị Bạn Bè','http://me.zing.vn/apps/ffs3','/images/app/m_app_ffs.jpg','http://staging.ffs.apps.zing.vn/app/payment/updateresult','318076597dcbebf1eca109ab4eeb11dfe6365608258bc8f4fdc356e44076e89c868caa650d6730bdd872b60d78f1c3ce0ef4072c10b1af4add9e81e2d5fa02b2ec9d0c6976520538','318076597dcbebf1eca109ab4eeb11dfe6365608258bc8f4fdc356e44076e89c868caa650d6730bdd872b60d78f1c3ce0ef4072c10b1af4add9e81e2d5fa02b2ec9d0c6976520538'),
 ('stbb','Siêu Thị Bạn Bè','Siêu Thị Bạn Bè','http://me.zing.vn/apps/ffs3','/images/app/m_app_ffs.jpg','http://staging.ffs.apps.zing.vn/app/payment/updateresult','318076597dcbebf1eca109ab4eeb11dfe6365608258bc8f4fdc356e44076e89c868caa650d6730bdd872b60d78f1c3ce0ef4072c10b1af4add9e81e2d5fa02b2ec9d0c6976520538','318076597dcbebf1eca109ab4eeb11dfe6365608258bc8f4fdc356e44076e89c868caa650d6730bdd872b60d78f1c3ce0ef4072c10b1af4add9e81e2d5fa02b2ec9d0c6976520538'),
('zing','Zing Pay','Zing Pay','https://pay.zing.vn','/images/app/m_app_zp.jpg','https://pay.zing.vn','zing-security-key','620d52a8205b36b9151d1cf17d3b1411d9e84f884a5084ec');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
