/**
 * CPRMTSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.cpnew.ws;

public class CPRMTNEWSoapBindingSkeleton implements com.vng.cpnew.ws.CPRMTNEW,
		org.apache.axis.wsdl.Skeleton {
	private com.vng.cpnew.ws.CPRMTNEW impl;
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
						new javax.xml.namespace.QName("", "messageType"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://www.w3.org/2001/XMLSchema", "int"),
						int.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "mobileOperator"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://schemas.xmlsoap.org/soap/encoding/",
								"string"), java.lang.String.class, false, false),
				new org.apache.axis.description.ParameterDesc(
						new javax.xml.namespace.QName("", "sumMT"),
						org.apache.axis.description.ParameterDesc.IN,
						new javax.xml.namespace.QName(
								"http://www.w3.org/2001/XMLSchema", "int"),
						int.class, false, false),
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
				"http://ws.cpnew.vng.com", "mtReceiver"));
		_oper.setSoapAction("");
		_myOperationsList.add(_oper);
		if (_myOperations.get("mtReceiver") == null) {
			_myOperations.put("mtReceiver", new java.util.ArrayList());
		}
		((java.util.List) _myOperations.get("mtReceiver")).add(_oper);
	}

	public CPRMTNEWSoapBindingSkeleton() {
		this.impl = new com.vng.cpnew.ws.CPRMTNEWSoapBindingImpl();
	}

	public CPRMTNEWSoapBindingSkeleton(com.vng.cpnew.ws.CPRMTNEW impl) {
		this.impl = impl;
	}

	public int mtReceiver(java.lang.String requestID, java.lang.String userID,
			java.lang.String serviceID, java.lang.String commandCode,
			java.lang.String message, int messageType,
			java.lang.String mobileOperator, int sumMT, java.lang.String sig)
			throws java.rmi.RemoteException {
		int ret = impl.mtReceiver(requestID, userID, serviceID, commandCode,
				message, messageType, mobileOperator, sumMT, sig);
		return ret;
	}

}
