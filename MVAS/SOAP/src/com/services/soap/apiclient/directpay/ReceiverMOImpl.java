/**
 * ReceiverMOImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.directpay;

public interface ReceiverMOImpl extends java.rmi.Remote {
    public int receiveMO(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String mobileOperator, java.lang.String userName, java.lang.String password, java.util.Calendar requestTime, java.lang.String sig) throws java.rmi.RemoteException;
}
