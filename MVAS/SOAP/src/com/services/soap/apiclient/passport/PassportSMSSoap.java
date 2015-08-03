/**
 * PassportSMSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.passport;

public interface PassportSMSSoap extends java.rmi.Remote {
    public int receiverMO(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String telcoOperator, java.lang.String partnerUsername, java.lang.String partnerPassword, java.util.Calendar requestTime) throws java.rmi.RemoteException;
}
