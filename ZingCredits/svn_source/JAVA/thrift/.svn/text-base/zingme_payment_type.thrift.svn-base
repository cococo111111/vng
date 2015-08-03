namespace java vng.zingme.payment.thrift
namespace php vng_zingme_payment_thrift

struct T_Response {
	1:i32 code,
	2:string refNo,
	3:string mac,
	4:double mxuBalance
}

struct T_AccResponse {
	1:i32 code,
	2:double currentBalance,
}

struct T_Transaction {
	1:i64 txID,
	2:i16 txType,
	3:i32 txTime,
	4:i32 txLocalTime,
	5:i32 userID,
	6:string userName,
	7:double currentBalance,
	8:double amount,
	9:string agentID,
	10:string refID,
	11:string itemIDs,
	12:string itemNames,
	13:string itemPrices,
	14:string itemQuantities,
	15:string mac,
	16:i16 responseCode,
	17:string description,
	18:string clientIP,
}

struct T_Account {
	1:i32 userID,
	2:double currentBalance,
	3:double amount,
	4:i64 txID,
	5:i16 txType,
	6:string agentID,
}

struct T_Stat{
	1:string txID,
	2:string stat
}

struct T_Token{
	1:string pToken,
	2:i64 timestamp
}

enum TTokenResCode{
	ZC_TK_RESCODE_SUCCESS = 0,
	ZC_TK_RESCODE_UNKNOWN = 1,
	ZC_TK_RESCODE_EXPIRE = 2,
	ZC_TK_RESCODE_NOT_EXIST = 3,
}

struct T_AppInfo{
	1:string appID,
	2:string appName,
	3:string appDes,
	4:string appURL,
	5:string iconPath,
	6:string restURL,
	7:string key1,
	8:string key2,
	9:byte isEnabled,
}

struct T_TranStat{
	1:i64 txID,
	2:i16 txStatus,
	3:i16 resultCode,
	4:string message,
}

struct T_ReportTransaction{
	1:i64 txID,
	2:i16 txType,
	3:i32 txTime,
	4:i32 txLocalTime,
	5:double currentBalance,
	6:double amount,
	7:string agentID,
	8:string refID,
	9:i16 txStatus,
	10:i16 resultCode,
	11:string message,
	12:string itemIDs,
	13:string itemNames,
	14:string itemPrices,
	15:string itemQuantities,
	16:i32 userID,
	17:string userName,
	18:string lastUpdate,
}

struct T_ReportSummary{
	1:string agentID,
	2:string startTime,
	3:string endTime,
	4:i32 totalTransaction,
	5:i32 totalTransactionSuccess,
	6:i32 totalTransactionFail,
	7:double totalMoney,
	8:i32 totalTransactionTimeOut,
	9:i32 totalTransactionNetError,
	10:i32 totalDiffUser,
}
