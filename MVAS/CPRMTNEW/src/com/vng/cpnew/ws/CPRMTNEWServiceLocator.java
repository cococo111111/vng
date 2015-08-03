/**
 * CPRMTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.cpnew.ws;

public class CPRMTNEWServiceLocator extends org.apache.axis.client.Service
		implements com.vng.cpnew.ws.CPRMTNEWService {

	public CPRMTNEWServiceLocator() {
	}

	public CPRMTNEWServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public CPRMTNEWServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for CPRMT
	private java.lang.String CPRMT_address = "http://localhost:8080/axis/services/CPRMT";

	public java.lang.String getCPRMTAddress() {
		return CPRMT_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String CPRMTWSDDServiceName = "CPRMT";

	public java.lang.String getCPRMTWSDDServiceName() {
		return CPRMTWSDDServiceName;
	}

	public void setCPRMTWSDDServiceName(java.lang.String name) {
		CPRMTWSDDServiceName = name;
	}

	public com.vng.cpnew.ws.CPRMTNEW getCPRMT()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(CPRMT_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getCPRMT(endpoint);
	}

	public com.vng.cpnew.ws.CPRMTNEW getCPRMT(java.net.URL portAddress)
			throws javax.xml.rpc.ServiceException {
		try {
			com.vng.cpnew.ws.CPRMTNEWSoapBindingStub _stub = new com.vng.cpnew.ws.CPRMTNEWSoapBindingStub(
					portAddress, this);
			_stub.setPortName(getCPRMTWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setCPRMTEndpointAddress(java.lang.String address) {
		CPRMT_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@Override
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (com.vng.cpnew.ws.CPRMTNEW.class
					.isAssignableFrom(serviceEndpointInterface)) {
				com.vng.cpnew.ws.CPRMTNEWSoapBindingStub _stub = new com.vng.cpnew.ws.CPRMTNEWSoapBindingStub(
						new java.net.URL(CPRMT_address), this);
				_stub.setPortName(getCPRMTWSDDServiceName());
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
	@Override
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("CPRMT".equals(inputPortName)) {
			return getCPRMT();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	@Override
	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://ws.cp.vng.com",
				"CPRMTService");
	}

	private java.util.HashSet ports = null;

	@Override
	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://ws.cp.vng.com",
					"CPRMT"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("CPRMT".equals(portName)) {
			setCPRMTEndpointAddress(address);
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
