/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.gamereturn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import vng.zingme.payment.backend.GameResponse;
import vng.zingme.payment.backend.ZKBackEndMainWorker;
import vng.zingme.payment.common.CommonDef;
import vng.zingme.payment.common.PaymentReturnCode;
import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.util.LogUtil;

/**
 * @author root
 * @version 1.0: modify to support https and character data instead of binary data
 *      (Difference between DataInputStream and BufferedReader)
 */
public class GameServiceClient {

    public GameServiceClient() {
    }

    public GameResponse sendReturnGame(T_Transaction tranx) {
        return sendReturnGameREST(tranx.refID, tranx.txID, tranx.agentID, 
                tranx.amount,  tranx.itemIDs, tranx.itemQuantities, tranx.itemPrices, 
                tranx.userName, tranx.mac, tranx.txTime, tranx.itemNames);
    }

    public GameResponse sendReturnGameREST(String billNo, long txID, String agentID, 
            double amount, String itemIDs, String itemQuantities, String amounts, 
            String userName, String mac, int txTime, String itemNames) {

        String gameServiceUrl = null;

        gameServiceUrl = ZKBackEndMainWorker.getRESTURL(agentID);

        if (gameServiceUrl == null) {
            String msg = "[" + GameServiceClient.class.getName() + "] Object gameServiceUrl is null!";
            log.warn(msg);
            return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_NOT_FOUND_SERVICE, 
                    CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_FOUNT);
        }

        URL url = null;
        try {
            String strUrl = gameServiceUrl + "data=" + mac;
            log.info("billNo:" + billNo + "; appID: " + agentID + "; URL: " + strUrl);
            url = new URL(strUrl);
        } catch (MalformedURLException ex) {
            log.info("WRONG FORMAT OF REST URL: billNo:" + billNo + "; appID: " + agentID + "; URL: " + gameServiceUrl + "data=" + mac);
            log.warn(ex.getMessage());
            return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_SERVICE_INVALID, 
                    CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_VALID);
        }

        if (url == null) {
            String msg = "[" + GameServiceClient.class.getName() + "] Object url is null!";
            // System.out.println(msg);
            log.warn(msg);
            return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_NOT_FOUND_SERVICE, 
                    CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_FOUNT);
        }

        URLConnection conn = null;
        try {
            conn = url.openConnection();
            conn.setConnectTimeout(Integer.valueOf(System.getProperty("url_connetion_timeout"))); // 30 seconds
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            log.error("COULD NOT OPEN CONNECTION OF REST URL: billNo:" + billNo + "; appID: " + agentID + "; URL: " + gameServiceUrl + "data=" + mac);
            return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_SERVICE_INVALID, 
                    CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_VALID);
        }

        if (conn == null) {
            String msg = "[" + GameServiceClient.class.getName() + "] Object conn is null!";
            log.warn(msg);

            return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_NOT_COMPATIBLE, 
                    CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_COMPATIBLE);

        } else {
            int resCode = PaymentReturnCode.GameCode.G_ERROR_UNKNOWN;
            StringTokenizer tok = null;
            String sResCode = "";
            StringBuilder data = new StringBuilder();

            // get data from InputStream via BufferedReader
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                while ((line = in.readLine()) != null) {
                    data.append(line);
                }
                in.close();
            } catch (IOException ex) {
                if(in != null) {
                    try {
                        in.close();
                    } catch (IOException ex1) {
                    }
                }
                log.warn(LogUtil.stackTrace(ex));
                if (ex.getMessage().startsWith("Server returned HTTP response code: 500 for URL") 
                        || ex.getMessage().startsWith("Server returned HTTP response code: 503 for URL")) {//time-out
                    log.error("TIMEOUT -> billNo:" + billNo + "; appId: " + agentID + "; REST data: " + data.toString());
                    return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_RESPONSE_TIMEOUT, 
                            CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_TIMEOUT);
                } else {
                    log.error("IOException -> billNo:" + billNo + "; appId: " + agentID + "; REST data: " + data.toString());
                    return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_SERVICE_INVALID, 
                            CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_VALID);
                }
            }

            try {
                log.info("billNo:" + billNo + "; appId: " + agentID + "; REST data: " + data.toString());
                tok = new StringTokenizer(data.toString(), ":");
                sResCode = tok.nextToken();
                resCode = Integer.parseInt(sResCode.trim());
                return new GameResponse(resCode, (tok.hasMoreTokens()) ? tok.nextToken() : " ");

            } catch (Exception ex) {
                log.warn("Can not parse the retcode for appId: " + agentID + "; ResCode: " + sResCode + "; REST data: " + data.toString());
                log.warn(LogUtil.stackTrace(ex));

                //not-compatible format
                return new GameResponse(PaymentReturnCode.GameCode.G_ERROR_NOT_COMPATIBLE, 
                        CommonDef.TRANX_FAIL_DESCRIPTION.SERVICE_NOT_COMPATIBLE);
            }
        }
    }
    private static final Logger log = Logger.getLogger("appActions");
}
