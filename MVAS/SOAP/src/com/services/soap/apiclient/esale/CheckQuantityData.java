/**
 * CheckQuantityData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class CheckQuantityData  implements java.io.Serializable {
    private int returnCode;  // attribute

    private int numOfCardCurrent;  // attribute

    private java.lang.String key;  // attribute

    private int supplierId;  // attribute

    private int supplierCardId;  // attribute

    private int quantity;  // attribute

    public CheckQuantityData() {
    }

    public CheckQuantityData(
           int returnCode,
           int numOfCardCurrent,
           java.lang.String key,
           int supplierId,
           int supplierCardId,
           int quantity) {
           this.returnCode = returnCode;
           this.numOfCardCurrent = numOfCardCurrent;
           this.key = key;
           this.supplierId = supplierId;
           this.supplierCardId = supplierCardId;
           this.quantity = quantity;
    }


    /**
     * Gets the returnCode value for this CheckQuantityData.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this CheckQuantityData.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the numOfCardCurrent value for this CheckQuantityData.
     * 
     * @return numOfCardCurrent
     */
    public int getNumOfCardCurrent() {
        return numOfCardCurrent;
    }


    /**
     * Sets the numOfCardCurrent value for this CheckQuantityData.
     * 
     * @param numOfCardCurrent
     */
    public void setNumOfCardCurrent(int numOfCardCurrent) {
        this.numOfCardCurrent = numOfCardCurrent;
    }


    /**
     * Gets the key value for this CheckQuantityData.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this CheckQuantityData.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the supplierId value for this CheckQuantityData.
     * 
     * @return supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }


    /**
     * Sets the supplierId value for this CheckQuantityData.
     * 
     * @param supplierId
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    /**
     * Gets the supplierCardId value for this CheckQuantityData.
     * 
     * @return supplierCardId
     */
    public int getSupplierCardId() {
        return supplierCardId;
    }


    /**
     * Sets the supplierCardId value for this CheckQuantityData.
     * 
     * @param supplierCardId
     */
    public void setSupplierCardId(int supplierCardId) {
        this.supplierCardId = supplierCardId;
    }


    /**
     * Gets the quantity value for this CheckQuantityData.
     * 
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this CheckQuantityData.
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckQuantityData)) return false;
        CheckQuantityData other = (CheckQuantityData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            this.numOfCardCurrent == other.getNumOfCardCurrent() &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            this.supplierId == other.getSupplierId() &&
            this.supplierCardId == other.getSupplierCardId() &&
            this.quantity == other.getQuantity();
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
        _hashCode += getNumOfCardCurrent();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        _hashCode += getSupplierId();
        _hashCode += getSupplierCardId();
        _hashCode += getQuantity();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckQuantityData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantityData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("returnCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ReturnCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("numOfCardCurrent");
        attrField.setXmlName(new javax.xml.namespace.QName("", "NumOfCardCurrent"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("key");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Key"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierCardId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierCardId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantity");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
