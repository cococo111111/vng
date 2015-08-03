/**
 * ReceiverMOPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.zingphoto;

public interface ReceiverMOPortType extends java.rmi.Remote {

    /**
     * Return code to the caller
     */
    public int moReceive(int MO_ID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String operator, java.lang.String partnerUsername, java.lang.String partnerPassword, java.util.Calendar requestTime, java.lang.String requestID) throws java.rmi.RemoteException;
}
