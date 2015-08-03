/*
MySQL Data Transfer
Source Host: 10.199.18.37
Source Database: zingffs
Target Host: 10.199.18.37
Target Database: zingffs
Date: 1/26/2011 4:48:26 PM
*/

USE zingffs;

CREATE TABLE `tbpayment_1_500000` (
  `payment_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(10) unsigned NOT NULL DEFAULT '0',
  `username` varchar(225) CHARACTER SET utf8 NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `price` int(11) NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `total_money` bigint(20) NOT NULL,
  `type_id` int(10) NOT NULL DEFAULT '0',
  `is_finish` tinyint(1) NOT NULL DEFAULT '0',
  `billno` bigint(20) NOT NULL DEFAULT '0',
  `create_date` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`payment_id`),
  KEY `username` (`username`),
  KEY `member_id` (`member_id`),
  KEY `billno` (`billno`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;