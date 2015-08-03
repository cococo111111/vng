/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daoService;

import java.util.Calendar;

/**
 *
 * @author root
 */
public abstract class DaoImpl< T> implements IDao< T> {

    protected static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("exception");
    private static final int MILISECINSEC = 1000;
    private static final long MILISECONDINDAY = 86400000L;
    private static int mth = 0, yr = 0;
    protected static String tableId;

    public DaoImpl() {
    }

    @Override
    public T find(int id) {
        return null;
    }

    /**
     * @sumary: get tableId(yyyyMM) depend on currentTime
     *
     * @param txLocalTime
     * @return
     */
    protected static String getTableId(int txLocalTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(txLocalTime * (long) (MILISECINSEC));
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        if (month == mth && year == yr) {
            return null;
        }
        mth = month;
        yr = year;

        String smonth = String.valueOf(month);
        if (month < 10) {
            smonth = "0" + smonth;
        }

        tableId = String.valueOf(year) + smonth;
        return tableId;
    }
}
