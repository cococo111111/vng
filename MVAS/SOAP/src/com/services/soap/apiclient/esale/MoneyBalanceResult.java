/**
 * MoneyBalanceResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class MoneyBalanceResult  implements java.io.Serializable {
    private int returnCode;

    private long balance;

    private long transferInDay;

    public MoneyBalanceResult() {
    }

    public MoneyBalanceResult(
           int returnCode,
           long balance,
           long transferInDay) {
           this.returnCode = returnCode;
           this.balance = balance;
           this.transferInDay = transferInDay;
    }


    /**
     * Gets the returnCode value for this MoneyBalanceResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this MoneyBalanceResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the balance value for this MoneyBalanceResult.
     * 
     * @return balance
     */
    public long getBalance() {
        return balance;
    }


    /**
     * Sets the balance value for this MoneyBalanceResult.
     * 
     * @param balance
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }


    /**
     * Gets the transferInDay value for this MoneyBalanceResult.
     * 
     * @return transferInDay
     */
    public long getTransferInDay() {
        return transferInDay;
    }


    /**
     * Sets the transferInDay value for this MoneyBalanceResult.
     * 
     * @param transferInDay
     */
    public void setTransferInDay(long transferInDay) {
        this.transferInDay = transferInDay;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MoneyBalanceResult)) return false;
        MoneyBalanceResult other = (MoneyBalanceResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            this.balance == other.getBalance() &&
            this.transferInDay == other.getTransferInDay();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getReturnCode();
        _hashCode += new Long(getBalance()).hashCode();
        _hashCode += new Long(getTransferInDay()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MoneyBalanceResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "MoneyBalanceResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Balance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transferInDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TransferInDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
