package org.csapi.www.wsdl.parlayx.sms.notification.v2_2._interface;

public class SmsNotificationProxy implements org.csapi.www.wsdl.parlayx.sms.notification.v2_2._interface.SmsNotification {
  private String _endpoint = null;
  private org.csapi.www.wsdl.parlayx.sms.notification.v2_2._interface.SmsNotification smsNotification = null;
  
  public SmsNotificationProxy() {
    _initSmsNotificationProxy();
  }
  
  public SmsNotificationProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmsNotificationProxy();
  }
  
  private void _initSmsNotificationProxy() {
    try {
      smsNotification = (new org.csapi.www.wsdl.parlayx.sms.notification.v2_2.service.SmsNotificationServiceLocator()).getSmsNotification();
      if (smsNotification != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smsNotification)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smsNotification)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smsNotification != null)
      ((javax.xml.rpc.Stub)smsNotification)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.csapi.www.wsdl.parlayx.sms.notification.v2_2._interface.SmsNotification getSmsNotification() {
    if (smsNotification == null)
      _initSmsNotificationProxy();
    return smsNotification;
  }
  
  public void notifySmsReception(java.lang.String correlator, org.csapi.www.schema.parlayx.sms.v2_2.SmsMessage message) throws java.rmi.RemoteException{
    if (smsNotification == null)
      _initSmsNotificationProxy();
    smsNotification.notifySmsReception(correlator, message);
  }
  
  public void notifySmsDeliveryReceipt(java.lang.String correlator, org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation deliveryStatus) throws java.rmi.RemoteException{
    if (smsNotification == null)
      _initSmsNotificationProxy();
    smsNotification.notifySmsDeliveryReceipt(correlator, deliveryStatus);
  }
  
  
}