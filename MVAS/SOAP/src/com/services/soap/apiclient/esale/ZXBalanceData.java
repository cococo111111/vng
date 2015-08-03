/**
 * ZXBalanceData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ZXBalanceData  implements java.io.Serializable {
    private int ZXBalance;  // attribute

    private int ZXQuotaPerDay;  // attribute

    private int ZXBalanceInDay;  // attribute

    private int ZXTransferredInDay;  // attribute

    private java.lang.String agencyCode;  // attribute

    private java.lang.String transactionDate;  // attribute

    public ZXBalanceData() {
    }

    public ZXBalanceData(
           int ZXBalance,
           int ZXQuotaPerDay,
           int ZXBalanceInDay,
           int ZXTransferredInDay,
           java.lang.String agencyCode,
           java.lang.String transactionDate) {
           this.ZXBalance = ZXBalance;
           this.ZXQuotaPerDay = ZXQuotaPerDay;
           this.ZXBalanceInDay = ZXBalanceInDay;
           this.ZXTransferredInDay = ZXTransferredInDay;
           this.agencyCode = agencyCode;
           this.transactionDate = transactionDate;
    }


    /**
     * Gets the ZXBalance value for this ZXBalanceData.
     * 
     * @return ZXBalance
     */
    public int getZXBalance() {
        return ZXBalance;
    }


    /**
     * Sets the ZXBalance value for this ZXBalanceData.
     * 
     * @param ZXBalance
     */
    public void setZXBalance(int ZXBalance) {
        this.ZXBalance = ZXBalance;
    }


    /**
     * Gets the ZXQuotaPerDay value for this ZXBalanceData.
     * 
     * @return ZXQuotaPerDay
     */
    public int getZXQuotaPerDay() {
        return ZXQuotaPerDay;
    }


    /**
     * Sets the ZXQuotaPerDay value for this ZXBalanceData.
     * 
     * @param ZXQuotaPerDay
     */
    public void setZXQuotaPerDay(int ZXQuotaPerDay) {
        this.ZXQuotaPerDay = ZXQuotaPerDay;
    }


    /**
     * Gets the ZXBalanceInDay value for this ZXBalanceData.
     * 
     * @return ZXBalanceInDay
     */
    public int getZXBalanceInDay() {
        return ZXBalanceInDay;
    }


    /**
     * Sets the ZXBalanceInDay value for this ZXBalanceData.
     * 
     * @param ZXBalanceInDay
     */
    public void setZXBalanceInDay(int ZXBalanceInDay) {
        this.ZXBalanceInDay = ZXBalanceInDay;
    }


    /**
     * Gets the ZXTransferredInDay value for this ZXBalanceData.
     * 
     * @return ZXTransferredInDay
     */
    public int getZXTransferredInDay() {
        return ZXTransferredInDay;
    }


    /**
     * Sets the ZXTransferredInDay value for this ZXBalanceData.
     * 
     * @param ZXTransferredInDay
     */
    public void setZXTransferredInDay(int ZXTransferredInDay) {
        this.ZXTransferredInDay = ZXTransferredInDay;
    }


    /**
     * Gets the agencyCode value for this ZXBalanceData.
     * 
     * @return agencyCode
     */
    public java.lang.String getAgencyCode() {
        return agencyCode;
    }


    /**
     * Sets the agencyCode value for this ZXBalanceData.
     * 
     * @param agencyCode
     */
    public void setAgencyCode(java.lang.String agencyCode) {
        this.agencyCode = agencyCode;
    }


    /**
     * Gets the transactionDate value for this ZXBalanceData.
     * 
     * @return transactionDate
     */
    public java.lang.String getTransactionDate() {
        return transactionDate;
    }


    /**
     * Sets the transactionDate value for this ZXBalanceData.
     * 
     * @param transactionDate
     */
    public void setTransactionDate(java.lang.String transactionDate) {
        this.transactionDate = transactionDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZXBalanceData)) return false;
        ZXBalanceData other = (ZXBalanceData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ZXBalance == other.getZXBalance() &&
            this.ZXQuotaPerDay == other.getZXQuotaPerDay() &&
            this.ZXBalanceInDay == other.getZXBalanceInDay() &&
            this.ZXTransferredInDay == other.getZXTransferredInDay() &&
            ((this.agencyCode==null && other.getAgencyCode()==null) || 
             (this.agencyCode!=null &&
              this.agencyCode.equals(other.getAgencyCode()))) &&
            ((this.transactionDate==null && other.getTransactionDate()==null) || 
             (this.transactionDate!=null &&
              this.transactionDate.equals(other.getTransactionDate())));
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
        _hashCode += getZXBalance();
        _hashCode += getZXQuotaPerDay();
        _hashCode += getZXBalanceInDay();
        _hashCode += getZXTransferredInDay();
        if (getAgencyCode() != null) {
            _hashCode += getAgencyCode().hashCode();
        }
        if (getTransactionDate() != null) {
            _hashCode += getTransactionDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZXBalanceData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ZXBalanceData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ZXBalance");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ZXBalance"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ZXQuotaPerDay");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ZXQuotaPerDay"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ZXBalanceInDay");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ZXBalanceInDay"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ZXTransferredInDay");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ZXTransferredInDay"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("agencyCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "AgencyCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transactionDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TransactionDate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
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
