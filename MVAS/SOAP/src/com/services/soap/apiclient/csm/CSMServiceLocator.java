/**
 * CSMServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.csm;

public class CSMServiceLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.csm.CSMService {

    public CSMServiceLocator() {
    }


    public CSMServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CSMServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CSMServiceSoap12
    private java.lang.String CSMServiceSoap12_address = "http://10.30.9.30/SMS/CSMService.asmx";

    public java.lang.String getCSMServiceSoap12Address() {
        return CSMServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CSMServiceSoap12WSDDServiceName = "CSMServiceSoap12";

    public java.lang.String getCSMServiceSoap12WSDDServiceName() {
        return CSMServiceSoap12WSDDServiceName;
    }

    public void setCSMServiceSoap12WSDDServiceName(java.lang.String name) {
        CSMServiceSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CSMServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCSMServiceSoap12(endpoint);
    }

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.csm.CSMServiceSoap12Stub _stub = new com.services.soap.apiclient.csm.CSMServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getCSMServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCSMServiceSoap12EndpointAddress(java.lang.String address) {
        CSMServiceSoap12_address = address;
    }


    // Use to get a proxy class for CSMServiceSoap
    private java.lang.String CSMServiceSoap_address = "http://10.30.9.30/SMS/CSMService.asmx";

    public java.lang.String getCSMServiceSoapAddress() {
        return CSMServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CSMServiceSoapWSDDServiceName = "CSMServiceSoap";

    public java.lang.String getCSMServiceSoapWSDDServiceName() {
        return CSMServiceSoapWSDDServiceName;
    }

    public void setCSMServiceSoapWSDDServiceName(java.lang.String name) {
        CSMServiceSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CSMServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCSMServiceSoap(endpoint);
    }

    public com.services.soap.apiclient.csm.CSMServiceSoap getCSMServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.csm.CSMServiceSoapStub _stub = new com.services.soap.apiclient.csm.CSMServiceSoapStub(portAddress, this);
            _stub.setPortName(getCSMServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCSMServiceSoapEndpointAddress(java.lang.String address) {
        CSMServiceSoap_address = address;
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
            if (com.services.soap.apiclient.csm.CSMServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.csm.CSMServiceSoap12Stub _stub = new com.services.soap.apiclient.csm.CSMServiceSoap12Stub(new java.net.URL(CSMServiceSoap12_address), this);
                _stub.setPortName(getCSMServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.csm.CSMServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.csm.CSMServiceSoapStub _stub = new com.services.soap.apiclient.csm.CSMServiceSoapStub(new java.net.URL(CSMServiceSoap_address), this);
                _stub.setPortName(getCSMServiceSoapWSDDServiceName());
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
        if ("CSMServiceSoap12".equals(inputPortName)) {
            return getCSMServiceSoap12();
        }
        else if ("CSMServiceSoap".equals(inputPortName)) {
            return getCSMServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "CSMService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CSMServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CSMServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CSMServiceSoap12".equals(portName)) {
            setCSMServiceSoap12EndpointAddress(address);
        }
        else 
if ("CSMServiceSoap".equals(portName)) {
            setCSMServiceSoapEndpointAddress(address);
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
