package com.vng.zing.credits.tranxfilter.model;

import com.vng.zing.credits.service.MysqlService;
import hapax.TemplateDataDictionary;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import org.apache.log4j.Logger;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class Filter {

    final static Logger _logger = Logger.getLogger(Filter.class);
    final static String FILTER = "select tx.agentID, tx.txTime, tx.txID, tx.amount, tx.itemNames, tx.prices, tx.quantities from zingcredits.transactions_%tY%tm tx, zingcredits.transactions_status_%tY%tm   txs  where tx.lastUpdate>='%tY-%tm-%td 00:00' and tx.lastUpdate<='%tY-%tm-%td 23:59' and tx.txid=txs.txid GROUP BY tx.txid having sum(txs.txStatus) IN (4, 7) order by tx.agentID;";
    
    public static String getFilter(long milisecs) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(milisecs);
        return String.format(FILTER, cl, cl, cl, cl, cl, cl, cl, cl, cl, cl);
    }

    public static void getTranx(TemplateDataDictionary dic, long milisecs) {
        Connection dbConnection = MysqlService.getInstance().getDbConnection();
        try {
            ResultSet rs = dbConnection.createStatement().executeQuery(getFilter(milisecs));
            TemplateDataDictionary trans;
            while (rs.next()) {
                trans = dic.addSection("TRANS");
                trans.setVariable("agentID", rs.getString("agentID"));
                trans.setVariable("txTime", rs.getString("txTime"));
                trans.setVariable("txID", rs.getString("txID"));
                trans.setVariable("amount", rs.getString("amount"));
                trans.setVariable("itemNames", rs.getString("itemNames"));
                trans.setVariable("prices", rs.getString("prices"));
                trans.setVariable("quantities", rs.getString("quantities"));    
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        MysqlService.getInstance().releaseDbConnection(dbConnection);
    }
}
