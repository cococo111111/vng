/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.client;

import vng.bankinggateway.stub.BankingGatewayServiceStub;

/**
 *
 * @author root
 */
public class BankingGatewayServiceHandler {

        private String url = "http://10.30.22.138:8080/axis2/services/BankingGatewayService";
        private static BankingGatewayServiceHandler instance = null;

        public BankingGatewayServiceHandler(String url) {
                this.url = url;
        }

        public static BankingGatewayServiceHandler getInstance(String url) {
                if (instance == null) {
                        instance = new BankingGatewayServiceHandler(url);
                }

                return instance;
        }

        public String callBankingGatewayService(String requestStr) {
                try {
                        BankingGatewayServiceStub stub = new BankingGatewayServiceStub(this.url);

                        BankingGatewayServiceStub.ReqExecute reqExecute = new BankingGatewayServiceStub.ReqExecute();
                        reqExecute.setReq(requestStr);
                        BankingGatewayServiceStub.ReqExecuteResponse reqExecuteResponse = stub.reqExecute(reqExecute);

                        return reqExecuteResponse.get_return();

                } catch (Exception ex) {
                        ex.printStackTrace();
                }

                return null;
        }
}
