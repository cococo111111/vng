/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.paymentreport;

import java.util.LinkedList;
import java.util.List;
import org.apache.thrift.TException;
import vng.zingme.payment.thrift.TReport;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;

/**
 *
 * @author root
 */
public class PaymentReportHandler implements TReport.Iface {

    PaymentReportModel _model = null;

    public PaymentReportHandler() {
        _model = new PaymentReportModel();
    }

    public List<T_ReportTransaction> getTransByApp(String appID, String startTime, String endTime, int tranxType, int startIndex, int numRow, final int txStatus) throws TException {
        List<T_ReportTransaction> res = null;
        int hop_count = 3;
        while (hop_count > 0 && res == null) {
            res = _model.getTransByApp(appID, startTime, endTime, tranxType, startIndex, numRow, txStatus);
            --hop_count;
        }
        if (res != null) {
            return res;
        }
        return new LinkedList<T_ReportTransaction>();
    }

    public List<T_ReportTransaction> getTransByUser(int userID, long txID, String startTime, String endTime, int startIndex, int numRow, final int txStatus) throws TException {
        List<T_ReportTransaction> res = null;
        int hop_count = 3;
        while (hop_count > 0 && res == null) {
            res = _model.getTransByUser(userID, txID, startTime, endTime, startIndex, numRow, txStatus);
            --hop_count;
        }
        if (res != null) {
            return res;
        }
        return new LinkedList<T_ReportTransaction>();
    }

    public List<T_ReportTransaction> getTransStatus(long txID, int localTime) throws TException {
        List<T_ReportTransaction> res = null;
        int hop_count = 3;
        while (hop_count > 0 && res == null) {
            res = _model.getTransStatus(txID, localTime);
            --hop_count;
        }
        if (res != null) {
            return res;
        }
        return new LinkedList<T_ReportTransaction>();
    }

    public T_ReportSummary summary(final String appID, final String startTime, final String endTime) throws TException {
        T_ReportSummary res = null;
        int hop_count = 3;
        while (hop_count > 0 && res == null) {
            res = _model.summary(appID, startTime, endTime);
            --hop_count;
        }
        if (res != null) {
            return res;
        }
        return new T_ReportSummary();
    }

    public List<T_ReportSummary> summaryDaily(String appID, final String startTime, final String endTime) throws TException {
        List<T_ReportSummary> res = null;
        int hop_count = 3;
        while (hop_count > 0 && res == null) {
            res = _model.summaryDaily(appID, startTime, endTime);
            --hop_count;
        }
        if (res != null) {
            return res;
        }
        return new LinkedList<T_ReportSummary>();
    }
}
