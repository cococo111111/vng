/**
 * SmssoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.isunlock;

public class SmssoapServiceLocator extends org.apache.axis.client.Service
		implements com.services.soap.apiclient.isunlock.SmssoapService {

	public SmssoapServiceLocator() {
	}

	public SmssoapServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public SmssoapServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for SmssoapPort
	
	//private java.lang.String SmssoapPort_address = "https://esb.vng.com.vn:8443/SMSWS/ReceiverMO";
	private java.lang.String SmssoapPort_address = "http://10.30.97.61:8080/SMSWS/ReceiverMO";

	public java.lang.String getSmssoapPortAddress() {
		return SmssoapPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String SmssoapPortWSDDServiceName = "ReceiverMOPort";

	public java.lang.String getSmssoapPortWSDDServiceName() {
		return SmssoapPortWSDDServiceName;
	}

	public void setSmssoapPortWSDDServiceName(java.lang.String name) {
		SmssoapPortWSDDServiceName = name;
	}

	public com.services.soap.apiclient.isunlock.SmssoapPort getSmssoapPort()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(SmssoapPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getSmssoapPort(endpoint);
	}

	public com.services.soap.apiclient.isunlock.SmssoapPort getSmssoapPort(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			com.services.soap.apiclient.isunlock.SmssoapBindingStub _stub = new com.services.soap.apiclient.isunlock.SmssoapBindingStub(
					portAddress, this);
			_stub.setPortName(getSmssoapPortWSDDServiceName());
			//System.out.println(_stub.getUsername());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setSmssoapPortEndpointAddress(java.lang.String address) {
		SmssoapPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (com.services.soap.apiclient.isunlock.SmssoapPort.class
					.isAssignableFrom(serviceEndpointInterface)) {
				com.services.soap.apiclient.isunlock.SmssoapBindingStub _stub = new com.services.soap.apiclient.isunlock.SmssoapBindingStub(
						new java.net.URL(SmssoapPort_address), this);
				_stub.setPortName(getSmssoapPortWSDDServiceName());
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
		if ("ReceiverMO".equals(inputPortName)) {
			return getSmssoapPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://tempuri.org/", "ReceiverMOImplService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("", "ReceiverMO"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("ReceiverMO".equals(portName)) {
			setSmssoapPortEndpointAddress(address);
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
