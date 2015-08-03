/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package balanceZoo;

import zooService.IZoo;
import dto.Balance;

/**
 *
 * @author root
 */
public interface IBalanceZoo extends IZoo<Balance> {

    public boolean add(Balance t, int balanceType, String path);

    public boolean deduct(Balance t, int balanceType, String path);
}
