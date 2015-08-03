/**
 * Receiver.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.vmg;

public interface Receiver extends java.rmi.Remote {
    public int moReceiverStandard(java.lang.String user_ID, java.lang.String service_ID, java.lang.String command_Code, java.lang.String message, java.lang.String request_ID, java.lang.String operator) throws java.rmi.RemoteException;
    public int moReceiverVNG(java.lang.String user_ID, java.lang.String service_ID, java.lang.String command_Code, java.lang.String message, java.lang.String request_ID, java.lang.String operator, java.lang.String content_ID) throws java.rmi.RemoteException;
    public int moReceiver(java.lang.String msgSequence, java.lang.String senderNumber, java.lang.String serviceNumber, java.lang.String timestamp, java.lang.String smsContent, java.lang.String commandCode) throws java.rmi.RemoteException;
    public int mtReceiver(java.lang.String user_ID, java.lang.String message, java.lang.String service_ID, java.lang.String command_Code, java.lang.String message_Type, java.lang.String request_ID, java.lang.String total_Message, java.lang.String message_Index, java.lang.String isMore, java.lang.String content_Type, java.lang.String operator) throws java.rmi.RemoteException;
}
