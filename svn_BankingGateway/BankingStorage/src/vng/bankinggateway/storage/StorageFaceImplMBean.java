package vng.bankinggateway.storage;

public interface StorageFaceImplMBean {

        long getTotalLogTx();

        long getTotalStoreTx();

        long getTotalHit();

        long getTotalUpdateBalance();

        double getUpdateRate();

        long getTotalUpdateBalanceFail();

        long getTotalUpdateDuplicate();

        double getUpdateFailPercentage();

        double getUpdateDupplicationPercentage();

        long getTpm();

        long getUpdatePM();

        double getLastTime();
}
