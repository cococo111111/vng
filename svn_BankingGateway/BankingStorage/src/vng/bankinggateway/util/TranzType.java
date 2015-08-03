package vng.bankinggateway.util;

public class TranzType {
	private static String TABLE_NAME = "tx_type";
	private static String TXID = "tti";
	private static String TTI = "name";
	private static byte TX_BALANCE = 10;
	private static byte TX_BILLING = 20;
	private static byte TX_GIFT = 30;
	private static byte TX_REVERSAL = 40;
	private static byte TX_TRANSFER = 50;

	public static boolean isReversal(byte tti) {
		return (tti == TX_REVERSAL);
	}

	public static boolean isBilling(byte tti) {
		return tti == TX_BILLING;
	}

	public static boolean isBalance(byte tti) {
		return tti == TX_BALANCE;
	}

	public static boolean isGift(byte tti) {
		return tti == TX_GIFT;
	}

	public static boolean isTransfer(byte tti) {
		return tti == TX_TRANSFER;
	}
	

}
