/**
 * MOReceiver.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.smspayment;

public interface MOReceiver extends javax.xml.rpc.Service {

/**
 * Receive Original Message from VTC
 */
    public java.lang.String getMOReceiverSoap12Address();

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap12() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMOReceiverSoapAddress();

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
