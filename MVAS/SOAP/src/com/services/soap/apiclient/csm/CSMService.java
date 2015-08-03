/**
 * CSMService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.csm;

public interface CSMService extends javax.xml.rpc.Service {
    public java.lang.String getCSMServiceSoap12Address();

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getCSMServiceSoapAddress();

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
