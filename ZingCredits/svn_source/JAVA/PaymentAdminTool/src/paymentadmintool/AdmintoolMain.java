/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentadmintool;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.AdminHandler;
import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.bo.thrift.BackEndHandler;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 *
 * @author root
 */
public class AdmintoolMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        byte a = new Byte("1");
        boolean b = (a == 1) ? true : false;
        if(b) {
            System.out.println(" TRUE " + a);
        } else {
            System.out.println(" FALSE " + a);
        }
//        // TODO code application logic here
//        if (args == null || args.length < 1) {
//            return;
//        }
//
//        if (args[0].equalsIgnoreCase("recoverBilling")) {
//            if (args.length < 2) {
//                return;
//            }
//            try {
//                BackEndHandler.getMainInstance("localhost", 4118).recoveryMissData(args[1]);
//            } catch (TException ex) {
//                log.log(Level.SEVERE, null, ex);
//            }
//        }
//
//        if (args[0].equalsIgnoreCase("regG")) {
//            if (args.length < 9) {
//                return;
//            }
//
//            T_AppInfo appInfo = new T_AppInfo();
//            appInfo.appID = args[1];
//            appInfo.appName = args[2];
//            appInfo.appDes = args[3];
//            appInfo.appURL = args[4];
//            appInfo.iconPath = args[5];
//            appInfo.restURL = args[6];
//            appInfo.isEnabled = Byte.parseByte(args[7]);
//
//            String s = "";
//            try {
//                s = AppServiceHandler.getMainInstance("10.30.22.138", 10114).registerGameServer(appInfo, args[8], true);
//            } catch (TException ex) {
//                log.log(Level.SEVERE, null, ex);
//            }
//            System.out.println(s);
//        }
//
//        if (args[0].equalsIgnoreCase("regP")) {
//            if (args.length < 9) {
//                return;
//            }
//
//            T_AppInfo appInfo = new T_AppInfo();
//            appInfo.appID = args[1];
//            appInfo.appName = args[2];
//            appInfo.appDes = args[3];
//            appInfo.appURL = args[4];
//            appInfo.iconPath = args[5];
//            appInfo.restURL = args[4];
//            appInfo.key1 = args[6];
//            appInfo.isEnabled = Byte.parseByte(args[7]);
//
//            int res = -1;
//            try {
//                res = AppServiceHandler.getMainInstance("10.30.22.135", 10114).registerPayLetterServer(appInfo, args[8]);
//            } catch (TException ex) {
//                log.log(Level.SEVERE, null, ex);
//            }
//            System.out.println(res);
//        }

    }
    private static final Logger log = Logger.getLogger(AdmintoolMain.class.getName());
}
