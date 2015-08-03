
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeData;

import dto.Transaction;

public class TranxSerializator extends Serializator<Transaction> {

    private static TranxSerializator instance = null;

    public static TranxSerializator getInstance() {
        if (instance == null) {
            instance = new TranxSerializator();
        }
        return instance;
    }
}
