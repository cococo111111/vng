package org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface;

public class SendSmsProxy implements org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface.SendSms {
  private String _endpoint = null;
  private org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface.SendSms sendSms = null;
  
  public SendSmsProxy() {
    _initSendSmsProxy();
  }
  
  public SendSmsProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendSmsProxy();
  }
  
  private void _initSendSmsProxy() {
    try {
      sendSms = (new org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceLocator()).getSendSms();
      if (sendSms != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendSms)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendSms)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendSms != null)
      ((javax.xml.rpc.Stub)sendSms)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface.SendSms getSendSms() {
    if (sendSms == null)
      _initSendSmsProxy();
    return sendSms;
  }
  
  public java.lang.String sendSms(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, java.lang.String message, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException{
    if (sendSms == null)
      _initSendSmsProxy();
    return sendSms.sendSms(addresses, senderName, charging, message, receiptRequest);
  }
  
  public java.lang.String sendSmsLogo(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, byte[] image, org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat smsFormat, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException{
    if (sendSms == null)
      _initSendSmsProxy();
    return sendSms.sendSmsLogo(addresses, senderName, charging, image, smsFormat, receiptRequest);
  }
  
  public java.lang.String sendSmsRingtone(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, java.lang.String ringtone, org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat smsFormat, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException{
    if (sendSms == null)
      _initSendSmsProxy();
    return sendSms.sendSmsRingtone(addresses, senderName, charging, ringtone, smsFormat, receiptRequest);
  }
  
  public org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[] getSmsDeliveryStatus(java.lang.String requestIdentifier) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException{
    if (sendSms == null)
      _initSendSmsProxy();
    return sendSms.getSmsDeliveryStatus(requestIdentifier);
  }
  
  
}