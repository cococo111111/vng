/**
 * SmsNotification.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.wsdl.parlayx.sms.notification.v2_2._interface;

public interface SmsNotification extends java.rmi.Remote {
    public void notifySmsReception(java.lang.String correlator, org.csapi.www.schema.parlayx.sms.v2_2.SmsMessage message) throws java.rmi.RemoteException;
    public void notifySmsDeliveryReceipt(java.lang.String correlator, org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation deliveryStatus) throws java.rmi.RemoteException;
}
