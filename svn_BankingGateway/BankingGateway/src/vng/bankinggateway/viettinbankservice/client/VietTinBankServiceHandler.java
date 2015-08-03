/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.viettinbankservice.client;

import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.util.InitUtil;
import vng.bankinggateway.viettinbankservice.stub.MerchantSvcStub;

/**
 *
 * @author root
 */
public class VietTinBankServiceHandler {

    private String url = "";
    private static VietTinBankServiceHandler instance = null;
    private static Logger log = Logger.getLogger("systemActions");

    public VietTinBankServiceHandler(String url) {
        this.url = url;
    }

    public static VietTinBankServiceHandler getInstance(String url) {
        if (instance == null) {
            instance = new VietTinBankServiceHandler(url);
        }

        return instance;
    }

    public String callMerchantSvcService(String cmdCode, String requestStr) {
        try {
            MerchantSvcStub stub  = new MerchantSvcStub(this.url);
            
            HttpTransportProperties.ProxyProperties proxyProperties =
                    new HttpTransportProperties.ProxyProperties();
            proxyProperties.setProxyName(InitUtil.PROXY_HOST);
            proxyProperties.setProxyPort(InitUtil.PROXY_PORT);
            stub._getServiceClient().getOptions().setProperty(HTTPConstants.HTTP_PROTOCOL_VERSION, HTTPConstants.HEADER_PROTOCOL_10);

            if (!InitUtil.PROXY_HOST.contains("noproxy")) {
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.PROXY, proxyProperties);
            }
            MerchantSvcStub.Execute excute = new MerchantSvcStub.Execute();
            excute.setCmdCode(cmdCode);
            excute.setStrData(requestStr);
            
            MerchantSvcStub.ExecuteResponse executeResponse = stub.execute(excute);
            return executeResponse.getExecuteResult();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return CommonDef.INVALID_CONNECTION;
        }
    }
}
