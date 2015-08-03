package com.services.soap.apiclient.directpay;

public class ReceiverMOImplProxy implements com.services.soap.apiclient.directpay.ReceiverMOImpl {
  private String _endpoint = null;
  private com.services.soap.apiclient.directpay.ReceiverMOImpl receiverMOImpl = null;
  
  public ReceiverMOImplProxy() {
    _initReceiverMOImplProxy();
  }
  
  public ReceiverMOImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initReceiverMOImplProxy();
  }
  
  private void _initReceiverMOImplProxy() {
    try {
      receiverMOImpl = (new com.services.soap.apiclient.directpay.ReceiverMOImplServiceLocator()).getReceiverMOImplPort();
      if (receiverMOImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)receiverMOImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)receiverMOImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (receiverMOImpl != null)
      ((javax.xml.rpc.Stub)receiverMOImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.services.soap.apiclient.directpay.ReceiverMOImpl getReceiverMOImpl() {
    if (receiverMOImpl == null)
      _initReceiverMOImplProxy();
    return receiverMOImpl;
  }
  
  public int receiveMO(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String mobileOperator, java.lang.String userName, java.lang.String password, java.util.Calendar requestTime, java.lang.String sig) throws java.rmi.RemoteException{
    if (receiverMOImpl == null)
      _initReceiverMOImplProxy();
    return receiverMOImpl.receiveMO(requestID, userID, serviceID, commandCode, message, mobileOperator, userName, password, requestTime, sig);
  }
  
  
}