package aTest;


import dto.Transaction;
import exchangeData.Serializator;
import exchangeData.TranxSerializator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Transaction tranx = new Transaction((long) 1, (short) 1, (int) (System.currentTimeMillis() / 1000), 1, "son",
                (double) 1000, (double) 1000, 1, null, null, null, null, null, null, (short) 1, null);




        System.out.println("tranx " + tranx.toString());

        Serializator<Transaction> a = TranxSerializator.getInstance();
        byte[] data = a.serialize(tranx);

        System.out.println(" data: " + data);

        tranx = (Transaction) a.deserialize(data);
        System.out.println(tranx.toString());

    }
}
