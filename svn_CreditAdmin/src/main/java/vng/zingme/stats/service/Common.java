package vng.zingme.stats.service;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.bo.thrift.LatestTranxCacheHandler;
import vng.zingme.payment.bo.thrift.ReportHandler;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.stats.mySqlConnectionPool.Config2;

public class Common {
	private static final Logger log = Logger.getLogger(Common.class);

	private static AppServiceHandler appHandler;
	private static ReportHandler reportHandler;
	private static LatestTranxCacheHandler tranxHandler;

	public static LatestTranxCacheHandler getTranxHandler() {
		return tranxHandler;
	}

	public static AppServiceHandler getAppHandler() {
		return appHandler;
	}

	public static ReportHandler getReportHandler() {
		return reportHandler;
	}

	static {
		try {
			appHandler = AppServiceHandler.getMainInstance(Config2.appinfohost,
					Config2.appinfoport);

			reportHandler = ReportHandler.getMainInstance(Config2.reporthost,
					Config2.reportport);

			tranxHandler = LatestTranxCacheHandler.getMainInstance(
					Config2.latestcachedhost, Config2.latestcachedport);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static String convertTranxType(short txType, double txAmount) {
		String type = "";
		switch (txType) {
		case CommonDef.TRANX_TYPE.TT_PUSH_MONEY:
			type = "NAPTIEN";
			break;
		case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_TELCO:
			type = "Nạp tiền thẻ cào";
			break;
		case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_TELCO_RECONCILE:
			type = " Hoàn tiền thẻ cào ";
			break;
		case CommonDef.TRANX_TYPE.TT_TRANSFER_MONEY:
			type = "Chuyển tiền";
			break;
		case 103:
			type = "Tăng tiền";
			break;
		case 201:
			type = "Thanh toán";
			break;
		case 202:
			type = "Hoàn tiền";
			break;
		case CommonDef.TRANX_TYPE.TT_COMPENSATE_CREDIT:
			if (txAmount > 0) {
				type = "Thêm tiền";
			} else {
				type = "Giảm tiền";
			}
			break;
		case CommonDef.TRANX_TYPE.TT_PUSH_MONEY_SMS:
			type = "Sms";
			break;
		default:
			type = String.valueOf(txType);
			break;
		}
		return type;
	}

	public static String decimalFormat(double dNumber) {
		NumberFormat nf = NumberFormat.getInstance();
		return nf.format(dNumber);

	}

}
