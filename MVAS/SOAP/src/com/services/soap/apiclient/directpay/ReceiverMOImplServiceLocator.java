/**
 * ReceiverMOImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.directpay;

public class ReceiverMOImplServiceLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.directpay.ReceiverMOImplService {

    public ReceiverMOImplServiceLocator() {
    }


    public ReceiverMOImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReceiverMOImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReceiverMOImplPort
    private java.lang.String ReceiverMOImplPort_address = "http://10.30.22.130:9191/dbg-api-wrapper/ReceiverMO";

    public java.lang.String getReceiverMOImplPortAddress() {
        return ReceiverMOImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReceiverMOImplPortWSDDServiceName = "ReceiverMOImplPort";

    public java.lang.String getReceiverMOImplPortWSDDServiceName() {
        return ReceiverMOImplPortWSDDServiceName;
    }

    public void setReceiverMOImplPortWSDDServiceName(java.lang.String name) {
        ReceiverMOImplPortWSDDServiceName = name;
    }

    public com.services.soap.apiclient.directpay.ReceiverMOImpl getReceiverMOImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReceiverMOImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReceiverMOImplPort(endpoint);
    }

    public com.services.soap.apiclient.directpay.ReceiverMOImpl getReceiverMOImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.directpay.ReceiverMOImplPortBindingStub _stub = new com.services.soap.apiclient.directpay.ReceiverMOImplPortBindingStub(portAddress, this);
            _stub.setPortName(getReceiverMOImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReceiverMOImplPortEndpointAddress(java.lang.String address) {
        ReceiverMOImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.services.soap.apiclient.directpay.ReceiverMOImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.directpay.ReceiverMOImplPortBindingStub _stub = new com.services.soap.apiclient.directpay.ReceiverMOImplPortBindingStub(new java.net.URL(ReceiverMOImplPort_address), this);
                _stub.setPortName(getReceiverMOImplPortWSDDServiceName());
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
        if ("ReceiverMOImplPort".equals(inputPortName)) {
            return getReceiverMOImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wrapper.dbgapi.payment.vng.com/", "ReceiverMOImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wrapper.dbgapi.payment.vng.com/", "ReceiverMOImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReceiverMOImplPort".equals(portName)) {
            setReceiverMOImplPortEndpointAddress(address);
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
