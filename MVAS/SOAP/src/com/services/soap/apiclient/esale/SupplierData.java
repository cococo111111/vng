/**
 * SupplierData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class SupplierData  implements java.io.Serializable {
    private int supplierId;  // attribute

    private java.lang.String supplierCode;  // attribute

    private java.lang.String supplierName;  // attribute

    private int statusId;  // attribute

    private int supplierTypeId;  // attribute

    private java.lang.String supplierTypeCode;  // attribute

    public SupplierData() {
    }

    public SupplierData(
           int supplierId,
           java.lang.String supplierCode,
           java.lang.String supplierName,
           int statusId,
           int supplierTypeId,
           java.lang.String supplierTypeCode) {
           this.supplierId = supplierId;
           this.supplierCode = supplierCode;
           this.supplierName = supplierName;
           this.statusId = statusId;
           this.supplierTypeId = supplierTypeId;
           this.supplierTypeCode = supplierTypeCode;
    }


    /**
     * Gets the supplierId value for this SupplierData.
     * 
     * @return supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }


    /**
     * Sets the supplierId value for this SupplierData.
     * 
     * @param supplierId
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    /**
     * Gets the supplierCode value for this SupplierData.
     * 
     * @return supplierCode
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }


    /**
     * Sets the supplierCode value for this SupplierData.
     * 
     * @param supplierCode
     */
    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }


    /**
     * Gets the supplierName value for this SupplierData.
     * 
     * @return supplierName
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }


    /**
     * Sets the supplierName value for this SupplierData.
     * 
     * @param supplierName
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }


    /**
     * Gets the statusId value for this SupplierData.
     * 
     * @return statusId
     */
    public int getStatusId() {
        return statusId;
    }


    /**
     * Sets the statusId value for this SupplierData.
     * 
     * @param statusId
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }


    /**
     * Gets the supplierTypeId value for this SupplierData.
     * 
     * @return supplierTypeId
     */
    public int getSupplierTypeId() {
        return supplierTypeId;
    }


    /**
     * Sets the supplierTypeId value for this SupplierData.
     * 
     * @param supplierTypeId
     */
    public void setSupplierTypeId(int supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }


    /**
     * Gets the supplierTypeCode value for this SupplierData.
     * 
     * @return supplierTypeCode
     */
    public java.lang.String getSupplierTypeCode() {
        return supplierTypeCode;
    }


    /**
     * Sets the supplierTypeCode value for this SupplierData.
     * 
     * @param supplierTypeCode
     */
    public void setSupplierTypeCode(java.lang.String supplierTypeCode) {
        this.supplierTypeCode = supplierTypeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SupplierData)) return false;
        SupplierData other = (SupplierData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.supplierId == other.getSupplierId() &&
            ((this.supplierCode==null && other.getSupplierCode()==null) || 
             (this.supplierCode!=null &&
              this.supplierCode.equals(other.getSupplierCode()))) &&
            ((this.supplierName==null && other.getSupplierName()==null) || 
             (this.supplierName!=null &&
              this.supplierName.equals(other.getSupplierName()))) &&
            this.statusId == other.getStatusId() &&
            this.supplierTypeId == other.getSupplierTypeId() &&
            ((this.supplierTypeCode==null && other.getSupplierTypeCode()==null) || 
             (this.supplierTypeCode!=null &&
              this.supplierTypeCode.equals(other.getSupplierTypeCode())));
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
        _hashCode += getSupplierId();
        if (getSupplierCode() != null) {
            _hashCode += getSupplierCode().hashCode();
        }
        if (getSupplierName() != null) {
            _hashCode += getSupplierName().hashCode();
        }
        _hashCode += getStatusId();
        _hashCode += getSupplierTypeId();
        if (getSupplierTypeCode() != null) {
            _hashCode += getSupplierTypeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SupplierData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("statusId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "StatusId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierTypeId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierTypeId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierTypeCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierTypeCode"));
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
