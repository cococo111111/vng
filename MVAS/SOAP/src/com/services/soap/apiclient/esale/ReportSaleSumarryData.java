/**
 * ReportSaleSumarryData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportSaleSumarryData  implements java.io.Serializable {
    private java.math.BigDecimal totalAmount;  // attribute

    private int totalRecords;  // attribute

    private java.lang.String reportDate;  // attribute

    public ReportSaleSumarryData() {
    }

    public ReportSaleSumarryData(
           java.math.BigDecimal totalAmount,
           int totalRecords,
           java.lang.String reportDate) {
           this.totalAmount = totalAmount;
           this.totalRecords = totalRecords;
           this.reportDate = reportDate;
    }


    /**
     * Gets the totalAmount value for this ReportSaleSumarryData.
     * 
     * @return totalAmount
     */
    public java.math.BigDecimal getTotalAmount() {
        return totalAmount;
    }


    /**
     * Sets the totalAmount value for this ReportSaleSumarryData.
     * 
     * @param totalAmount
     */
    public void setTotalAmount(java.math.BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    /**
     * Gets the totalRecords value for this ReportSaleSumarryData.
     * 
     * @return totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }


    /**
     * Sets the totalRecords value for this ReportSaleSumarryData.
     * 
     * @param totalRecords
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }


    /**
     * Gets the reportDate value for this ReportSaleSumarryData.
     * 
     * @return reportDate
     */
    public java.lang.String getReportDate() {
        return reportDate;
    }


    /**
     * Sets the reportDate value for this ReportSaleSumarryData.
     * 
     * @param reportDate
     */
    public void setReportDate(java.lang.String reportDate) {
        this.reportDate = reportDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportSaleSumarryData)) return false;
        ReportSaleSumarryData other = (ReportSaleSumarryData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.totalAmount==null && other.getTotalAmount()==null) || 
             (this.totalAmount!=null &&
              this.totalAmount.equals(other.getTotalAmount()))) &&
            this.totalRecords == other.getTotalRecords() &&
            ((this.reportDate==null && other.getReportDate()==null) || 
             (this.reportDate!=null &&
              this.reportDate.equals(other.getReportDate())));
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
        if (getTotalAmount() != null) {
            _hashCode += getTotalAmount().hashCode();
        }
        _hashCode += getTotalRecords();
        if (getReportDate() != null) {
            _hashCode += getReportDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportSaleSumarryData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalAmount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalAmount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalRecords");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalRecords"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("reportDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ReportDate"));
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
