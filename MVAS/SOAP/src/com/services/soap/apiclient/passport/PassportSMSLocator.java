/**
 * PassportSMSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.passport;

public class PassportSMSLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.passport.PassportSMS {

    public PassportSMSLocator() {
    }


    public PassportSMSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PassportSMSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PassportSMSSoap12
    private java.lang.String PassportSMSSoap12_address = "http://10.30.17.193/PassportSMSGateway/PassportSMSGateway.asmx";

    public java.lang.String getPassportSMSSoap12Address() {
        return PassportSMSSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PassportSMSSoap12WSDDServiceName = "PassportSMSSoap12";

    public java.lang.String getPassportSMSSoap12WSDDServiceName() {
        return PassportSMSSoap12WSDDServiceName;
    }

    public void setPassportSMSSoap12WSDDServiceName(java.lang.String name) {
        PassportSMSSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PassportSMSSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPassportSMSSoap12(endpoint);
    }

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.passport.PassportSMSSoap12Stub _stub = new com.services.soap.apiclient.passport.PassportSMSSoap12Stub(portAddress, this);
            _stub.setPortName(getPassportSMSSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPassportSMSSoap12EndpointAddress(java.lang.String address) {
        PassportSMSSoap12_address = address;
    }


    // Use to get a proxy class for PassportSMSSoap
    private java.lang.String PassportSMSSoap_address = "http://10.30.17.193/PassportSMSGateway/PassportSMSGateway.asmx";

    public java.lang.String getPassportSMSSoapAddress() {
        return PassportSMSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PassportSMSSoapWSDDServiceName = "PassportSMSSoap";

    public java.lang.String getPassportSMSSoapWSDDServiceName() {
        return PassportSMSSoapWSDDServiceName;
    }

    public void setPassportSMSSoapWSDDServiceName(java.lang.String name) {
        PassportSMSSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PassportSMSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPassportSMSSoap(endpoint);
    }

    public com.services.soap.apiclient.passport.PassportSMSSoap getPassportSMSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.passport.PassportSMSSoapStub _stub = new com.services.soap.apiclient.passport.PassportSMSSoapStub(portAddress, this);
            _stub.setPortName(getPassportSMSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPassportSMSSoapEndpointAddress(java.lang.String address) {
        PassportSMSSoap_address = address;
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
            if (com.services.soap.apiclient.passport.PassportSMSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.passport.PassportSMSSoap12Stub _stub = new com.services.soap.apiclient.passport.PassportSMSSoap12Stub(new java.net.URL(PassportSMSSoap12_address), this);
                _stub.setPortName(getPassportSMSSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.passport.PassportSMSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.passport.PassportSMSSoapStub _stub = new com.services.soap.apiclient.passport.PassportSMSSoapStub(new java.net.URL(PassportSMSSoap_address), this);
                _stub.setPortName(getPassportSMSSoapWSDDServiceName());
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
        if ("PassportSMSSoap12".equals(inputPortName)) {
            return getPassportSMSSoap12();
        }
        else if ("PassportSMSSoap".equals(inputPortName)) {
            return getPassportSMSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("PassportSMSGateway", "PassportSMS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("PassportSMSGateway", "PassportSMSSoap12"));
            ports.add(new javax.xml.namespace.QName("PassportSMSGateway", "PassportSMSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PassportSMSSoap12".equals(portName)) {
            setPassportSMSSoap12EndpointAddress(address);
        }
        else 
if ("PassportSMSSoap".equals(portName)) {
            setPassportSMSSoapEndpointAddress(address);
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
