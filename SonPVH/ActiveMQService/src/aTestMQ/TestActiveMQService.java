package aTestMQ;

import balanceProducer.BalanceProducerImpl;
import dto.BalanceDTO;
import java.io.IOException;
import producerService.IProducer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class TestActiveMQService {

    public static void main(String[] args) throws IOException, InterruptedException {
        int runs = 4;
        for (int i = 0; i < runs; i++) {
            Runnable task;
            task = new Runnable() {
                public int startIndex;
                IProducer producer = new BalanceProducerImpl();

                public Runnable setStart(int startIndex) throws IOException, InterruptedException {
                    this.startIndex = startIndex;
                    return this;
                }

                @Override
                public void run() {
                    long start = System.nanoTime();
                    int countter = 5000;
                    for (int j = 0; j < countter; j++) {
                        long nodeId = startIndex * 100000 + (j + 12);

                        BalanceDTO dto = new BalanceDTO((int) nodeId++, 1000, 0, j,
                                (int) (System.currentTimeMillis() / 1000), j, j, 102, null);
                        producer.sendMessage(dto);

                    }
                    long endTime = System.nanoTime();
                    System.out.println("Total ms " + startIndex + " " + (endTime - start) / 1000000d);
                    double avarage = ((endTime - start) / 1000000d) / (double) countter;
                    System.out.println("Evarage ms " + startIndex + " " + avarage);
                    System.out.println("total reg for 1s " + startIndex + " " + (1000d / avarage));
                }
            }.setStart(i);
            new Thread(task).start();
        }
    }
}
