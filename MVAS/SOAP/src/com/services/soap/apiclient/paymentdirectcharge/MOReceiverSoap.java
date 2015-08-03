/**
 * MOReceiverSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.paymentdirectcharge;

public interface MOReceiverSoap extends java.rmi.Remote {
    public int receiveMO(int MO_Id, java.lang.String userId, java.lang.String serviceId, java.lang.String commandCode, java.lang.String message, java.lang.String userName, java.lang.String password, java.util.Calendar requestTime, java.lang.String telco) throws java.rmi.RemoteException;
}
