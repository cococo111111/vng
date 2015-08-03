/**
 * ReportAgencySaleCSDetailByDateData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportAgencySaleCSDetailByDateData  implements java.io.Serializable {
    private java.lang.String createdDate;  // attribute

    private java.lang.String orderNo;  // attribute

    private java.lang.String supplierName;  // attribute

    private java.lang.String prefixCode;  // attribute

    private java.math.BigDecimal amount;  // attribute

    private java.lang.String serial;  // attribute

    private java.lang.String saller;  // attribute

    private int totalRecords;  // attribute

    public ReportAgencySaleCSDetailByDateData() {
    }

    public ReportAgencySaleCSDetailByDateData(
           java.lang.String createdDate,
           java.lang.String orderNo,
           java.lang.String supplierName,
           java.lang.String prefixCode,
           java.math.BigDecimal amount,
           java.lang.String serial,
           java.lang.String saller,
           int totalRecords) {
           this.createdDate = createdDate;
           this.orderNo = orderNo;
           this.supplierName = supplierName;
           this.prefixCode = prefixCode;
           this.amount = amount;
           this.serial = serial;
           this.saller = saller;
           this.totalRecords = totalRecords;
    }


    /**
     * Gets the createdDate value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return createdDate
     */
    public java.lang.String getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.lang.String createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the orderNo value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return orderNo
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }


    /**
     * Sets the orderNo value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Gets the supplierName value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return supplierName
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }


    /**
     * Sets the supplierName value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param supplierName
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }


    /**
     * Gets the prefixCode value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return prefixCode
     */
    public java.lang.String getPrefixCode() {
        return prefixCode;
    }


    /**
     * Sets the prefixCode value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param prefixCode
     */
    public void setPrefixCode(java.lang.String prefixCode) {
        this.prefixCode = prefixCode;
    }


    /**
     * Gets the amount value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return amount
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param amount
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }


    /**
     * Gets the serial value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return serial
     */
    public java.lang.String getSerial() {
        return serial;
    }


    /**
     * Sets the serial value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param serial
     */
    public void setSerial(java.lang.String serial) {
        this.serial = serial;
    }


    /**
     * Gets the saller value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return saller
     */
    public java.lang.String getSaller() {
        return saller;
    }


    /**
     * Sets the saller value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param saller
     */
    public void setSaller(java.lang.String saller) {
        this.saller = saller;
    }


    /**
     * Gets the totalRecords value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @return totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }


    /**
     * Sets the totalRecords value for this ReportAgencySaleCSDetailByDateData.
     * 
     * @param totalRecords
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportAgencySaleCSDetailByDateData)) return false;
        ReportAgencySaleCSDetailByDateData other = (ReportAgencySaleCSDetailByDateData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.orderNo==null && other.getOrderNo()==null) || 
             (this.orderNo!=null &&
              this.orderNo.equals(other.getOrderNo()))) &&
            ((this.supplierName==null && other.getSupplierName()==null) || 
             (this.supplierName!=null &&
              this.supplierName.equals(other.getSupplierName()))) &&
            ((this.prefixCode==null && other.getPrefixCode()==null) || 
             (this.prefixCode!=null &&
              this.prefixCode.equals(other.getPrefixCode()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.serial==null && other.getSerial()==null) || 
             (this.serial!=null &&
              this.serial.equals(other.getSerial()))) &&
            ((this.saller==null && other.getSaller()==null) || 
             (this.saller!=null &&
              this.saller.equals(other.getSaller()))) &&
            this.totalRecords == other.getTotalRecords();
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
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getOrderNo() != null) {
            _hashCode += getOrderNo().hashCode();
        }
        if (getSupplierName() != null) {
            _hashCode += getSupplierName().hashCode();
        }
        if (getPrefixCode() != null) {
            _hashCode += getPrefixCode().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getSerial() != null) {
            _hashCode += getSerial().hashCode();
        }
        if (getSaller() != null) {
            _hashCode += getSaller().hashCode();
        }
        _hashCode += getTotalRecords();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportAgencySaleCSDetailByDateData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDateData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("createdDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CreatedDate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("orderNo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OrderNo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("prefixCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PrefixCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("amount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Amount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("serial");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Serial"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("saller");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Saller"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalRecords");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalRecords"));
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
