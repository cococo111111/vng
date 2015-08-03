/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balanceService;

import dto.Balance;
import dto.BalanceDTO;

/**
 *
 * @author root
 */
public interface IBalanceService {

    public boolean addMoney(BalanceDTO balanceDto);

    public boolean deductMoney(BalanceDTO balanceDto);

    public Balance getBalancebyId(int Id);
}
