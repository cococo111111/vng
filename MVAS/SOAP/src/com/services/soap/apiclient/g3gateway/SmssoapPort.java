/**
 * SmssoapPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.g3gateway;

public interface SmssoapPort extends java.rmi.Remote {

    /**
     * MO Receive from user
     */
    public int moReceive(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String operator, java.lang.String partnerUsername, java.lang.String partnerPassword, java.lang.String requestTime) throws java.rmi.RemoteException;
}
