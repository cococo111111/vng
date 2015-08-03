/**
 * GetPaymentMethodData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class GetPaymentMethodData  implements java.io.Serializable {
    private int paymentMethodId;  // attribute

    private int paymentTypeId;  // attribute

    private java.lang.String paymentTypeName;  // attribute

    private int areaId;  // attribute

    private java.lang.String areaDesc;  // attribute

    private int bankId;  // attribute

    private java.lang.String bankName;  // attribute

    private java.lang.String logo;  // attribute

    private java.lang.String infoMethod;  // attribute

    public GetPaymentMethodData() {
    }

    public GetPaymentMethodData(
           int paymentMethodId,
           int paymentTypeId,
           java.lang.String paymentTypeName,
           int areaId,
           java.lang.String areaDesc,
           int bankId,
           java.lang.String bankName,
           java.lang.String logo,
           java.lang.String infoMethod) {
           this.paymentMethodId = paymentMethodId;
           this.paymentTypeId = paymentTypeId;
           this.paymentTypeName = paymentTypeName;
           this.areaId = areaId;
           this.areaDesc = areaDesc;
           this.bankId = bankId;
           this.bankName = bankName;
           this.logo = logo;
           this.infoMethod = infoMethod;
    }


    /**
     * Gets the paymentMethodId value for this GetPaymentMethodData.
     * 
     * @return paymentMethodId
     */
    public int getPaymentMethodId() {
        return paymentMethodId;
    }


    /**
     * Sets the paymentMethodId value for this GetPaymentMethodData.
     * 
     * @param paymentMethodId
     */
    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }


    /**
     * Gets the paymentTypeId value for this GetPaymentMethodData.
     * 
     * @return paymentTypeId
     */
    public int getPaymentTypeId() {
        return paymentTypeId;
    }


    /**
     * Sets the paymentTypeId value for this GetPaymentMethodData.
     * 
     * @param paymentTypeId
     */
    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }


    /**
     * Gets the paymentTypeName value for this GetPaymentMethodData.
     * 
     * @return paymentTypeName
     */
    public java.lang.String getPaymentTypeName() {
        return paymentTypeName;
    }


    /**
     * Sets the paymentTypeName value for this GetPaymentMethodData.
     * 
     * @param paymentTypeName
     */
    public void setPaymentTypeName(java.lang.String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }


    /**
     * Gets the areaId value for this GetPaymentMethodData.
     * 
     * @return areaId
     */
    public int getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this GetPaymentMethodData.
     * 
     * @param areaId
     */
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the areaDesc value for this GetPaymentMethodData.
     * 
     * @return areaDesc
     */
    public java.lang.String getAreaDesc() {
        return areaDesc;
    }


    /**
     * Sets the areaDesc value for this GetPaymentMethodData.
     * 
     * @param areaDesc
     */
    public void setAreaDesc(java.lang.String areaDesc) {
        this.areaDesc = areaDesc;
    }


    /**
     * Gets the bankId value for this GetPaymentMethodData.
     * 
     * @return bankId
     */
    public int getBankId() {
        return bankId;
    }


    /**
     * Sets the bankId value for this GetPaymentMethodData.
     * 
     * @param bankId
     */
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }


    /**
     * Gets the bankName value for this GetPaymentMethodData.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this GetPaymentMethodData.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the logo value for this GetPaymentMethodData.
     * 
     * @return logo
     */
    public java.lang.String getLogo() {
        return logo;
    }


    /**
     * Sets the logo value for this GetPaymentMethodData.
     * 
     * @param logo
     */
    public void setLogo(java.lang.String logo) {
        this.logo = logo;
    }


    /**
     * Gets the infoMethod value for this GetPaymentMethodData.
     * 
     * @return infoMethod
     */
    public java.lang.String getInfoMethod() {
        return infoMethod;
    }


    /**
     * Sets the infoMethod value for this GetPaymentMethodData.
     * 
     * @param infoMethod
     */
    public void setInfoMethod(java.lang.String infoMethod) {
        this.infoMethod = infoMethod;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPaymentMethodData)) return false;
        GetPaymentMethodData other = (GetPaymentMethodData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.paymentMethodId == other.getPaymentMethodId() &&
            this.paymentTypeId == other.getPaymentTypeId() &&
            ((this.paymentTypeName==null && other.getPaymentTypeName()==null) || 
             (this.paymentTypeName!=null &&
              this.paymentTypeName.equals(other.getPaymentTypeName()))) &&
            this.areaId == other.getAreaId() &&
            ((this.areaDesc==null && other.getAreaDesc()==null) || 
             (this.areaDesc!=null &&
              this.areaDesc.equals(other.getAreaDesc()))) &&
            this.bankId == other.getBankId() &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            ((this.logo==null && other.getLogo()==null) || 
             (this.logo!=null &&
              this.logo.equals(other.getLogo()))) &&
            ((this.infoMethod==null && other.getInfoMethod()==null) || 
             (this.infoMethod!=null &&
              this.infoMethod.equals(other.getInfoMethod())));
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
        _hashCode += getPaymentMethodId();
        _hashCode += getPaymentTypeId();
        if (getPaymentTypeName() != null) {
            _hashCode += getPaymentTypeName().hashCode();
        }
        _hashCode += getAreaId();
        if (getAreaDesc() != null) {
            _hashCode += getAreaDesc().hashCode();
        }
        _hashCode += getBankId();
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        if (getLogo() != null) {
            _hashCode += getLogo().hashCode();
        }
        if (getInfoMethod() != null) {
            _hashCode += getInfoMethod().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPaymentMethodData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethodData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("paymentMethodId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PaymentMethodId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("paymentTypeId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PaymentTypeId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("paymentTypeName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PaymentTypeName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("areaId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "AreaId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("areaDesc");
        attrField.setXmlName(new javax.xml.namespace.QName("", "AreaDesc"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("bankId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "BankId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("bankName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "BankName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("logo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Logo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("infoMethod");
        attrField.setXmlName(new javax.xml.namespace.QName("", "InfoMethod"));
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
