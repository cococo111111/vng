DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201103`;
CREATE TABLE  `zingcredits_app`.`transactions_201103` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201104`;
CREATE TABLE  `zingcredits_app`.`transactions_201104` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201105`;
CREATE TABLE  `zingcredits_app`.`transactions_201105` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201106`;
CREATE TABLE  `zingcredits_app`.`transactions_201106` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201107`;
CREATE TABLE  `zingcredits_app`.`transactions_201107` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201108`;
CREATE TABLE  `zingcredits_app`.`transactions_201108` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201109`;
CREATE TABLE  `zingcredits_app`.`transactions_201109` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201110`;
CREATE TABLE  `zingcredits_app`.`transactions_201110` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201111`;
CREATE TABLE  `zingcredits_app`.`transactions_201111` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201112`;
CREATE TABLE  `zingcredits_app`.`transactions_201112` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201201`;
CREATE TABLE  `zingcredits_app`.`transactions_201201` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201202`;
CREATE TABLE  `zingcredits_app`.`transactions_201202` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201203`;
CREATE TABLE  `zingcredits_app`.`transactions_201203` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201204`;
CREATE TABLE  `zingcredits_app`.`transactions_201204` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201205`;
CREATE TABLE  `zingcredits_app`.`transactions_201205` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201206`;
CREATE TABLE  `zingcredits_app`.`transactions_201206` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201207`;
CREATE TABLE  `zingcredits_app`.`transactions_201207` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201208`;
CREATE TABLE  `zingcredits_app`.`transactions_201208` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201209`;
CREATE TABLE  `zingcredits_app`.`transactions_201209` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201210`;
CREATE TABLE  `zingcredits_app`.`transactions_201210` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201211`;
CREATE TABLE  `zingcredits_app`.`transactions_201211` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201212`;
CREATE TABLE  `zingcredits_app`.`transactions_201212` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201301`;
CREATE TABLE  `zingcredits_app`.`transactions_201301` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201302`;
CREATE TABLE  `zingcredits_app`.`transactions_201302` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201303`;
CREATE TABLE  `zingcredits_app`.`transactions_201303` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201304`;
CREATE TABLE  `zingcredits_app`.`transactions_201304` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201305`;
CREATE TABLE  `zingcredits_app`.`transactions_201305` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201306`;
CREATE TABLE  `zingcredits_app`.`transactions_201306` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201307`;
CREATE TABLE  `zingcredits_app`.`transactions_201307` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201308`;
CREATE TABLE  `zingcredits_app`.`transactions_201308` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201309`;
CREATE TABLE  `zingcredits_app`.`transactions_201309` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201310`;
CREATE TABLE  `zingcredits_app`.`transactions_201310` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201311`;
CREATE TABLE  `zingcredits_app`.`transactions_201311` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201312`;
CREATE TABLE  `zingcredits_app`.`transactions_201312` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201401`;
CREATE TABLE  `zingcredits_app`.`transactions_201401` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201402`;
CREATE TABLE  `zingcredits_app`.`transactions_201402` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201403`;
CREATE TABLE  `zingcredits_app`.`transactions_201403` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201404`;
CREATE TABLE  `zingcredits_app`.`transactions_201404` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201405`;
CREATE TABLE  `zingcredits_app`.`transactions_201405` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201406`;
CREATE TABLE  `zingcredits_app`.`transactions_201406` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201407`;
CREATE TABLE  `zingcredits_app`.`transactions_201407` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201408`;
CREATE TABLE  `zingcredits_app`.`transactions_201408` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201409`;
CREATE TABLE  `zingcredits_app`.`transactions_201409` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201410`;
CREATE TABLE  `zingcredits_app`.`transactions_201410` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201411`;
CREATE TABLE  `zingcredits_app`.`transactions_201411` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201412`;
CREATE TABLE  `zingcredits_app`.`transactions_201412` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201501`;
CREATE TABLE  `zingcredits_app`.`transactions_201501` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201502`;
CREATE TABLE  `zingcredits_app`.`transactions_201502` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201503`;
CREATE TABLE  `zingcredits_app`.`transactions_201503` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201504`;
CREATE TABLE  `zingcredits_app`.`transactions_201504` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201505`;
CREATE TABLE  `zingcredits_app`.`transactions_201505` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201506`;
CREATE TABLE  `zingcredits_app`.`transactions_201506` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201507`;
CREATE TABLE  `zingcredits_app`.`transactions_201507` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201508`;
CREATE TABLE  `zingcredits_app`.`transactions_201508` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201509`;
CREATE TABLE  `zingcredits_app`.`transactions_201509` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201510`;
CREATE TABLE  `zingcredits_app`.`transactions_201510` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201511`;
CREATE TABLE  `zingcredits_app`.`transactions_201511` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201512`;
CREATE TABLE  `zingcredits_app`.`transactions_201512` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201601`;
CREATE TABLE  `zingcredits_app`.`transactions_201601` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201602`;
CREATE TABLE  `zingcredits_app`.`transactions_201602` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201603`;
CREATE TABLE  `zingcredits_app`.`transactions_201603` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201604`;
CREATE TABLE  `zingcredits_app`.`transactions_201604` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201605`;
CREATE TABLE  `zingcredits_app`.`transactions_201605` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201606`;
CREATE TABLE  `zingcredits_app`.`transactions_201606` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201607`;
CREATE TABLE  `zingcredits_app`.`transactions_201607` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201608`;
CREATE TABLE  `zingcredits_app`.`transactions_201608` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201609`;
CREATE TABLE  `zingcredits_app`.`transactions_201609` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201610`;
CREATE TABLE  `zingcredits_app`.`transactions_201610` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201611`;
CREATE TABLE  `zingcredits_app`.`transactions_201611` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201612`;
CREATE TABLE  `zingcredits_app`.`transactions_201612` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201701`;
CREATE TABLE  `zingcredits_app`.`transactions_201701` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201702`;
CREATE TABLE  `zingcredits_app`.`transactions_201702` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201703`;
CREATE TABLE  `zingcredits_app`.`transactions_201703` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201704`;
CREATE TABLE  `zingcredits_app`.`transactions_201704` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201705`;
CREATE TABLE  `zingcredits_app`.`transactions_201705` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201706`;
CREATE TABLE  `zingcredits_app`.`transactions_201706` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201707`;
CREATE TABLE  `zingcredits_app`.`transactions_201707` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201708`;
CREATE TABLE  `zingcredits_app`.`transactions_201708` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201709`;
CREATE TABLE  `zingcredits_app`.`transactions_201709` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201710`;
CREATE TABLE  `zingcredits_app`.`transactions_201710` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201711`;
CREATE TABLE  `zingcredits_app`.`transactions_201711` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201712`;
CREATE TABLE  `zingcredits_app`.`transactions_201712` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201801`;
CREATE TABLE  `zingcredits_app`.`transactions_201801` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201802`;
CREATE TABLE  `zingcredits_app`.`transactions_201802` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201803`;
CREATE TABLE  `zingcredits_app`.`transactions_201803` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201804`;
CREATE TABLE  `zingcredits_app`.`transactions_201804` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201805`;
CREATE TABLE  `zingcredits_app`.`transactions_201805` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201806`;
CREATE TABLE  `zingcredits_app`.`transactions_201806` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201807`;
CREATE TABLE  `zingcredits_app`.`transactions_201807` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201808`;
CREATE TABLE  `zingcredits_app`.`transactions_201808` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201809`;
CREATE TABLE  `zingcredits_app`.`transactions_201809` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201810`;
CREATE TABLE  `zingcredits_app`.`transactions_201810` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201811`;
CREATE TABLE  `zingcredits_app`.`transactions_201811` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201812`;
CREATE TABLE  `zingcredits_app`.`transactions_201812` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201901`;
CREATE TABLE  `zingcredits_app`.`transactions_201901` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201902`;
CREATE TABLE  `zingcredits_app`.`transactions_201902` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201903`;
CREATE TABLE  `zingcredits_app`.`transactions_201903` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201904`;
CREATE TABLE  `zingcredits_app`.`transactions_201904` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201905`;
CREATE TABLE  `zingcredits_app`.`transactions_201905` (
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
DROP TABLE IF EXISTS `zingcredits_app`.`transactions_201906`;
CREATE TABLE  `zingcredits_app`.`transactions_201906` (
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


