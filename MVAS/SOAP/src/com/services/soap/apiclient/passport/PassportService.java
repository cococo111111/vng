/**
 * PassportService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.passport;

public interface PassportService extends javax.xml.rpc.Service {
    public java.lang.String getPassportServiceSoap12Address();

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getPassportServiceSoapAddress();

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
