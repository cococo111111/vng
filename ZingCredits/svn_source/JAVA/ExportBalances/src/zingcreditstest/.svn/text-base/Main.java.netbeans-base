/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zingcreditstest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.bo.thrift.StorageHandler;
import vng.zingme.payment.thrift.T_AccResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import vng.zingme.payment.common.config.ConfigUtil;
import vng.zingme.payment.model.util.Config;
import vng.zingme.payment.model.util.DBConnectionManager;
import vng.zingme.util.LogUtil;

/**
 *
 * @author root
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String config = System.getProperty("config", "");

        PropertyConfigurator.configure(config + "/log4j.properties");

        ConfigUtil.loadConfigFile(config + "/exportzingcreditsdata.config", "appActions");


        //active config
        Config.timeOut = 1001;
        exportBalances();
    }

    private static List<Integer> getListIDs() {
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(1000);
        } catch (Exception ex) {
            log.warn(ex);
            DBConnectionManager.getInstance().invalidateConnection(conn);
            return null;
        }

        if (conn == null) {
            log.warn("connection-db null!");
            return null;
        }

        LinkedList<Integer> res = new LinkedList<Integer>();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet executeQuery = stmt.executeQuery();
            if (executeQuery != null) {

                while (executeQuery.next()) {

                    int userID = executeQuery.getInt("userID");

                    res.add(userID);

                }
            }

            DBConnectionManager.getInstance().returnConnection(conn);
        } catch (Exception ex) {
            DBConnectionManager.getInstance().invalidateConnection(conn);
            log.warn(ex);
            res = null;
        }
        return res;
    }
    private static Logger log = Logger.getLogger("appActions");
    private static final String sql = "select * from zingcredits.accounts";

    private static void exportBalances() {

        List<Integer> listIDs = null;
        int hope_count = 20;
        while (hope_count > 0 && (listIDs == null || listIDs.size() <= 0)) {
            listIDs = getListIDs();
            --hope_count;
        }

        path = System.getProperty("export_path", "/data/zingcredits/export/balances");
        try {
            (new File(path)).mkdirs();
        } catch (Exception ex) {
            log.warn(LogUtil.stackTrace(ex));
        }

        if (listIDs == null || listIDs.size() <= 0) {
            log.warn("extract data from database fail!");
        } else {
            try {
                Writer output_cache = null;
                File file_cache = new File(path + File.separator + "balances_in_cache_" + nowString());
                output_cache = new BufferedWriter(new FileWriter(file_cache, true));

                Writer output_db = null;
                File file_db = new File(path + File.separator + "balances_in_db_" + nowString());
                output_db = new BufferedWriter(new FileWriter(file_db, true));

                Writer output_diff = null;
                File file_diff = new File(path + File.separator + "diffs_balance_" + nowString());
                output_diff = new BufferedWriter(new FileWriter(file_diff, true));

                for (Iterator<Integer> it = listIDs.iterator(); it.hasNext();) {
                    Integer integer = it.next();

                    hope_count = 3;
                    boolean ss = false;
                    while (hope_count > 0 && !ss) {

                        try {
                            T_AccResponse balance = bal.getBalance(integer);
                            double balance1 = st.getBalance(integer);

                            output_cache.write(integer + "\t" + balance.currentBalance + "\n");
                            output_db.write(integer + "\t" + balance1 + "\n");

                            if (Math.abs(balance.currentBalance - balance1) > esilon) {
                                output_diff.write(integer + "\t" + balance.currentBalance + "\t" + balance1 + "\t" + (balance.currentBalance - balance1) + "\n");
                            }

                            ss = true;
                            Thread.sleep(1);

                        } catch (Exception ex) {
                        }
                        --hope_count;
                    }
                }

                output_diff.close();

                output_db.close();
                output_cache.close();

            } catch (Exception ex) {
                log.error(ex);
            }
        }

    }
    private static BalanceCacheHandler bal = BalanceCacheHandler.getMainInstance("10.30.22.158", 5131);
    private static StorageHandler st = StorageHandler.getMainInstance("10.30.22.153", 9702);
    private static String path = "/data/zingcredits/export/balances";

    private static String nowString() {
        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String smonth = String.valueOf(month);
        if (month < 10) {
            smonth = "0" + smonth;
        }
        String sday = String.valueOf(day);
        if (day < 10) {
            sday = "0" + sday;
        }

        String result = "" + year + smonth + sday;

        return result;
    }
    private static final double esilon = 0.00001;
}
