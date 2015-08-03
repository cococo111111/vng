/**
 * PassportSMS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.passport;

public interface PassportSMS extends javax.xml.rpc.Service {
    public java.lang.String getPassportSMSSoap12Address();

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap12() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getPassportSMSSoapAddress();

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
