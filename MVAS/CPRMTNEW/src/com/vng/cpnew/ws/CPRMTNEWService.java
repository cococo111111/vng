/**
 * CPRMTService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.cpnew.ws;

public interface CPRMTNEWService extends javax.xml.rpc.Service {
	public java.lang.String getCPRMTAddress();

	public com.vng.cpnew.ws.CPRMTNEW getCPRMT()
			throws javax.xml.rpc.ServiceException;

	public com.vng.cpnew.ws.CPRMTNEW getCPRMT(java.net.URL portAddress)
			throws javax.xml.rpc.ServiceException;
}
