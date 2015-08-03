DROP TABLE IF EXISTS `zingcredits_app`.`apps_sort`;
CREATE TABLE  `zingcredits_app`.`apps_sort` (
  `appID` varchar(50) NOT NULL,
  `position` int NOT NULL default 1,
  PRIMARY KEY  (`appID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

