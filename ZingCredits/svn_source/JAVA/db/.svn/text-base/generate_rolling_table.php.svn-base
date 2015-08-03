<?php
$backupDay=('2011-03-11');
for($i=0;$i<100;$i++){
$day=strtotime($backupDay.' +'.$i.' month');
$fromMonth = date('m',$day);
$fromYear = date('Y',$day);
$time= $fromYear."".$fromMonth;
echo "DROP TABLE IF EXISTS `zingcredits_app`.`transactions_".$time."`;
CREATE TABLE  `zingcredits_app`.`transactions_".$time."` (
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;\n";
}
die();

?>
