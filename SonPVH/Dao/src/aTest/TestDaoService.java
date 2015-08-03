package aTest;

import balanceDao.BalanceDaoImpl;
import daoService.IDao;
import dto.BalanceDTO;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class TestDaoService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.println((int) (System.currentTimeMillis() / 1000));
        //   BalanceDTO dto0 = new BalanceDTO(10005, 1000, 0, 3, (int) (System.currentTimeMillis() / 1000), 1000, 1, TRANX_TYPE.TT_PUSH_MONEY, null);
        //  IDao<BalanceDTO> dao = BalanceDaoImpl.getInstance();
        // dao.insert(dto0);
//        Transaction tranx = new Transaction((long) 3, (short) 1, (int) (System.currentTimeMillis() / 1000),
//                1, 1, "mr.A", (double) 10000, (double) 1000, "agentA", null,
//                null, null, null, null, null, (short) 1, null, null, 1, (long) 1);
//        IDao<Transaction> dao = TransactionDaoImpl.getInstance();
//        dao.insert(tranx);
        int runs = 1;
        for (int i = 0; i < runs; i++) {
            Runnable task = new Runnable() {
                public int startIndex;
                IDao dao;

                public Runnable setStart(int startIndex) throws IOException, InterruptedException {
                    dao = BalanceDaoImpl.getInstance();
                    this.startIndex = startIndex;
                    return this;
                }

                @Override
                public void run() {
                    BalanceDTO balanceDTO = new BalanceDTO(101, 1000, 0, 3, (int) (System.currentTimeMillis() / 1000), 1000, 1, 102, null);
                    dao.insert(balanceDTO);
                }
            }.setStart(i);
            new Thread(task).start();
        }
    }
}
