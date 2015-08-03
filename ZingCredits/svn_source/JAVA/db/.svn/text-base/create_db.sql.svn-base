
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

DROP DATABASE IF EXISTS zingcredits;

--
-- Create schema zingcredits
--

CREATE DATABASE IF NOT EXISTS zingcredits;
USE zingcredits;

--
-- Definition of table `zingcredits`.`apps_info`
-- Lưu thông tin đăng ký của game/app

DROP TABLE IF EXISTS `zingcredits`.`apps_info`;
CREATE TABLE  `zingcredits`.`apps_info` (
  `appID` varchar(50) NOT NULL,
  `appName` varchar(255) NOT NULL,
  `appDesc` varchar(1023),
  `appURL` varchar(1023) NOT NULL, -- ví dụ: http://me.zing.vn/apps/ffs
  `iconPath` varchar(1023) NOT NULL, -- URL đến icon, ví dụ: http://credits-me.zing.vn/images/app/m_app_ffs.jpg
  `restURL` varchar(1023) NOT NULL, -- URL REST request gửi đến game/app thông báo chuyển item cho user sau khi đã trừ tiền trong tài khoản
  `key1` varchar(255) NOT NULL, -- private key sử dụng để sinh MAC tại bước "build billing form"
  `key2` varchar(255) NOT NULL, -- private key sử dụng để sinh MAC tại bước "gửi REST request cho game/app"
  `authMethod` int unsigned NOT NULL default 0, -- phương thức sinh MAC
  `isEnabled` tinyint NOT NULL default 1, -- 0: khong cho phep, 1: cho phep
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`appID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`accounts`
-- Lưu số tiền trong tài khoản của user

DROP TABLE IF EXISTS `zingcredits`.`accounts`;
CREATE TABLE  `zingcredits`.`accounts` (
  `userID` int unsigned NOT NULL,
  `amount` double NOT NULL, -- số tiền trong tài khoản
  `lastTxID` bigint unsigned NOT NULL, -- ID cua giao dich cuoi cung
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`acc_history_bill`
-- Lưu nhật ký mua hàng của user
-- Insert dữ liệu mới vào bảng này mỗi khi có sự thay đổi tiền trong tài khoản của user bởi giao dịch mua hàng
-- Không cho update dữ liệu trong bảng này
-- Mỗi lần insert vào bảng này đều kéo theo 1 lần update bảng `accounts`

DROP TABLE IF EXISTS `zingcredits`.`acc_history_bill`;
CREATE TABLE  `zingcredits`.`acc_history_bill` (
  `userID` int unsigned NOT NULL,
  `currentBalance` double NOT NULL, -- số tiền trong tài khoản trước khi thực hiện giao dịch
  `amount` double NOT NULL,  -- tổng tiền của giao dịch (thường là tổng đơn giá x số lượng mỗi item trong giao dịch)
  `txID` bigint unsigned NOT NULL, -- ID của giao dịch, tạo ra/quản lý bởi Zing Credits
  `txType` smallint unsigned NOT NULL, -- loại giao dịch: thanh_toán, hoàn_tiền
  `notes` varchar(1023) NOT NULL, -- chú thích: lý do, tác nhân thực hiện giao dịch
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`acc_history_push`
-- Lưu nhật ký chuyển tiền vào của user
-- Insert dữ liệu mới vào bảng này mỗi khi có sự thay đổi tiền trong tài khoản của user bởi giao dịch chuyển tiền vào
-- Không cho update dữ liệu trong bảng này
-- Mỗi lần insert vào bảng này đều kéo theo 1 lần update bảng `accounts`

DROP TABLE IF EXISTS `zingcredits`.`acc_history_push`;
CREATE TABLE  `zingcredits`.`acc_history_push` (
  `userID` int unsigned NOT NULL,
  `currentBalance` double NOT NULL, -- số tiền trong tài khoản trước khi thực hiện giao dịch
  `amount` double NOT NULL, -- tổng tiền của giao dịch
  `txID` bigint unsigned NOT NULL, -- ID của giao dịch, tạo ra/quản lý bởi Zing Credits
  `txType` smallint unsigned NOT NULL, -- loại giao dịch: nạp_tiền
  `notes` varchar(1023) NOT NULL, -- chú thích: lý do, tác nhân thực hiện giao dịch
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


--
-- Definition of table `zingcredits`.`history_admin`
-- Lưu nhật ký hiệu chỉnh tiền của admin cho 1 user
-- Insert dữ liệu mới vào bảng này mỗi khi có sự thay đổi tiền trong tài khoản của user bởi hiệu chỉnh tiền của admin
-- Không cho update dữ liệu trong bảng này
-- Mỗi lần insert vào bảng này đều kéo theo 1 lần update bảng `accounts`

DROP TABLE IF EXISTS `zingcredits`.`history_admin`;
CREATE TABLE  `zingcredits`.`history_admin` (
  `userID` int unsigned NOT NULL,
  `currentBalance` double NOT NULL, -- số tiền trong tài khoản trước khi thực hiện giao dịch
  `amount` double NOT NULL, -- tổng tiền của giao dịch
  `txID` bigint unsigned NOT NULL, -- ID của giao dịch, tạo ra/quản lý bởi Zing Credits
  `txType` smallint unsigned NOT NULL, -- loại giao dịch: nạp_tiền
  `notes` varchar(1023) NOT NULL, -- chú thích: lý do, tác nhân thực hiện giao dịch
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  KEY `user_time` (`userID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`txID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`transactions`
-- Bảng này chứa thông tin đầy đủ của các giao dịch
-- Insert dữ liệu mới vào bảng này mỗi khi bắt đầu giao dịch
-- Không cho update dữ liệu
-- Insert dữ liệu vào bảng này kéo theo insert 1 record vào bảng transactions_status với status "bắt đầu"

DROP TABLE IF EXISTS `zingcredits`.`transactions`;
CREATE TABLE  `zingcredits`.`transactions` (
  `txID` bigint unsigned NOT NULL, -- ID của giao dịch, tạo ra/quản lý bởi Zing Credits
  `txType` smallint unsigned NOT NULL, -- loại giao dịch: thanh_toán, nạp_tiền
  `txTime` int unsigned NOT NULL, -- Unix timestamp của giao dịch duoc xac dinh tren server cua game/app
  `txLocalTime` int unsigned NOT NULL, -- Local unix timestamp của giao dịch khi bắt đầu tạo giao dịch (trước khi insert vào ZooKeeper)
  `userID` int unsigned NOT NULL,
  `userName` varchar(255) NOT NULL,
  `currentBalance` double NOT NULL, -- số tiền trong tài khoản trước khi thực hiện giao dịch
  `agentID` varchar(50) NOT NULL, -- là appID
  `refID` varchar(50) NOT NULL, -- ID của giao dịch được tạo/quản lý bởi game/app
  `items` varchar(1023), -- danh sách ID của vật phẩm, phân cách bởi dấu |
  `itemNames` varchar(1023), -- danh sách tên từng vật phẩm, phân cách bởi dấu |
  `prices` varchar(1023), -- danh sách đơn giá từng vật phẩm, phân cách bởi dấu |
  `quantities` varchar(1023), -- danh sách số lượng từng vật phẩm, phân cách bởi dấu |
  `amount` double NOT NULL, -- tổng tiển của giao dịch
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`txID`),
  KEY `agent_time` (`agentID`,`lastUpdate`),
  KEY `user_tx` (`userID`,`lastUpdate`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`transactions_status`
-- Lưu trạng thái của giao dịch, mỗi giao dịch có thể có nhiều data records
-- Insert dữ liệu mới mỗi khi giao dịch có trạng thái mới
-- Không cho update dữ liệu

DROP TABLE IF EXISTS `zingcredits`.`transactions_status`;
CREATE TABLE  `zingcredits`.`transactions_status` (
  `txID` bigint unsigned NOT NULL, -- ID của giao dịch, tạo ra/quản lý bởi Zing Credits
  `txStatus` smallint NOT NULL, -- tình trạng giao dịch: tham khảo bảng tx_status_type
  `resultCode` smallint NOT NULL, -- ma lỗi từ game/app
  `message` varchar(255) default NULL, -- thông báo lỗi từ game/app
  `lastUpdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  KEY `txID` (`txID`),
  KEY `lastUpdate` (`lastUpdate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`tx_type`
--

DROP TABLE IF EXISTS `zingcredits`.`tx_type`;
CREATE TABLE  `zingcredits`.`tx_type` (
-- Các loại giao dịch
---- thanh_toán: mua bán vật phẩm trong game/app
---- hoàn_tiền: trả lại tiền khi mua bán vật phẩm thất bại
---- nạp_tiền: chuyển tiền từ ZingPay
---- chuyển_tiền: chuyển tiền giữa users
---- tặng_tiền: tặng tiền từ hệ thống
---- admin: quyết định cộng/trừ tiền từ người quản trị bởi khiếu nại, ...
---- test: giao dịch test bởi nhóm phát triển
  `txType` smallint unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `desc` varchar(1023),
  PRIMARY KEY  (`txType`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Definition of table `zingcredits`.`tx_status_type`
--

DROP TABLE IF EXISTS `zingcredits`.`tx_status_type`;
CREATE TABLE  `zingcredits`.`tx_status_type` (
-- Các loại trạng thái
---- Bắt_đầu: sau khi lấy từ Zookeeper, UI hiển thị "Giao dịch đang thực hiện"
---- Cập_nhật_tài_khoản: sau khi cập nhật (cộng/trừ) số tiền trong tài khoản, UI hiển thị "Giao dịch đang thực hiện"
---- Gửi_REST: trước khi gửi REST request yêu cầu game/app chuyển vật phẩm, UI hiển thị "Giao dịch đang thực hiện"
---- Đóng_thành_công: game/app trả lời mã thành công, UI hiển thị "Giao dịch thành công" với mô tả chi tiết của giao dịch
---- Đóng_thất bại: game/app trả lời mã thất bại, UI hiển thị "Giao dịch không thành công" với thông điệp lỗi do game/app trả về
---- Đóng thất_bại_timeout: đã gửi request cho game/app, nhưng không nhận được trả lời (hoặc time exception), không thực hiện rollback, UI hiển thị "Giao dịch thành công"
---- Đóng thất_bại_network_err: lỗi (exception) khi gửi request cho game/app, có thực hiện ROLLBACK, UI hiển thị "Giao dịch thất bại" với mô tả lỗi "Lỗi mạng"
  `txStatus` smallint NOT NULL,
  `isCompleted` tinyint NOT NULL, -- 0: chưa hoàn thành, 1: đã hoàn thành
  `name` varchar(255) NOT NULL,
  `desc` varchar(1023),
  PRIMARY KEY  (`txStatus`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- -----------------------------------------------------------------------------
-- Predefined data

-- apps_info
INSERT INTO `zingcredits`.`apps_info` (`appID`,`appName`,`appDesc`,`appURL`,`iconPath`,`restURL`,`key1`,`key2`,`authMethod`,`isEnabled`) VALUES 
 ('stbb','Siêu Thị Bạn Bè','Siêu Thị Bạn Bè','http://me.zing.vn/apps/ffs','apps/stbb.jpg','http://ffs2.apps.zing.vn/app/payment/updateresult','fAQO8VU09Bch7+TW5zi8BfBVSWKiC3WM','fAQO8VU09Bch7+TW5zi8BfBVSWKiC3WM',0,1),
 ('zing','Zing Pay','Zing Pay','https://pay.zing.vn','apps/zing.jpg','https://pay.zing.vn','Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD','Dz5zJbamRMcxs2iGhnbv2hSPTXgL2vYD',0,1),
 ('ntvv','Nông Trại Vui Vẻ','Nông Trại Vui Vẻ','http://ntvv.apps.zing.vn','apps/ntvv.jpg','http://game-simulator.me.zing.vn/response/index','Q/Y8eJr7SPmbImm6tT57mTBPxC+oWEcD','Q/Y8eJr7SPmbImm6tT57mTBPxC+oWEcD',0,1);

-- tx_status_type
INSERT INTO `zingcredits`.`tx_status_type` (`txStatus`,`isCompleted`,`name`,`desc`) VALUES 
 (1,	0,	'initialized','Khởi tạo giao dịch'),
 (2,	0,	'updated_balance','Đã cập nhật số tiền mới vào tài khoản'),
 (3,	0,	'rest_requesting','Chuẩn bị gửi REST request yêu cầu game/app chuyển vật phẩm cho người dùng'),
 (101,	1,	'closed_success','Game/app trả lời mã thành công (đã chuyển vật phẩm cho người dùng)'),
 (102,	1,	'closed_ex_timeout','Đã gửi request REST cho game/app, nhưng không nhận được trả lời (xem như đã chuyển vật phẩm cho người dùng)'),
 (-103,	1,	'closed_fail','Game/app trả lời mã thất bại (không chuyển vật phẩm cho người dùng)'),
 (-104,	1,	'closed_ex_network','Lỗi mạng khi gửi request REST cho game/app (chưa chuyển vật phẩm cho người dùng)');

-- tx_type
INSERT INTO `zingcredits`.`tx_type` (`txType`,`name`,`desc`) VALUES 
 (1,	'test',			'Giao dịch test bởi nhóm phát triển'),
 (2,	'admin',		'Quyết định cộng/trừ tiền từ người quản trị do khiếu nại, ...'),
 (101,	'nap_tien',		'Chuyển tiền từ Zing Pay'),
 (102,	'chuyen_tien',	'Chuyển tiền giữa người dùng'),
 (103,	'tang_tien',	'Tặng tiền từ hệ thống'),
 (201,	'thanh_toan',	'Mua vật phẩm trong game/app'),
 (202,	'hoan_tien',	'Trả lại tiền khi mua vật phẩm thất bại');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
