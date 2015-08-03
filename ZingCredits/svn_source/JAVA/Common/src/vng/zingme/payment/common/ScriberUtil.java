/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.common;

import vng.wte.core.scribe.ScribeClientManager;
import vng.zingme.payment.thrift.T_Transaction;

/**
 *
 * @author root
 */
public class ScriberUtil {

    public static ScribeClientManager getScribe() {
        return ScribeClientManager.getInstance(vng.wte.core.scribe.Config.HOST,
                vng.wte.core.scribe.Config.PORT);
    }

    public static String logme(T_Transaction tranx, int resultCode) {
        StringBuilder buffer = new StringBuilder();

        final String SEPARATE = "\t";

        buffer.append(tranx.clientIP).append(SEPARATE).append(tranx.userID).append(SEPARATE).append(tranx.userName).append(SEPARATE).append(tranx.currentBalance).append(SEPARATE).append(tranx.agentID).append(SEPARATE).append(tranx.txID).append(SEPARATE).append(tranx.txType).append(SEPARATE).append(tranx.txTime).append(SEPARATE).append(tranx.txLocalTime).append(SEPARATE).append(tranx.refID).append(SEPARATE).append(tranx.itemIDs).append(SEPARATE).append(tranx.itemNames).append(SEPARATE).append(tranx.itemPrices).append(SEPARATE).append(tranx.itemQuantities).append(SEPARATE).append(tranx.amount).append(SEPARATE).append(tranx.responseCode).append(SEPARATE).append(resultCode);

        return buffer.toString();
    }
}
