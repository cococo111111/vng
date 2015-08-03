package com.vng.zing.credits.compensation.model;

import com.vng.jcore.common.Config;
import com.vng.zing.credits.compensation.util.Utils;
import com.vng.zing.credits.service.MysqlService;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.*;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.T_TranStat;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;
import vng.zingme.xcache2_payment.thrift.TDataResult;
import vng.zingme.xcache2_payment.thrift.TOperationPolicy;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class CompensationHandler {
    //4, 7 - DXL
    //106: timeout.

    private final static Logger _logger = Logger.getLogger(CompensationHandler.class);
    private final static String FILTER = "select tx.agentID, tx.txTime, tx.txID, tx.userID, tx.userName, tx.amount, tx.itemNames, tx.prices, tx.quantities from zingcredits.transactions_%tY%tm tx, zingcredits.transactions_status_%tY%tm   txs  where tx.lastUpdate>='%tY-%tm-%td 00:00' and tx.lastUpdate<='%tY-%tm-%td 23:59' and tx.txid=txs.txid and tx.txid!=17971598 and tx.txid!=17971213 GROUP BY tx.txid having sum(txs.txStatus) IN (%s) order by tx.agentID;";
    private final static String REASON = Config.getParam("COMPENSATION", "reason");
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static String getQuery(long milisecs, String status) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(milisecs);
        return String.format(FILTER, cl, cl, cl, cl, cl, cl, cl, cl, cl, cl, status);
    }
    private static DateFormat vdf = new SimpleDateFormat("HH:mm dd/MM/yyy");

    public static List<AdjustData> getCompensationList(String date, String status) {
        List<AdjustData> clist = new ArrayList<AdjustData>();
        Connection dbConnection = MysqlService.getInstance().getDbConnection();
        try {
            ResultSet rs = dbConnection.createStatement().executeQuery(getQuery(df.parse(date).getTime(), status));
            while (rs.next()) {
                AdjustData data = new AdjustData();
                data.setUserID(rs.getInt("userID"));
                data.setUserName(rs.getString("userName"));
                Date d = new Date((long) rs.getInt("txTime") * 1000);
                data.setReason(String.format(REASON, vdf.format(d), rs.getString("txID")));
                data.setAdjustMoney(rs.getDouble("amount"));
                data.setTime((int) (System.currentTimeMillis() / 1000));
                data.setTxID(rs.getLong("txID"));
                data.setItemsName(rs.getString("itemNames"));
                clist.add(data);
            }
        } catch (Exception ex) {
            _logger.error(ex);
        }
        MysqlService.getInstance().releaseDbConnection(dbConnection);
        return clist;
    }
    private static final String SIG = Config.getParam("COMPENSATION", "sig");
    private static final AdminHandler adHandler = AdminHandler.getMainInstance(Config.getParam("COMPENSATION", "host"), Integer.parseInt(Config.getParam("COMPENSATION", "port")));

    public static void processCompensaionList(List<AdjustData> data) {
        for (int i = 0; i < data.size(); ++i) {
            AdjustData ad = data.get(i);
            try {
                adHandler.adjustUser(ad.getUserID(), ad.getAdjustMoney(), SIG, ad.getReason(), "10.30.22.155", ad.getTime());
            } catch (Exception ex) {
                _logger.error(ad, ex);
            }
        }
    }
    private static final SerializeDeserializeHandler serderhandler = SerializeDeserializeHandler.getMainInstance();

    private static synchronized boolean updateTransactionStat(int userID, long tranxID, String desc, Vector<byte[]> _lastCache) {
        for (int i = 0; i < _lastCache.size(); ++i) {
            T_Transaction t_Transaction;
            byte[] element = _lastCache.get(i);
            t_Transaction = serderhandler.deserialize(element);
            if (t_Transaction != null && t_Transaction.userID == userID && t_Transaction.txID == tranxID/*
                     * && t_Transaction.responseCode ==
                     * CommonDef.TRANX_STAT.TS_INPROCESS
                     */) {
                _lastCache.removeElement(element);
                t_Transaction.responseCode = (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL;
                t_Transaction.description = desc;
                _lastCache.set(i, serderhandler.serialize(t_Transaction).clone());
                return true;
            }
        }
        return false;
    }

    private static synchronized Vector<byte[]> sortTranx(Vector<byte[]> _lastCache) {
        if (_lastCache.size() > 0) {
            Vector<byte[]> temp = new Vector<byte[]>(_lastCache);
            _lastCache.clear();
            for (int i = 0; i < temp.size(); ++i) {
                byte[] element = temp.get(i);
                T_Transaction t_Transaction = serderhandler.deserialize(element);
                byte[] el = null;
                int j;
                for (j = 0; j < _lastCache.size(); ++j) {
                    el = _lastCache.get(j);
                    T_Transaction cTranx = serderhandler.deserialize(el);
                    if (cTranx.getTxID() < t_Transaction.getTxID()) {
                        break;
                    }
                }
                _lastCache.insertElementAt(element, j);
            }
        }
        return _lastCache;
    }

    private static void printTranx(Vector<byte[]> _lastCache) {
        if (_lastCache.size() > 0) {
            for (int i = 0; i < _lastCache.size(); ++i) {
                byte[] element = _lastCache.get(i);
                T_Transaction t_Transaction = serderhandler.deserialize(element);
                System.out.println(t_Transaction);
            }
        }
    }
    private static XCache2Handler xcache = XCache2Handler.getMainInstance(Config.getParam("XCACHE", "host"), Integer.parseInt(Config.getParam("XCACHE", "port")));

    private static Object deserialize(String s)
            throws IOException, ClassNotFoundException {

        // byte[] bytes = StringUtil.HexStringToByteArray(s);
        byte[] bytes = Base64.decodeBase64(s.getBytes());

        return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
    }
    private static final ObjectSerialize objSerialize = new ObjectSerialize();
    private static final Object lockObj = new Object();

    static private String serialize(Object o) {
        String res = "";
        synchronized (lockObj) {
            res = objSerialize.serializeS(o);
        }
        return res;
    }

    private static Vector<byte[]> getDataFromXCache(int userID) {
        Vector<byte[]> res = null;
        try {
            TDataResult data = xcache.getData(String.valueOf(userID), TOperationPolicy.OPPOL_NONE);

            if (data.error < 0) {
                res = null;
            } else {
                res = (Vector<byte[]>) deserialize(data.data.value);
            }

        } catch (Exception ex) {
            _logger.error(ex);
            res = null;
        }

        return res;
    }
    static String auth = Config.getParam("XCACHE", "auth");

    private static void putXCache(int userID, Vector<byte[]> value) {
        try {
            xcache.put(auth, String.valueOf(userID), serialize(value), TOperationPolicy.OPPOL_INS_OR_UPD);
        } catch (Exception ex) {
            _logger.error(ex);
        }
    }

    public static void processUpdateCache(List<AdjustData> data) {
        for (int i = 0; i < data.size(); ++i) {
            AdjustData ad = data.get(i);
            try {
                Vector<byte[]> value = getDataFromXCache(ad.getUserID());
                if (value != null) {
                    updateTransactionStat(ad.getUserID(), ad.getTxID(), "Mua " + ad.getItemsName(), value);
                    putXCache(ad.getUserID(), value);
                }
            } catch (Exception ex) {
                _logger.error(ad, ex);
            }
        }
    }

    public static void processSortCache(int userID) {
        try {
            Vector<byte[]> value = getDataFromXCache(userID);
            if (value != null) {
                value = sortTranx(value);
                putXCache(userID, value);
                printTranx(value);
            }
        } catch (Exception ex) {
            _logger.error("userID: " + userID, ex);
        }
    }
    private static StorageHandler stHandler = StorageHandler.getMainInstance(Config.getParam("STORAGE", "host"), Integer.parseInt(Config.getParam("STORAGE", "port")));
    private static int HOPE_COUNT = 3;

    static private int tryUpdateTranxStat(T_TranStat ts) {
        int hope_count = HOPE_COUNT, res = PaymentReturnCode.DatabaseCode.DB_ERROR;
        while (hope_count > 0 && res != PaymentReturnCode.DatabaseCode.DB_SUCCESS) {
            try {
                res = stHandler.updateTransactionStatus(ts);
            } catch (TException ex) {
                _logger.warn(LogUtil.stackTrace(ex));
            }
            --hope_count;
        }
        return res;
    }

    public static void processUpdateStorage(List<AdjustData> data) {
        for (int i = 0; i < data.size(); ++i) {
            AdjustData ad = data.get(i);
            try {
                tryUpdateTranxStat(new T_TranStat(ad.getTxID(), (short) CommonDef.TRANX_STAT.TS_CLOSE_FAIL, (short) -1001, "Lỗi GD"));
            } catch (Exception ex) {
                _logger.error(ad, ex);
            }
        }
    }

    public static void printXCacheValue(int userID) {
        System.out.println("----" + userID + "----");
        try {
            Vector<byte[]> value = getDataFromXCache(userID);
            if (value != null) {
                printTranx(value);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void compensateUser(String adjustMoney) {
        try {
            String compansate_sig = Config.getParam("COMPENSATE", "sig");
            String appID = Config.getParam("COMPENSATE", "appID");
            String reason = Config.getParam("COMPENSATE", "reason");
            int userID = Integer.parseInt(Config.getParam("COMPENSATE", "userID"));
            int rs = adHandler.compensate(userID, Double.valueOf(adjustMoney), compansate_sig, reason, "10.30.22.155", (int) (System.currentTimeMillis() / 1000), appID, CommonDef.TRANX_TYPE.TT_COMPENSATE_CREDIT);
        } catch (Exception ex) {
            System.out.println("ex = " + ex.getMessage());
            _logger.error(ex);
        }

    }

    public static void processCompensaionListForgame(List<AdjustData> data) {
        AdjustData ad = null;
        try {
            String compansate_sig = Config.getParam("COMPENSATE", "sig");
            String appID = Config.getParam("COMPENSATE", "appID");
            int compensate_accountID = Integer.parseInt(Config.getParam("COMPENSATE", "userID"));
            OutputStreamWriter os = new FileWriter(appID + "_result.txt");
            for (int i = 0; i < data.size(); ++i) {
                ad = data.get(i);
                if (ad.getUserID() > 0) {
                    int rs = adHandler.compensate(compensate_accountID, 0 - ad.getAdjustMoney(), compansate_sig, ad.getReason(), "10.30.22.155", ad.getTime(), appID, CommonDef.TRANX_TYPE.TT_COMPENSATE_DEBIT);

                    if (rs == 0) {
                        rs = adHandler.compensate(ad.getUserID(), ad.getAdjustMoney(), compansate_sig, ad.getReason(), "10.30.22.155", ad.getTime(), appID, CommonDef.TRANX_TYPE.TT_COMPENSATE_CREDIT);
                        if (rs == 0) {
                            os.append("Success: " + ad.toString() + "\n");
                        } else {
                            os.append(rs + " Adjust Failed: " + ad.toString() + "\n");
                        }
                    } else {
                        os.append(rs + " Adjust Failed: " + ad.toString() + "\n");
                        break;
                    }
                } else {
                    os.append("-2: Not exist Username=" + ad.getUserName() + "\n");
                }
                os.flush();
            }
            os.close();
        } catch (Exception ex) {
            _logger.error(ex);
        }
    }

    public static List<AdjustData> getListFromCSV(String csvFilename, String regex) {
        BufferedReader br = null;
        List<AdjustData> result = new ArrayList<AdjustData>();
        try {
            br = new BufferedReader(new FileReader(csvFilename));
            String dataRow = "";
            while ((dataRow = br.readLine()) != null) {
                String[] dataArray = dataRow.split(regex);
                AdjustData adjustData = new AdjustData();

                String userID = dataArray[0];
                String userName = dataArray[1];
                if(userID.trim().length() == 0 && userName.trim().length() > 0) {
                    // co UserName
                    adjustData.setUserID(Utils.getMainInstance().getZingId(userName));
                    adjustData.setUserName(userName);
                } else if(userID.trim().length() > 0 && userName.trim().length() == 0) {
                    // co UserID
                    int iUserID = Integer.parseInt(userID);
                    adjustData.setUserID(iUserID);
                    adjustData.setUserName(Utils.getMainInstance().getZingName(iUserID));
                } else {
                    int iUserID = Integer.parseInt(userID);
                    adjustData.setUserID(iUserID);
                    adjustData.setUserName(userName);
                }

                adjustData.setAdjustMoney(Double.parseDouble(dataArray[2]));
                adjustData.setReason(dataArray[3]);
                adjustData.setTime((int) (System.currentTimeMillis() / 1000));
                result.add(adjustData);
                System.out.println(adjustData.toString());
            }
            br.close();
        } catch (IOException ex) {
            _logger.error(ex);
        }
        return result;
    }

    public static void compensationForGame(String inputFile) {
        String regex = System.getProperty("regex"); // , ;
        if (regex == null || "".equals(regex)) {
            regex = ";";
        }
        List<AdjustData> listdata = getListFromCSV(inputFile, regex);
        processCompensaionListForgame(listdata);
    }
    
    public static void processCompensaionListForCard(List<AdjustData> data) {
        AdjustData ad = null;
        try {
            String compansate_sig = Config.getParam("COMPENSATE", "sig");
            String appID = "zing";
            OutputStreamWriter os = new FileWriter(appID + "_result.txt");
            for (int i = 0; i < data.size(); ++i) {
                ad = data.get(i);
                if (ad.getUserID() > 0) {
                    int rs = adHandler.compensate(ad.getUserID(), ad.getAdjustMoney(), compansate_sig, ad.getReason(), "10.30.22.155", ad.getTime(), appID, CommonDef.TRANX_TYPE.TT_PUSH_MONEY_TELCO_RECONCILE);
                    if (rs == 0) {
                        os.append("Success: " + ad.toString() + "\n");
                    } else {
                        os.append(rs + " Adjust Failed: " + ad.toString() + "\n");
                    }
                } else {
                    os.append("-2: Not exist Username=" + ad.getUserName() + "\n");
                }
                os.flush();
            }
            os.close();
        } catch (Exception ex) {
            _logger.error(ex);
        }
    }
    
    public static void compensationForCard(String inputFile) {
        String regex = System.getProperty("regex"); // , ;
        if (regex == null || "".equals(regex)) {
            regex = ";";
        }
        List<AdjustData> listdata = getListFromCSV(inputFile, regex);
        processCompensaionListForCard(listdata);
    }
}
