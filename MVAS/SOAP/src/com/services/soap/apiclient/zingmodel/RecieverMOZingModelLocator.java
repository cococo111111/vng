/**
 * RecieverMOZingModelLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.zingmodel;

public class RecieverMOZingModelLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.zingmodel.RecieverMOZingModel {

    public RecieverMOZingModelLocator() {
    }


    public RecieverMOZingModelLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RecieverMOZingModelLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RecieverMOZingModelSoap12
    private java.lang.String RecieverMOZingModelSoap12_address = "http://test.f-idol.vn/RecieverMOZingModel.asmx";

    public java.lang.String getRecieverMOZingModelSoap12Address() {
        return RecieverMOZingModelSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecieverMOZingModelSoap12WSDDServiceName = "RecieverMOZingModelSoap12";

    public java.lang.String getRecieverMOZingModelSoap12WSDDServiceName() {
        return RecieverMOZingModelSoap12WSDDServiceName;
    }

    public void setRecieverMOZingModelSoap12WSDDServiceName(java.lang.String name) {
        RecieverMOZingModelSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap getRecieverMOZingModelSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecieverMOZingModelSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecieverMOZingModelSoap12(endpoint);
    }

    public com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap getRecieverMOZingModelSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap12Stub _stub = new com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap12Stub(portAddress, this);
            _stub.setPortName(getRecieverMOZingModelSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecieverMOZingModelSoap12EndpointAddress(java.lang.String address) {
        RecieverMOZingModelSoap12_address = address;
    }


    // Use to get a proxy class for RecieverMOZingModelSoap
    private java.lang.String RecieverMOZingModelSoap_address = "http://test.f-idol.vn/RecieverMOZingModel.asmx";

    public java.lang.String getRecieverMOZingModelSoapAddress() {
        return RecieverMOZingModelSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecieverMOZingModelSoapWSDDServiceName = "RecieverMOZingModelSoap";

    public java.lang.String getRecieverMOZingModelSoapWSDDServiceName() {
        return RecieverMOZingModelSoapWSDDServiceName;
    }

    public void setRecieverMOZingModelSoapWSDDServiceName(java.lang.String name) {
        RecieverMOZingModelSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap getRecieverMOZingModelSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecieverMOZingModelSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecieverMOZingModelSoap(endpoint);
    }

    public com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap getRecieverMOZingModelSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoapStub _stub = new com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoapStub(portAddress, this);
            _stub.setPortName(getRecieverMOZingModelSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecieverMOZingModelSoapEndpointAddress(java.lang.String address) {
        RecieverMOZingModelSoap_address = address;
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
            if (com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap12Stub _stub = new com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap12Stub(new java.net.URL(RecieverMOZingModelSoap12_address), this);
                _stub.setPortName(getRecieverMOZingModelSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoapStub _stub = new com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoapStub(new java.net.URL(RecieverMOZingModelSoap_address), this);
                _stub.setPortName(getRecieverMOZingModelSoapWSDDServiceName());
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
        if ("RecieverMOZingModelSoap12".equals(inputPortName)) {
            return getRecieverMOZingModelSoap12();
        }
        else if ("RecieverMOZingModelSoap".equals(inputPortName)) {
            return getRecieverMOZingModelSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("ReceiverMOZingModel", "RecieverMOZingModel");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ReceiverMOZingModel", "RecieverMOZingModelSoap12"));
            ports.add(new javax.xml.namespace.QName("ReceiverMOZingModel", "RecieverMOZingModelSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RecieverMOZingModelSoap12".equals(portName)) {
            setRecieverMOZingModelSoap12EndpointAddress(address);
        }
        else 
if ("RecieverMOZingModelSoap".equals(portName)) {
            setRecieverMOZingModelSoapEndpointAddress(address);
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
