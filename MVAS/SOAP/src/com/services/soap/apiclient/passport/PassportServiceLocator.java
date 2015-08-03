/**
 * PassportServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.passport;

public class PassportServiceLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.passport.PassportService {

    public PassportServiceLocator() {
    }


    public PassportServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PassportServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PassportServiceSoap12
    private java.lang.String PassportServiceSoap12_address = "http://10.30.17.193/Passport5_LocalTest/PassportService.asmx";

    public java.lang.String getPassportServiceSoap12Address() {
        return PassportServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PassportServiceSoap12WSDDServiceName = "PassportServiceSoap12";

    public java.lang.String getPassportServiceSoap12WSDDServiceName() {
        return PassportServiceSoap12WSDDServiceName;
    }

    public void setPassportServiceSoap12WSDDServiceName(java.lang.String name) {
        PassportServiceSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PassportServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPassportServiceSoap12(endpoint);
    }

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.passport.PassportServiceSoap12Stub _stub = new com.services.soap.apiclient.passport.PassportServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getPassportServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPassportServiceSoap12EndpointAddress(java.lang.String address) {
        PassportServiceSoap12_address = address;
    }


    // Use to get a proxy class for PassportServiceSoap
    private java.lang.String PassportServiceSoap_address = "http://10.30.17.193/Passport5_LocalTest/PassportService.asmx";

    public java.lang.String getPassportServiceSoapAddress() {
        return PassportServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PassportServiceSoapWSDDServiceName = "PassportServiceSoap";

    public java.lang.String getPassportServiceSoapWSDDServiceName() {
        return PassportServiceSoapWSDDServiceName;
    }

    public void setPassportServiceSoapWSDDServiceName(java.lang.String name) {
        PassportServiceSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PassportServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPassportServiceSoap(endpoint);
    }

    public com.services.soap.apiclient.passport.PassportServiceSoap getPassportServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.passport.PassportServiceSoapStub _stub = new com.services.soap.apiclient.passport.PassportServiceSoapStub(portAddress, this);
            _stub.setPortName(getPassportServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPassportServiceSoapEndpointAddress(java.lang.String address) {
        PassportServiceSoap_address = address;
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
            if (com.services.soap.apiclient.passport.PassportServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.passport.PassportServiceSoap12Stub _stub = new com.services.soap.apiclient.passport.PassportServiceSoap12Stub(new java.net.URL(PassportServiceSoap12_address), this);
                _stub.setPortName(getPassportServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.passport.PassportServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.passport.PassportServiceSoapStub _stub = new com.services.soap.apiclient.passport.PassportServiceSoapStub(new java.net.URL(PassportServiceSoap_address), this);
                _stub.setPortName(getPassportServiceSoapWSDDServiceName());
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
        if ("PassportServiceSoap12".equals(inputPortName)) {
            return getPassportServiceSoap12();
        }
        else if ("PassportServiceSoap".equals(inputPortName)) {
            return getPassportServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("PassportSystem", "PassportService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("PassportSystem", "PassportServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("PassportSystem", "PassportServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PassportServiceSoap12".equals(portName)) {
            setPassportServiceSoap12EndpointAddress(address);
        }
        else 
if ("PassportServiceSoap".equals(portName)) {
            setPassportServiceSoapEndpointAddress(address);
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
