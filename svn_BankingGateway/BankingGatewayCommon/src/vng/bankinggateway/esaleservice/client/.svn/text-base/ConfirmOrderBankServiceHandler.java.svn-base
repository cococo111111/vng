/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.bankinggateway.esaleservice.client;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.CheckAgency;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.CheckAgencyResponse;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.ConfirmOrder;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.ConfirmOrderResponse;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.Topup;
import vng.bankinggateway.esaleservice.stub.ConfirmOrderBankServiceStub.TopupResponse;
import vng.bankinggateway.common.util.CommonDef;
import vng.bankinggateway.common.util.Encryption;

/**
 *
 * @author root
 */
public class ConfirmOrderBankServiceHandler {

    private String URL = "http://10.30.17.193:9001/ConfirmOrderBankService.asmx";
    private static ConfirmOrderBankServiceHandler instance = null;
    private static Logger log = Logger.getLogger("serviceActions");

    public static ConfirmOrderBankServiceHandler getInstance(String url) {
        if (instance == null) {
            instance = new ConfirmOrderBankServiceHandler(url);
        }

        return instance;
    }

    public ConfirmOrderBankServiceHandler(String url) {
        this.URL = url;
    }

    public int confirmOrderBankService(String agencyCode, String orderNo,
            String responseCode, String txID, String bankCode) throws AxisFault,
            RemoteException {
        log.info("Confirm order for agencyCode = " + agencyCode + " orderNo = " 
                + orderNo + " responseCode = " + responseCode + " txId = " + txID + " bankCode = " + bankCode);
        ConfirmOrderBankServiceStub stub = new ConfirmOrderBankServiceStub(URL);
        ConfirmOrder confirmOrder = new ConfirmOrder();
        confirmOrder.setAgencyCode(agencyCode);
        confirmOrder.setOrderNo(orderNo);
        confirmOrder.setResponseCode(responseCode);
        confirmOrder.setTrnsID(txID);
        confirmOrder.setBankCode(bankCode);

        String checkSum = Encryption.SHA1(confirmOrder.getAgencyCode()
                + confirmOrder.getOrderNo() + confirmOrder.getResponseCode()
                + confirmOrder.getTrnsID()  + confirmOrder.getBankCode() 
                + CommonDef.SHA1_SHARED_KEY);
        confirmOrder.setCheckSum(checkSum);

        ConfirmOrderResponse confirmOrderResponse = stub.confirmOrder(confirmOrder);

        log.info("Response Code from confirmOrderBankService := " + confirmOrderResponse.getConfirmOrderResult());

        return confirmOrderResponse.getConfirmOrderResult();
    }

    public CheckAgencyResponse checkAgency(String accountName) throws AxisFault, RemoteException {
        log.info("Check Agency for accountName = " + accountName);
        ConfirmOrderBankServiceStub stub = new ConfirmOrderBankServiceStub(URL);
        CheckAgency checkAgency = new CheckAgency();
        checkAgency.setAccountName(accountName);

        String checkSum = Encryption.SHA1(accountName + CommonDef.SHA1_SHARED_KEY);
        checkAgency.setCheckSum(checkSum);

        CheckAgencyResponse checkAgencyResponse = stub.checkAgency(checkAgency);

        log.info("Response Code from checkAgency := " + checkAgencyResponse.getCheckAgencyResult().getReturnCode());
        log.info("FullName := " + checkAgencyResponse.getCheckAgencyResult().getFullName());
        log.info("Agency Code := " + checkAgencyResponse.getCheckAgencyResult().getAgencyCode());
        log.info("ID Card := " + checkAgencyResponse.getCheckAgencyResult().getIdCard());
        log.info("Address := " + checkAgencyResponse.getCheckAgencyResult().getAddress());
        log.info("ManageUnitId := " + checkAgencyResponse.getCheckAgencyResult().getManageUnitId());
        return checkAgencyResponse;
    }

    public TopupResponse topup(String accountName, BigDecimal totalAmount, String note,
            String trnsID,
            String bankCode, String paymentChannel)
            throws AxisFault, RemoteException {
        log.info("Call EsaleWebservice to Topup order for accountName = " + accountName
                + " amount = " + totalAmount + " trnsID = " + trnsID + " bankCode = " + bankCode
                + " paymentChannel = " + paymentChannel);
        ConfirmOrderBankServiceStub stub = new ConfirmOrderBankServiceStub(URL);

        Topup topup = new Topup();
        topup.setAccountName(accountName);
        topup.setTotalAmount(totalAmount);
        topup.setNote(note);
        topup.setTrnsID(trnsID);
        topup.setBankCode(bankCode);
        topup.setPaymentChannel(paymentChannel);


        String checkSum = Encryption.SHA1(
                topup.getAccountName() + topup.getTotalAmount() + topup.getNote()
                + topup.getTrnsID() + topup.getBankCode() + topup.getPaymentChannel() + CommonDef.SHA1_SHARED_KEY);
        topup.setCheckSum(checkSum);

        TopupResponse topupResponse = stub.topup(topup);

        log.info("ReturnCode from topup = " + topupResponse.getTopupResult().getReturnCode());
        log.info("OrderNo = " + topupResponse.getTopupResult().getOrderNo());

        return topupResponse;
    }
}
