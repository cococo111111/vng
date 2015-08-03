/**
 * CSToolReceiverMOLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.cstool;

public class CSToolReceiverMOLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.cstool.CSToolReceiverMO {

    public CSToolReceiverMOLocator() {
    }


    public CSToolReceiverMOLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CSToolReceiverMOLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CSToolReceiverMOSoap12
    private java.lang.String CSToolReceiverMOSoap12_address = "http://mmo.pm.apicst.vn/SupportService/CSToolReceiverMO.asmx";

    public java.lang.String getCSToolReceiverMOSoap12Address() {
        return CSToolReceiverMOSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CSToolReceiverMOSoap12WSDDServiceName = "CSToolReceiverMOSoap12";

    public java.lang.String getCSToolReceiverMOSoap12WSDDServiceName() {
        return CSToolReceiverMOSoap12WSDDServiceName;
    }

    public void setCSToolReceiverMOSoap12WSDDServiceName(java.lang.String name) {
        CSToolReceiverMOSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.cstool.CSToolReceiverMOSoap getCSToolReceiverMOSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CSToolReceiverMOSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCSToolReceiverMOSoap12(endpoint);
    }

    public com.services.soap.apiclient.cstool.CSToolReceiverMOSoap getCSToolReceiverMOSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.cstool.CSToolReceiverMOSoap12Stub _stub = new com.services.soap.apiclient.cstool.CSToolReceiverMOSoap12Stub(portAddress, this);
            _stub.setPortName(getCSToolReceiverMOSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCSToolReceiverMOSoap12EndpointAddress(java.lang.String address) {
        CSToolReceiverMOSoap12_address = address;
    }


    // Use to get a proxy class for CSToolReceiverMOSoap
    private java.lang.String CSToolReceiverMOSoap_address = "http://mmo.pm.apicst.vn/SupportService/CSToolReceiverMO.asmx";

    public java.lang.String getCSToolReceiverMOSoapAddress() {
        return CSToolReceiverMOSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CSToolReceiverMOSoapWSDDServiceName = "CSToolReceiverMOSoap";

    public java.lang.String getCSToolReceiverMOSoapWSDDServiceName() {
        return CSToolReceiverMOSoapWSDDServiceName;
    }

    public void setCSToolReceiverMOSoapWSDDServiceName(java.lang.String name) {
        CSToolReceiverMOSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.cstool.CSToolReceiverMOSoap getCSToolReceiverMOSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CSToolReceiverMOSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCSToolReceiverMOSoap(endpoint);
    }

    public com.services.soap.apiclient.cstool.CSToolReceiverMOSoap getCSToolReceiverMOSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.cstool.CSToolReceiverMOSoapStub _stub = new com.services.soap.apiclient.cstool.CSToolReceiverMOSoapStub(portAddress, this);
            _stub.setPortName(getCSToolReceiverMOSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCSToolReceiverMOSoapEndpointAddress(java.lang.String address) {
        CSToolReceiverMOSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.services.soap.apiclient.cstool.CSToolReceiverMOSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.cstool.CSToolReceiverMOSoap12Stub _stub = new com.services.soap.apiclient.cstool.CSToolReceiverMOSoap12Stub(new java.net.URL(CSToolReceiverMOSoap12_address), this);
                _stub.setPortName(getCSToolReceiverMOSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.cstool.CSToolReceiverMOSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.cstool.CSToolReceiverMOSoapStub _stub = new com.services.soap.apiclient.cstool.CSToolReceiverMOSoapStub(new java.net.URL(CSToolReceiverMOSoap_address), this);
                _stub.setPortName(getCSToolReceiverMOSoapWSDDServiceName());
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
        if ("CSToolReceiverMOSoap12".equals(inputPortName)) {
            return getCSToolReceiverMOSoap12();
        }
        else if ("CSToolReceiverMOSoap".equals(inputPortName)) {
            return getCSToolReceiverMOSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "CSToolReceiverMO");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CSToolReceiverMOSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CSToolReceiverMOSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CSToolReceiverMOSoap12".equals(portName)) {
            setCSToolReceiverMOSoap12EndpointAddress(address);
        }
        else 
if ("CSToolReceiverMOSoap".equals(portName)) {
            setCSToolReceiverMOSoapEndpointAddress(address);
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
