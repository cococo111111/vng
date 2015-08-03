/**
 * SmssoapService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.g3gateway;

public interface SmssoapService extends javax.xml.rpc.Service {
    public java.lang.String getSmssoapPortAddress();

    public com.services.soap.apiclient.g3gateway.SmssoapPort getSmssoapPort() throws javax.xml.rpc.ServiceException;

    public com.services.soap.apiclient.g3gateway.SmssoapPort getSmssoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
