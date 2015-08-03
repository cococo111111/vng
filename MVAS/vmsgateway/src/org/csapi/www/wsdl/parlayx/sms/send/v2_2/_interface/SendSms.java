/**
 * SendSms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface;

public interface SendSms extends java.rmi.Remote {
    public java.lang.String sendSms(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, java.lang.String message, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException;
    public java.lang.String sendSmsLogo(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, byte[] image, org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat smsFormat, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException;
    public java.lang.String sendSmsRingtone(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, java.lang.String ringtone, org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat smsFormat, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException;
    public org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[] getSmsDeliveryStatus(java.lang.String requestIdentifier) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException;
}
