USE zingcredits;

CREATE TABLE  `zingcredits`.`acc_history_bill_201101` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201101` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201101` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201101` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201102` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201102` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201102` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201102` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201103` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201103` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201103` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201103` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201104` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201104` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201104` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201104` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201105` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201105` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201105` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201105` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201106` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201106` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201106` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201106` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201107` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201107` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201107` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201107` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201108` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201108` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201108` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201108` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201109` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201109` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201109` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201109` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201110` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201110` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201110` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201110` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201111` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201111` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201111` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201111` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201112` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201112` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201112` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201112` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201201` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201201` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201201` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201201` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201202` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201202` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201202` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201202` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201203` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201203` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201203` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201203` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201204` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201204` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201204` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201204` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201205` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201205` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201205` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201205` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201206` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201206` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201206` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201206` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201207` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201207` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201207` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201207` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201208` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201208` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201208` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201208` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201209` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201209` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201209` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201209` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201210` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201210` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201210` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201210` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201211` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201211` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201211` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201211` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201212` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201212` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201212` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201212` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201301` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201301` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201301` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201301` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201302` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201302` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201302` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201302` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201303` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201303` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201303` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201303` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201304` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201304` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201304` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201304` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201305` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201305` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201305` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201305` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201306` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201306` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201306` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201306` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201307` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201307` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201307` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201307` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201308` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201308` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201308` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201308` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201309` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201309` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201309` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201309` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201310` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201310` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201310` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201310` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201311` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201311` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201311` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201311` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201312` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201312` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201312` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201312` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201401` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201401` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201401` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201401` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201402` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201402` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201402` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201402` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201403` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201403` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201403` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201403` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201404` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201404` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201404` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201404` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201405` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201405` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201405` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201405` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201406` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201406` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201406` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201406` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201407` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201407` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201407` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201407` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201408` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201408` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201408` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201408` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201409` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201409` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201409` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201409` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201410` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201410` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201410` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201410` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201411` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201411` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201411` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201411` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201412` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201412` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201412` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201412` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201501` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201501` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201501` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201501` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201502` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201502` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201502` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201502` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201503` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201503` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201503` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201503` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201504` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201504` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201504` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201504` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201505` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201505` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201505` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201505` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201506` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201506` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201506` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201506` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201507` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201507` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201507` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201507` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201508` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201508` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201508` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201508` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201509` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201509` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201509` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201509` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201510` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201510` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201510` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201510` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201511` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201511` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201511` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201511` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201512` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201512` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201512` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201512` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201601` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201601` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201601` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201601` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201602` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201602` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201602` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201602` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201603` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201603` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201603` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201603` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201604` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201604` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201604` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201604` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201605` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201605` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201605` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201605` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201606` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201606` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201606` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201606` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201607` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201607` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201607` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201607` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201608` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201608` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201608` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201608` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201609` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201609` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201609` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201609` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201610` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201610` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201610` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201610` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201611` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201611` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201611` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201611` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201612` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201612` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201612` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201612` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201701` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201701` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201701` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201701` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201702` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201702` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201702` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201702` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201703` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201703` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201703` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201703` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201704` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201704` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201704` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201704` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201705` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201705` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201705` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201705` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201706` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201706` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201706` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201706` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201707` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201707` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201707` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201707` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201708` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201708` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201708` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201708` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201709` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201709` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201709` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201709` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201710` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201710` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201710` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201710` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201711` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201711` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201711` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201711` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201712` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201712` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201712` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201712` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201801` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201801` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201801` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201801` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201802` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201802` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201802` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201802` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201803` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201803` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201803` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201803` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201804` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201804` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201804` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201804` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201805` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201805` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201805` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201805` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201806` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201806` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201806` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201806` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201807` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201807` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201807` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201807` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201808` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201808` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201808` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201808` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201809` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201809` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201809` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201809` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201810` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201810` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201810` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201810` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201811` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201811` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201811` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201811` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE  `zingcredits`.`acc_history_bill_201812` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`acc_history_push_201812` (
  `userID` int(10) unsigned NOT NULL,
  `currentBalance` double NOT NULL,
  `amount` double NOT NULL,
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `notes` varchar(1023) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_201812` (
  `txID` bigint(20) unsigned NOT NULL,
  `txType` smallint(5) unsigned NOT NULL,
  `txTime` int(10) unsigned NOT NULL,
  `txLocalTime` int(10) unsigned NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL,
  `agentID` varchar(50) NOT NULL,
  `refID` varchar(50) NOT NULL,
  `items` varchar(1023) DEFAULT NULL,
  `itemNames` varchar(1023) DEFAULT NULL,
  `prices` varchar(1023) DEFAULT NULL,
  `quantities` varchar(1023) DEFAULT NULL,
  `amount` double NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
CREATE TABLE  `zingcredits`.`transactions_status_201812` (
  `txID` bigint(20) unsigned NOT NULL,
  `txStatus` smallint(6) NOT NULL,
  `resultCode` smallint(6) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;