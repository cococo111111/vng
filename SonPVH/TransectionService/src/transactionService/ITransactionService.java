/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transactionService;

import dto.Transaction;

/**
 *
 * @author root
 */
public interface ITransactionService {

    public int createTranx(Transaction tranx);

    public int confirmSuccessTranx(Transaction tranx);

    public int removeTranx(Transaction tranx);
}
