/**
 * CSMServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.csm;

public interface CSMServiceSoap extends java.rmi.Remote {

    /**
     * Cập nhật trạng thái từ chối nhận SMS quảng cáo của khách hàng
     */
    public int rejectSMSAdv(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String mobileOperator, java.lang.String userName, java.lang.String password, java.util.Calendar requestTime) throws java.rmi.RemoteException;
}
