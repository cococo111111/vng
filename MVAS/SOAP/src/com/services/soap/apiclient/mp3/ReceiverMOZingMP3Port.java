/**
 * ReceiverMOZingMP3Port.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.mp3;

public interface ReceiverMOZingMP3Port extends java.rmi.Remote {
    public int receiveMO(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String mobileOperator, java.lang.String username, java.lang.String password, java.lang.String requestTime) throws java.rmi.RemoteException;
}
