/**
 * CardShopAPIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class CardShopAPIServiceLocator extends org.apache.axis.client.Service implements com.services.soap.apiclient.esale.CardShopAPIService {

    public CardShopAPIServiceLocator() {
    }


    public CardShopAPIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CardShopAPIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CardShopAPIServiceSoap12
    private java.lang.String CardShopAPIServiceSoap12_address = "http://10.30.17.193/eSaleAPIv2/eSaleServices.asmx";

    public java.lang.String getCardShopAPIServiceSoap12Address() {
        return CardShopAPIServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CardShopAPIServiceSoap12WSDDServiceName = "CardShopAPIServiceSoap12";

    public java.lang.String getCardShopAPIServiceSoap12WSDDServiceName() {
        return CardShopAPIServiceSoap12WSDDServiceName;
    }

    public void setCardShopAPIServiceSoap12WSDDServiceName(java.lang.String name) {
        CardShopAPIServiceSoap12WSDDServiceName = name;
    }

    public com.services.soap.apiclient.esale.CardShopAPIServiceSoap getCardShopAPIServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CardShopAPIServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCardShopAPIServiceSoap12(endpoint);
    }

    public com.services.soap.apiclient.esale.CardShopAPIServiceSoap getCardShopAPIServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.esale.CardShopAPIServiceSoap12Stub _stub = new com.services.soap.apiclient.esale.CardShopAPIServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getCardShopAPIServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCardShopAPIServiceSoap12EndpointAddress(java.lang.String address) {
        CardShopAPIServiceSoap12_address = address;
    }


    // Use to get a proxy class for CardShopAPIServiceSoap
    private java.lang.String CardShopAPIServiceSoap_address = "http://10.30.17.193/eSaleAPIv2/eSaleServices.asmx";

    public java.lang.String getCardShopAPIServiceSoapAddress() {
        return CardShopAPIServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CardShopAPIServiceSoapWSDDServiceName = "CardShopAPIServiceSoap";

    public java.lang.String getCardShopAPIServiceSoapWSDDServiceName() {
        return CardShopAPIServiceSoapWSDDServiceName;
    }

    public void setCardShopAPIServiceSoapWSDDServiceName(java.lang.String name) {
        CardShopAPIServiceSoapWSDDServiceName = name;
    }

    public com.services.soap.apiclient.esale.CardShopAPIServiceSoap getCardShopAPIServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CardShopAPIServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCardShopAPIServiceSoap(endpoint);
    }

    public com.services.soap.apiclient.esale.CardShopAPIServiceSoap getCardShopAPIServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.services.soap.apiclient.esale.CardShopAPIServiceSoapStub _stub = new com.services.soap.apiclient.esale.CardShopAPIServiceSoapStub(portAddress, this);
            _stub.setPortName(getCardShopAPIServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCardShopAPIServiceSoapEndpointAddress(java.lang.String address) {
        CardShopAPIServiceSoap_address = address;
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
            if (com.services.soap.apiclient.esale.CardShopAPIServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.esale.CardShopAPIServiceSoap12Stub _stub = new com.services.soap.apiclient.esale.CardShopAPIServiceSoap12Stub(new java.net.URL(CardShopAPIServiceSoap12_address), this);
                _stub.setPortName(getCardShopAPIServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (com.services.soap.apiclient.esale.CardShopAPIServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.services.soap.apiclient.esale.CardShopAPIServiceSoapStub _stub = new com.services.soap.apiclient.esale.CardShopAPIServiceSoapStub(new java.net.URL(CardShopAPIServiceSoap_address), this);
                _stub.setPortName(getCardShopAPIServiceSoapWSDDServiceName());
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
        if ("CardShopAPIServiceSoap12".equals(inputPortName)) {
            return getCardShopAPIServiceSoap12();
        }
        else if ("CardShopAPIServiceSoap".equals(inputPortName)) {
            return getCardShopAPIServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardShopAPIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardShopAPIServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardShopAPIServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CardShopAPIServiceSoap12".equals(portName)) {
            setCardShopAPIServiceSoap12EndpointAddress(address);
        }
        else 
if ("CardShopAPIServiceSoap".equals(portName)) {
            setCardShopAPIServiceSoapEndpointAddress(address);
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
