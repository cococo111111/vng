/**
 * AdsRMTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.adsnew.ws;

public class AdsRMTNEWServiceLocator extends org.apache.axis.client.Service
		implements com.vng.adsnew.ws.AdsRMTNEWService {

	public AdsRMTNEWServiceLocator() {
	}

	public AdsRMTNEWServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public AdsRMTNEWServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for AdsRMT
	private java.lang.String AdsRMT_address = "http://localhost:8080/axis/services/AdsRMTNEW";

	public java.lang.String getAdsRMTAddress() {
		return AdsRMT_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String AdsRMTWSDDServiceName = "AdsRMTNEW";

	public java.lang.String getAdsRMTWSDDServiceName() {
		return AdsRMTWSDDServiceName;
	}

	public void setAdsRMTWSDDServiceName(java.lang.String name) {
		AdsRMTWSDDServiceName = name;
	}

	public com.vng.adsnew.ws.AdsRMTNEW getAdsRMT()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(AdsRMT_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getAdsRMT(endpoint);
	}

	public com.vng.adsnew.ws.AdsRMTNEW getAdsRMT(java.net.URL portAddress)
			throws javax.xml.rpc.ServiceException {
		try {
			com.vng.adsnew.ws.AdsRMTNEWSoapBindingStub _stub = new com.vng.adsnew.ws.AdsRMTNEWSoapBindingStub(
					portAddress, this);
			_stub.setPortName(getAdsRMTWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setAdsRMTEndpointAddress(java.lang.String address) {
		AdsRMT_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (com.vng.adsnew.ws.AdsRMTNEW.class
					.isAssignableFrom(serviceEndpointInterface)) {
				com.vng.adsnew.ws.AdsRMTNEWSoapBindingStub _stub = new com.vng.adsnew.ws.AdsRMTNEWSoapBindingStub(
						new java.net.URL(AdsRMT_address), this);
				_stub.setPortName(getAdsRMTWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException(
				"There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null"
								: serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("AdsRMT".equals(inputPortName)) {
			return getAdsRMT();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://ws.adsnew.vng.com",
				"AdsRMTNEWService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://ws.adsnew.vng.com",
					"AdsRMTNEW"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("AdsRMT".equals(portName)) {
			setAdsRMTEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(
					" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
