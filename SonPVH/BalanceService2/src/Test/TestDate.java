/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Common.BalanceDTO;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class TestDate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Date date = getDate("2013-11-01");
        System.out.println(date.getMonth());
    }

    private static java.sql.Date getDate(String sDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date udate = null;
        java.sql.Date date = null;
        try {
            udate = formatter.parse(sDate);
            System.out.println("ulti_date :" + udate.getMonth());
            date = new java.sql.Date(udate.getTime());
            System.out.println("sql_date :" + date.getMonth());
//            udate = formatter.parse(sDate);
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(udate);
//            int month = cal.get(Calendar.MONTH);
//            System.out.println("month cal " + month);
        } catch (ParseException ex) {
            Logger.getLogger(ConcurrenHashMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
