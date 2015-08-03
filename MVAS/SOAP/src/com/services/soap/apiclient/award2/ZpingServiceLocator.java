/**
 * ZpingServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.award2;

public class ZpingServiceLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.award2.ZpingService {

    public ZpingServiceLocator() {
    }


    public ZpingServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZpingServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for zpingPort
    private java.lang.String zpingPort_address = "http://local.awards.zing.vn/webservice/service/zping.php";

    public java.lang.String getzpingPortAddress() {
        return zpingPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String zpingPortWSDDServiceName = "zpingPort";

    public java.lang.String getzpingPortWSDDServiceName() {
        return zpingPortWSDDServiceName;
    }

    public void setzpingPortWSDDServiceName(java.lang.String name) {
        zpingPortWSDDServiceName = name;
    }

    public com.services.soap.apiclient.award2.ZpingPort getzpingPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(zpingPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getzpingPort(endpoint);
    }

    public com.services.soap.apiclient.award2.ZpingPort getzpingPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.award2.ZpingBindingStub _stub = new com.services.soap.apiclient.award2.ZpingBindingStub(portAddress, this);
            _stub.setPortName(getzpingPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setzpingPortEndpointAddress(java.lang.String address) {
        zpingPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.services.soap.apiclient.award2.ZpingPort.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.award2.ZpingBindingStub _stub = new com.services.soap.apiclient.award2.ZpingBindingStub(new java.net.URL(zpingPort_address), this);
                _stub.setPortName(getzpingPortWSDDServiceName());
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
        if ("zpingPort".equals(inputPortName)) {
            return getzpingPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://local.awards.zing.vn/webservice/service/zping.php", "zpingService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://local.awards.zing.vn/webservice/service/zping.php", "zpingPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("zpingPort".equals(portName)) {
            setzpingPortEndpointAddress(address);
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
