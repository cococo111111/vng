/**
 * RecieverMOZingModelSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.zingmodel;

public interface RecieverMOZingModelSoap extends java.rmi.Remote {
    public int receiveMO(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String operator, java.lang.String partnerUsername, java.lang.String partnerPassword, java.util.Calendar requestTime) throws java.rmi.RemoteException;
    public java.lang.String receiveMO2(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String operator, java.lang.String partnerUsername, java.lang.String partnerPassword, java.util.Calendar requestTime) throws java.rmi.RemoteException;
}
