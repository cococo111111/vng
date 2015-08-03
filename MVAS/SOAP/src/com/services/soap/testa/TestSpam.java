package com.services.soap.testa;

import com.services.soap.mo.MOMessage;
import com.services.soap.mo.MoneyCounter;
import com.services.soap.mo.Spam;

public class TestSpam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*String userID = "8432329";
		MoneyCounter moneyCounter = null;
		
		for (int i = 0; i < 10; i++) {
			moneyCounter = Spam.getInstance().getMoneyCounter(userID);
			MOMessage mOMessage = null;
			long now = System.currentTimeMillis();		
			if (moneyCounter != null){
				if (moneyCounter.getCount() > Spam.SPAM_MAX_SEND){
					if (now - moneyCounter.getFirstMOMessage().getTime()  <= Spam.SPAM_DURATION_CHECK ){
						// return spam message
						System.out.println("i=" + i + "spam message");
					}else{
						moneyCounter.dequeueMOMessage();
						mOMessage = new MOMessage();
						mOMessage.setTime(now);
						moneyCounter.enqueueMOMessage(mOMessage);
					}
				}else{
					mOMessage = new MOMessage();
					mOMessage.setTime(now);
					moneyCounter.enqueueMOMessage(mOMessage);
				}				
			}else{
				moneyCounter = new MoneyCounter();
				mOMessage = new MOMessage();
				mOMessage.setTime(now);
				moneyCounter.enqueueMOMessage(mOMessage);
			}
			Spam.getInstance().putMoneyCounter(userID, moneyCounter);
		}*/
		String commandCode = "vng11";
		String infor = "";
		if ( !commandCode.equalsIgnoreCase(infor.split(" ")[0].trim()) ){
			System.out.print("Tin nhan sai cu phap");
		}

	}

}
