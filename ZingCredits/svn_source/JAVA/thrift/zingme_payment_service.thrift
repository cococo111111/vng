include "zingme_payment_type.thrift"

namespace java vng.zingme.payment.thrift
namespace php vng_zingme_payment_thrift

service TPayment{
	zingme_payment_type.T_Response billing(1: zingme_payment_type.T_Transaction tx, 2: zingme_payment_type.T_Token tk),
	zingme_payment_type.T_Response bill(1: string agentID, 2: string encodedData, 3: zingme_payment_type.T_Token tk, 4: string clientIP),
	oneway void warmupCache(1:i32 userID),
	zingme_payment_type.T_AccResponse getBalance(1:i32 userID),
	// zingme_payment_type.T_Transaction parseTransaction(1: string agentID, 2: string encodedData),
	list<string> zingUnSignature(1: string agentID, 2: string encodedData, 3:i32 kindofkey),
}

service TBalanceCaching{
	zingme_payment_type.T_AccResponse add(1:zingme_payment_type.T_Account accBalance),
	zingme_payment_type.T_AccResponse deduct(1: zingme_payment_type.T_Transaction tx),
	void warmupCache(1:i32 userID),
	zingme_payment_type.T_AccResponse getBalance(1:i32 userID),
}

service TStatCaching{
	i32 check(1:zingme_payment_type.T_Stat stat),
	i32 put(1:zingme_payment_type.T_Stat stat),
	i32 remove(1:zingme_payment_type.T_Stat stat)
}

service TStorage{
  	i32 storeTx(1: zingme_payment_type.T_Transaction tx),
	i32 updateBalance(1:zingme_payment_type.T_Account accBalance),
	double getBalance(1:i32 userID),
	i32 updateTransactionStatus(1:zingme_payment_type.T_TranStat tranxStat),
}

service TZKBackEnd{
	oneway void tranxComing(1:i64 tranxID),
	i32 recoveryMissData(1:string adminSig),
	i32 rollbackTransaction(1:i64 tranxID),
	i32 retryTransaction(1:i64 tranxID),
}

service TLatestCache{
	i32 put(1:zingme_payment_type.T_Transaction tx),
	list<zingme_payment_type.T_Transaction> get(1: i32 userID),
	i32 updateStat(1:zingme_payment_type.T_Transaction tx),
	i32 updateTransactionStat(1:i32 userID, 2:i64 tranxID, 3:i32 stat, 4:string description,5:double currentbanlce),
	i32 removeTransaction(1:i32 userID, 2:i64 tranxID),
	zingme_payment_type.T_Transaction getTransaction(1:i32 userID, 2: string agentID, 3: string billNo),
	zingme_payment_type.T_Transaction getTransactionByTxID(1:i32 userID, 2: i64 txID),
	i32 getTransactionStatus(1:i32 userID, 2: i64 txID),
}

service TToken{
	i32 checkToken(1: zingme_payment_type.T_Token tk),
  	zingme_payment_type.T_Token getToken(1: i32 userID),
}

service TAppServer{
	string getAppName(1:string appID),
	zingme_payment_type.T_AppInfo getAppInfo(1:string appID),

	string registerGameServer(1:zingme_payment_type.T_AppInfo appInfo, 2:string adminSig, 3: bool isNewkey),
	i32 registerPayLetterServer(1:zingme_payment_type.T_AppInfo appInfo, 2:string adminSig),

	list<zingme_payment_type.T_AppInfo> getAllAppInfo(),
}

service TAdminServer{
	i32 adjustUser(1:i32 userID, 2:double adjustMoney, 3:string adminSig, 4:string reason, 5:string clientIP, 6:i32 time),
}

service TReport{
	list<zingme_payment_type.T_ReportTransaction> getTransByApp(1:string appID, 2:string startTime, 3:string endTime, 4:i32 tranxType, 5:i32 startIndex, 6:i32 numRow, 7:i32 txStatus),
	list<zingme_payment_type.T_ReportTransaction> getTransByUser(1:i32 userID, 2:i64 txID, 3:string startTime, 4:string endTime, 5:i32 startIndex, 6:i32 numRow, 7:i32 txStatus),
	list<zingme_payment_type.T_ReportTransaction> getTransStatus(1:i64 txID, 2:i32 localTime),

	zingme_payment_type.T_ReportSummary summary(1:string appID, 2:string startTime, 3:string endTime),
	list<zingme_payment_type.T_ReportSummary> summaryDaily(1:string appID, 2:string startTime, 3:string endTime),
}
