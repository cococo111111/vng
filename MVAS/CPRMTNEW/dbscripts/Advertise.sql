# HeidiSQL Dump 
#
# --------------------------------------------------------
# Host:                         10.199.38.102
# Database:                     smsgw
# Server version:               5.0.66a-enterprise-gpl-log
# Server OS:                    redhat-linux-gnu
# Target compatibility:         mysqldump+mysqlcli 5.0
# Target max_allowed_packet:    67108864
# HeidiSQL version:             4.0 RC1
# --------------------------------------------------------


USE `smsgw`;


DROP TABLE IF EXISTS `operators`;

#
# Table structure for table 'operators'
#

CREATE TABLE `operators` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `operator` varchar(10) NOT NULL,
  `series` varchar(10) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `series` (`series`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



#
# Dumping data for table 'operators'
#

LOCK TABLES `operators` WRITE;
/*!40000 ALTER TABLE `operators` DISABLE KEYS*/;
INSERT INTO `operators` (`operator`, `series`) VALUES
	('VMS','8490'),
	('VMS','8493'),
	('VMS','84121'),
	('VMS','84122'),
	('VMS','84126'),
	('VMS','84128'),
	('VIETEL','8497'),
	('VIETEL','8498'),
	('VIETEL','84168'),
	('VIETEL','84169'),
	('VIETEL','84166'),
	('GPC','8491'),
	('GPC','8494'),
	('GPC','84123'),
	('GPC','84125'),
	('GPC','84127'),
	('SFONE','8495');
UNLOCK TABLES;


DROP TABLE IF EXISTS `vng_cancel_ads`;

#
# Table structure for table 'vng_cancel_ads'
#

CREATE TABLE `vng_cancel_ads` (
  `userid` varchar(20) NOT NULL,
  PRIMARY KEY  (`userid`),
  KEY `userid` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
