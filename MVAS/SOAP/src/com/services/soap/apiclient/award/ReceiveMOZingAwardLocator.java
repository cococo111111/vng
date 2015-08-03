/**
 * ReceiveMOZingAwardLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.award;

public class ReceiveMOZingAwardLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.award.ReceiveMOZingAward {

    public ReceiveMOZingAwardLocator() {
    }


    public ReceiveMOZingAwardLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReceiveMOZingAwardLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReceiveMOZingAwardPort
    private java.lang.String ReceiveMOZingAwardPort_address = "http://award.vlink.vn/webservice/service/soap-server.php";

    public java.lang.String getReceiveMOZingAwardPortAddress() {
        return ReceiveMOZingAwardPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReceiveMOZingAwardPortWSDDServiceName = "ReceiveMOZingAwardPort";

    public java.lang.String getReceiveMOZingAwardPortWSDDServiceName() {
        return ReceiveMOZingAwardPortWSDDServiceName;
    }

    public void setReceiveMOZingAwardPortWSDDServiceName(java.lang.String name) {
        ReceiveMOZingAwardPortWSDDServiceName = name;
    }

    public com.services.soap.apiclient.award.ReceiveMOZingAwardPortType getReceiveMOZingAwardPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReceiveMOZingAwardPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReceiveMOZingAwardPort(endpoint);
    }

    public com.services.soap.apiclient.award.ReceiveMOZingAwardPortType getReceiveMOZingAwardPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.award.ReceiveMOZingAwardBindingStub _stub = new com.services.soap.apiclient.award.ReceiveMOZingAwardBindingStub(portAddress, this);
            _stub.setPortName(getReceiveMOZingAwardPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReceiveMOZingAwardPortEndpointAddress(java.lang.String address) {
        ReceiveMOZingAwardPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.services.soap.apiclient.award.ReceiveMOZingAwardPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.award.ReceiveMOZingAwardBindingStub _stub = new com.services.soap.apiclient.award.ReceiveMOZingAwardBindingStub(new java.net.URL(ReceiveMOZingAwardPort_address), this);
                _stub.setPortName(getReceiveMOZingAwardPortWSDDServiceName());
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
        if ("ReceiveMOZingAwardPort".equals(inputPortName)) {
            return getReceiveMOZingAwardPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ReceiveMOZingAward", "ReceiveMOZingAward");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ReceiveMOZingAward", "ReceiveMOZingAwardPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReceiveMOZingAwardPort".equals(portName)) {
            setReceiveMOZingAwardPortEndpointAddress(address);
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
