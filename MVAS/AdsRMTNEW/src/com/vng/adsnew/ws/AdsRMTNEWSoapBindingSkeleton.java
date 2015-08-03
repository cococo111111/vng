/**
 * AdsRMTSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.adsnew.ws;

public class AdsRMTNEWSoapBindingSkeleton implements
		com.vng.adsnew.ws.AdsRMTNEW, org.apache.axis.wsdl.Skeleton {
	private com.vng.adsnew.ws.AdsRMTNEW impl;
	private static java.util.Map _myOperations = new java.util.Hashtable();
	private static java.util.Collection _myOperationsList = new java.util.ArrayList();

	/**
	 * Returns List of OperationDesc objects with this name
	 */
	public static java.util.List getOperationDescByName(
			java.lang.String methodName) {
		return (java.util.List) _myOperations.get(methodName);
	}

	/**
	 * Returns Collection of OperationDescs
	 */
	public static java.util.Collection getOperationDescs() {
		return _myOperationsList;
	}

	static {
		org.apache.axis.description.OperationDesc _oper;
		org.apache.axis.description.FaultDesc _fault;
		org.apache.axis.description.ParameterDesc[] _params;
		_params = new org.apache.axis.description.ParameterDesc[] {
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "requestID"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "userID"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "serviceID"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "commandCode"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "message"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "sig"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false), };
		_oper = new org.apache.axis.description.OperationDesc("mtReceiver",
				_params, new javax.xml.namespace.QName("", "mtReceiverReturn"));
		_oper.setReturnType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "int"));
		_oper.setElementQName(new javax.xml.namespace.QName(
				"http://ws.adsnew.vng.com", "mtReceiver"));
		_oper.setSoapAction("");
		_myOperationsList.add(_oper);
		if (_myOperations.get("mtReceiver") == null) {
			_myOperations.put("mtReceiver", new java.util.ArrayList());
		}
		((java.util.List) _myOperations.get("mtReceiver")).add(_oper);
	}

	public AdsRMTNEWSoapBindingSkeleton() {
		this.impl = new com.vng.adsnew.ws.AdsRMTNEWSoapBindingImpl();
	}

	public AdsRMTNEWSoapBindingSkeleton(com.vng.adsnew.ws.AdsRMTNEW impl) {
		this.impl = impl;
	}

	public int mtReceiver(java.lang.String requestID, java.lang.String userID,
			java.lang.String serviceID, java.lang.String commandCode,
			java.lang.String message, java.lang.String sig)
			throws java.rmi.RemoteException {
		int ret = impl.mtReceiver(requestID, userID, serviceID, commandCode,
				message, sig);
		return ret;
	}

}
