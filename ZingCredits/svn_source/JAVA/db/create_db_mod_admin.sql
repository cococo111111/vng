DROP TABLE IF EXISTS `zingcredits_app`.`credits_admin`;

CREATE TABLE  `zingcredits_app`.`credits_admin` (
  `adminID` varchar(50) NOT NULL,
  `adminName` varchar(255) NOT NULL,
  `adminPassword` varchar(1000),
  PRIMARY KEY  (`adminID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
