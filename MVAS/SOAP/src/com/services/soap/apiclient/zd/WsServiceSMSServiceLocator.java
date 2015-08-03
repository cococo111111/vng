/**
 * WsServiceSMSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.zd;

public class WsServiceSMSServiceLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.zd.WsServiceSMSService {

    public WsServiceSMSServiceLocator() {
    }


    public WsServiceSMSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsServiceSMSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WsServiceSMSPort
    private java.lang.String WsServiceSMSPort_address = "http://deal.zing.vn/ws_soapservices/wsdl";

    public java.lang.String getWsServiceSMSPortAddress() {
        return WsServiceSMSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WsServiceSMSPortWSDDServiceName = "WsServiceSMSPort";

    public java.lang.String getWsServiceSMSPortWSDDServiceName() {
        return WsServiceSMSPortWSDDServiceName;
    }

    public void setWsServiceSMSPortWSDDServiceName(java.lang.String name) {
        WsServiceSMSPortWSDDServiceName = name;
    }

    public com.services.soap.apiclient.zd.WsServiceSMSPort getWsServiceSMSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WsServiceSMSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWsServiceSMSPort(endpoint);
    }

    public com.services.soap.apiclient.zd.WsServiceSMSPort getWsServiceSMSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.zd.WsServiceSMSBindingStub _stub = new com.services.soap.apiclient.zd.WsServiceSMSBindingStub(portAddress, this);
            _stub.setPortName(getWsServiceSMSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWsServiceSMSPortEndpointAddress(java.lang.String address) {
        WsServiceSMSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.services.soap.apiclient.zd.WsServiceSMSPort.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.zd.WsServiceSMSBindingStub _stub = new com.services.soap.apiclient.zd.WsServiceSMSBindingStub(new java.net.URL(WsServiceSMSPort_address), this);
                _stub.setPortName(getWsServiceSMSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WsServiceSMSPort".equals(inputPortName)) {
            return getWsServiceSMSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://deal.zing.vn/ws_soapservices/wsdl", "WsServiceSMSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://deal.zing.vn/ws_soapservices/wsdl", "WsServiceSMSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WsServiceSMSPort".equals(portName)) {
            setWsServiceSMSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
