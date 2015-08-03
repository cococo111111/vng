DROP DATABASE IF EXISTS zingcredits_app;

--
-- Create schema zingcredits
--

CREATE DATABASE IF NOT EXISTS zingcredits_app;
USE zingcredits_app;

DROP TABLE IF EXISTS `zingcredits_app`.`apps_summary`;
CREATE TABLE  `zingcredits_app`.`apps_summary` (
  `appID` varchar(50) NOT NULL,
  `analyticDate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `totalTranx` int unsigned NOT NULL default 0, -- tổng số transactions của app
  `totalSuccessTranx` int unsigned NOT NULL default 0, -- tổng số transactions thành công của app
  `totalFailureTranx` int unsigned NOT NULL default 0, -- tổng số transactions thất bại của app
  `totalTimeoutTranx` int unsigned NOT NULL default 0, -- tổng số transactions thất bại do time out của app
  `totalNetworkFailTranx` int unsigned NOT NULL default 0, -- tổng số transactions thất bại do network của app
  `amount` double NOT NULL, -- số tiền giao dịch
  `totalUsers` int unsigned NOT NULL default 0, -- số users thực hiện giao dịch
   PRIMARY KEY (`appID`,`analyticDate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `zingcredits_app`.`transactions`;
CREATE TABLE  `zingcredits_app`.`transactions` (
  `txID` bigint unsigned NOT NULL, -- ID của giao dịch, tạo ra/quản lý bởi Zing Credits
  `txTime` timestamp NOT NULL default CURRENT_TIMESTAMP, -- Unix timestamp của giao dịch
  `userID` int unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `appID` varchar(50) NOT NULL, -- là appID
  `refID` varchar(50) NOT NULL, -- ID của giao dịch được tạo/quản lý bởi game/app
  `itemIDs` varchar(1023), -- danh sách ID của vật phẩm, phân cách bởi dấu |
  `itemNames` varchar(1023), -- danh sách tên từng vật phẩm, phân cách bởi dấu |
  `itemPrices` varchar(1023), -- danh sách đơn giá từng vật phẩm, phân cách bởi dấu |
  `itemQuantities` varchar(1023), -- danh sách số lượng từng vật phẩm, phân cách bởi dấu |
  `amount` double NOT NULL, -- tổng tiển của giao dịch
  `resultCode` smallint NOT NULL,
  `message` varchar(255) default NULL, -- thông báo lỗi từ game/app
  PRIMARY KEY  (`appID`,`refID`),
  KEY `agent_time` (`appID`,`txTime`),
  KEY `userID_time` (`userID`,`txTime`),
  KEY `userName_time` (`userName`,`txTime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

