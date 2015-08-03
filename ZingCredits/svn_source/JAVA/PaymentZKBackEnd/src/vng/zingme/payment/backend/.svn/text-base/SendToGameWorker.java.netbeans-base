/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.backend;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;
import java.util.concurrent.ArrayBlockingQueue;
import org.apache.log4j.Logger;
import vng.zingme.payment.amq.LogAMQueueProducer;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.common.ScriberUtil;
import vng.zingme.payment.common.worker.IWorkerRunable;
import vng.zingme.payment.gamereturn.GameServiceClient;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class SendToGameWorker implements IWorkerRunable {

    private static ArrayBlockingQueue<T_Transaction> sendGameWorkerQueue = null;
    private static LogAMQueueProducer logProducer = new LogAMQueueProducer();

    public SendToGameWorker() {
        int _updateCacheQueueSize = Integer.parseInt(System.getProperty("sendgame_queuesize", "400000"));
        sendGameWorkerQueue = new ArrayBlockingQueue<T_Transaction>(_updateCacheQueueSize, true);
    }

    public void run() {
        while (!stoped) {
            try {
                T_Transaction tranx = sendGameWorkerQueue.take();
                if (tranx != null) {
                    createEncodedData(tranx);
                    if (tranx.mac.equals("")) {
                        pushJob(tranx);
                    } else {
                        logTransaction(tranx);
                        doSendToGame(tranx);
                    }
                }
            } catch (InterruptedException ex) {
                log.warn(ex.getMessage());
            }

        }
    }

    public void stop() {
        stoped = true;
    }
    static boolean stoped = false;

    public void pushJob(Object obj) {
        try {
            sendGameWorkerQueue.put((T_Transaction) obj);
        } catch (InterruptedException ex) {
            log.info(ex.getMessage());
        }
    }
    private static final int RETRY_COUNT = 3;
    private static final Logger log = Logger.getLogger("appActions");
    private static final GameServiceClient _gameService = new GameServiceClient();
    private static final Logger datalog = Logger.getLogger("dataActions");
    private static final Logger updatefaillog = Logger.getLogger("updatefailActions");

    private void doSendToGame(T_Transaction tranx) {
        GameResponse gRes = new GameResponse(PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT, "default result");

        for (int i = 0; i < RETRY_COUNT && (gRes.code == PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT); ++i) {

            gRes = _gameService.sendReturnGame(tranx);
        }

        if (gRes.code >= PaymentReturnCode.GameCode.G_SUCCESS) {

            ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS, (short) gRes.code, ""));

            ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS, gRes.des, tranx.currentBalance));

            tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_SUCCESS;
            String s_data_log = ScriberUtil.logme(tranx, gRes.code);

            try {

                ScriberUtil.getScribe().log(s_data_log);
                // log payment
                logProducer.enAMQueue(tranx);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }

            try {
                datalog.info(s_data_log);
            } catch (Exception ex) {
                log.warn(LogUtil.stackTrace(ex));
            }

        } else {
            if (gRes.code == PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT) {

                ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT, (short) gRes.code, ""));

                ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT, gRes.des, tranx.currentBalance));

                tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_TIME_OUT;
                String s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_NET_ERROR);

                try {

                    ScriberUtil.getScribe().log(s_data_log);
                    // log payment
                    logProducer.enAMQueue(tranx);
                } catch (Exception ex) {
                    log.warn(LogUtil.stackTrace(ex));
                }

                try {
                    datalog.info(s_data_log);
                } catch (Exception ex) {
                    log.warn(LogUtil.stackTrace(ex));
                }

                String s_fail_log = s_data_log + "\t" + gRes.des;
                try {
                    updatefaillog.info(s_fail_log);
                } catch (Exception ex) {
                    log.warn(LogUtil.stackTrace(ex));
                }

            } else {

                if (ZKBackEndMainWorker.getInstance().doJob(tranx.txID, true, true) != PaymentReturnCode.ERROR_OPERATOR_FAIL) {
                    if (gRes.code == PaymentReturnCode.GameCode.G_ERROR_NETWORK_FAIL
                            || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_COMPATIBLE
                            || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_FOUND_SERVICE
                            || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_SERVER
                            || gRes.code == PaymentReturnCode.GameCode.G_ERROR_NOT_SERVER
                            || gRes.code == PaymentReturnCode.GameCode.G_ERROR_SERVICE_INVALID
                            || gRes.code == PaymentReturnCode.GameCode.G_ERROR_UNKNOWN) {
                        ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK, (short) gRes.code, ""));

                        ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK, gRes.des, tranx.currentBalance, gRes.code));

                        tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_EX_NETWORK;
                        String s_data_log = ScriberUtil.logme(tranx, gRes.code);

                        try {

                            ScriberUtil.getScribe().log(s_data_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                        try {
                            datalog.info(s_data_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                        String s_fail_log = s_data_log + "\t" + gRes.des;
                        try {
                            updatefaillog.info(s_fail_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                    } else {
                        ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL, (short) gRes.code, gRes.des));

                        ZKBackEndMainWorker.getInstance().getUpdatecacheworker().work(new UpdateCacheWorkerData(tranx.agentID, tranx.userID, tranx.txID, CommonDef.TRANX_STAT.TS_CLOSE_FAIL, gRes.des, tranx.currentBalance));

                        tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL;
                        String s_data_log = ScriberUtil.logme(tranx, gRes.code);

                        try {

                            ScriberUtil.getScribe().log(s_data_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                        try {
                            datalog.info(s_data_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }

                        String s_fail_log = s_data_log + "\t" + gRes.des;
                        try {
                            updatefaillog.info(s_fail_log);
                        } catch (Exception ex) {
                            log.warn(LogUtil.stackTrace(ex));
                        }
                    }
                }
            }
        }

        //recycle path in zookeeper
        ZKBackEndMainWorker.getInstance().getRecyclezkworker().work(ZookeeperListenerManager.getMainInstance().getParentTranxPath() + CommonDef.FILE_SEP + tranx.txID);
    }

    private void createEncodedData(T_Transaction tranx) {
        String key = ZKBackEndMainWorker.getKey(tranx.agentID, 2);

        if (key == null) {
            tranx.mac = "";
            return;
        }

        String mac = "";

        std__vectorT_std__string_t params = new std__vectorT_std__string_t();
        // params.add(tranx.agentID);
        // params.add(tranx.userName);
        params.add(String.valueOf(tranx.userID));
        params.add(tranx.refID);
        params.add(tranx.itemIDs);
        params.add(tranx.itemNames);
        params.add(tranx.itemQuantities);
        params.add(tranx.itemPrices);
        params.add(String.valueOf(tranx.amount));
        params.add(String.valueOf(tranx.txTime));
        params.add(String.valueOf(tranx.txID));
        zcommon_StringHolder k = new zcommon_StringHolder();
        k.setValue(key);
        zcommon_StringHolder data = new zcommon_StringHolder();
        //encode
        int e = ZCypher.zma_encode(data, params, k, 0, 0);

        mac = data.getValue();

        tranx.mac = mac;
    }

    private void logTransaction(T_Transaction tranx) {
        ZKBackEndMainWorker.getInstance().tryUpdateTranxStat(new T_TranStat(tranx.txID, (short) CommonDef.TRANX_STAT.TS_REST_REQUESTING, CommonDef.TRANX_RES_CODE.TC_INPROCESS, ""));

        tranx.responseCode = (short) CommonDef.TRANX_STAT.TS_REST_REQUESTING;
        String s_data_log = ScriberUtil.logme(tranx, CommonDef.TRANX_RES_CODE.TC_INPROCESS);

        try {
            ScriberUtil.getScribe().log(s_data_log);
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }

        try {
            datalog.info(s_data_log);
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }
    }
}
