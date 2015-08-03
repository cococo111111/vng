/**
 * PassportServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.passport;

public interface PassportServiceSoap extends java.rmi.Remote {
    public java.lang.String[] requestService(java.lang.String serviceName, java.lang.String[][] body, int requestId, java.lang.String userIP, java.lang.String clientType, java.lang.String wsAccount, java.lang.String wsPassword, int productId) throws java.rmi.RemoteException;
    public java.lang.String[][] getConfigurationInfo(java.lang.String serviceName, int requestId, java.lang.String userIP, java.lang.String clientType, java.lang.String wsAccount, java.lang.String wsPassword, int productId) throws java.rmi.RemoteException;
    public java.lang.String[][] getAccountInfo(java.lang.String serviceName, java.lang.String[][] body, int requestId, java.lang.String userIP, java.lang.String clientType, java.lang.String wsAccount, java.lang.String wsPassword, int productId) throws java.rmi.RemoteException;
    public java.lang.String[] getAccountInfoByName(java.lang.String accountName, int requestId, java.lang.String userIP, java.lang.String clientType, java.lang.String wsAccount, java.lang.String wsPassword, int productId) throws java.rmi.RemoteException;
}
