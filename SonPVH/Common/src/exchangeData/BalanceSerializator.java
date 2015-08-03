/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeData;

import dto.Balance;

public class BalanceSerializator extends Serializator<Balance> {

    private static BalanceSerializator instance = null;

    public static BalanceSerializator getInstance() {
        if (instance == null) {
            instance = new BalanceSerializator();
        }
        return instance;
    }
}
