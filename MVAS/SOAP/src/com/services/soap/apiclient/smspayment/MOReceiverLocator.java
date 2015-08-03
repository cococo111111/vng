/**
 * MOReceiverLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.smspayment;

public class MOReceiverLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.smspayment.MOReceiver {

/**
 * Receive Original Message from VTC
 */

    public MOReceiverLocator() {
    }


    public MOReceiverLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MOReceiverLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MOReceiverSoap12
    private java.lang.String MOReceiverSoap12_address = "http://10.30.17.253/MOReceiver/MOReceiver.asmx";

    public java.lang.String getMOReceiverSoap12Address() {
        return MOReceiverSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MOReceiverSoap12WSDDServiceName = "MOReceiverSoap12";

    public java.lang.String getMOReceiverSoap12WSDDServiceName() {
        return MOReceiverSoap12WSDDServiceName;
    }

    public void setMOReceiverSoap12WSDDServiceName(java.lang.String name) {
        MOReceiverSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MOReceiverSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMOReceiverSoap12(endpoint);
    }

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.smspayment.MOReceiverSoap12Stub _stub = new com.services.soap.apiclient.smspayment.MOReceiverSoap12Stub(portAddress, this);
            _stub.setPortName(getMOReceiverSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMOReceiverSoap12EndpointAddress(java.lang.String address) {
        MOReceiverSoap12_address = address;
    }


    // Use to get a proxy class for MOReceiverSoap
    private java.lang.String MOReceiverSoap_address = "http://10.30.17.253/MOReceiver/MOReceiver.asmx";

    public java.lang.String getMOReceiverSoapAddress() {
        return MOReceiverSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MOReceiverSoapWSDDServiceName = "MOReceiverSoap";

    public java.lang.String getMOReceiverSoapWSDDServiceName() {
        return MOReceiverSoapWSDDServiceName;
    }

    public void setMOReceiverSoapWSDDServiceName(java.lang.String name) {
        MOReceiverSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MOReceiverSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMOReceiverSoap(endpoint);
    }

    public com.services.soap.apiclient.smspayment.MOReceiverSoap getMOReceiverSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.smspayment.MOReceiverSoapStub _stub = new com.services.soap.apiclient.smspayment.MOReceiverSoapStub(portAddress, this);
            _stub.setPortName(getMOReceiverSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMOReceiverSoapEndpointAddress(java.lang.String address) {
        MOReceiverSoap_address = address;
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
            if (com.services.soap.apiclient.smspayment.MOReceiverSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.smspayment.MOReceiverSoap12Stub _stub = new com.services.soap.apiclient.smspayment.MOReceiverSoap12Stub(new java.net.URL(MOReceiverSoap12_address), this);
                _stub.setPortName(getMOReceiverSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.smspayment.MOReceiverSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.smspayment.MOReceiverSoapStub _stub = new com.services.soap.apiclient.smspayment.MOReceiverSoapStub(new java.net.URL(MOReceiverSoap_address), this);
                _stub.setPortName(getMOReceiverSoapWSDDServiceName());
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
        if ("MOReceiverSoap12".equals(inputPortName)) {
            return getMOReceiverSoap12();
        }
        else if ("MOReceiverSoap".equals(inputPortName)) {
            return getMOReceiverSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vinagame.com.vn/payment", "MOReceiver");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vinagame.com.vn/payment", "MOReceiverSoap12"));
            ports.add(new javax.xml.namespace.QName("http://www.vinagame.com.vn/payment", "MOReceiverSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MOReceiverSoap12".equals(portName)) {
            setMOReceiverSoap12EndpointAddress(address);
        }
        else 
if ("MOReceiverSoap".equals(portName)) {
            setMOReceiverSoapEndpointAddress(address);
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
