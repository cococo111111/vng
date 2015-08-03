/**
 * ReportSaleDetailByZXResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportSaleDetailByZXResult  implements java.io.Serializable {
    private int returnCode;

    private java.math.BigDecimal sumTotalAmount;

    private com.services.soap.apiclient.esale.ReportSaleDetailByZXData[] reportSaleDetailByZXs;

    public ReportSaleDetailByZXResult() {
    }

    public ReportSaleDetailByZXResult(
           int returnCode,
           java.math.BigDecimal sumTotalAmount,
           com.services.soap.apiclient.esale.ReportSaleDetailByZXData[] reportSaleDetailByZXs) {
           this.returnCode = returnCode;
           this.sumTotalAmount = sumTotalAmount;
           this.reportSaleDetailByZXs = reportSaleDetailByZXs;
    }


    /**
     * Gets the returnCode value for this ReportSaleDetailByZXResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this ReportSaleDetailByZXResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the sumTotalAmount value for this ReportSaleDetailByZXResult.
     * 
     * @return sumTotalAmount
     */
    public java.math.BigDecimal getSumTotalAmount() {
        return sumTotalAmount;
    }


    /**
     * Sets the sumTotalAmount value for this ReportSaleDetailByZXResult.
     * 
     * @param sumTotalAmount
     */
    public void setSumTotalAmount(java.math.BigDecimal sumTotalAmount) {
        this.sumTotalAmount = sumTotalAmount;
    }


    /**
     * Gets the reportSaleDetailByZXs value for this ReportSaleDetailByZXResult.
     * 
     * @return reportSaleDetailByZXs
     */
    public com.services.soap.apiclient.esale.ReportSaleDetailByZXData[] getReportSaleDetailByZXs() {
        return reportSaleDetailByZXs;
    }


    /**
     * Sets the reportSaleDetailByZXs value for this ReportSaleDetailByZXResult.
     * 
     * @param reportSaleDetailByZXs
     */
    public void setReportSaleDetailByZXs(com.services.soap.apiclient.esale.ReportSaleDetailByZXData[] reportSaleDetailByZXs) {
        this.reportSaleDetailByZXs = reportSaleDetailByZXs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportSaleDetailByZXResult)) return false;
        ReportSaleDetailByZXResult other = (ReportSaleDetailByZXResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            ((this.sumTotalAmount==null && other.getSumTotalAmount()==null) || 
             (this.sumTotalAmount!=null &&
              this.sumTotalAmount.equals(other.getSumTotalAmount()))) &&
            ((this.reportSaleDetailByZXs==null && other.getReportSaleDetailByZXs()==null) || 
             (this.reportSaleDetailByZXs!=null &&
              java.util.Arrays.equals(this.reportSaleDetailByZXs, other.getReportSaleDetailByZXs())));
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
        if (getSumTotalAmount() != null) {
            _hashCode += getSumTotalAmount().hashCode();
        }
        if (getReportSaleDetailByZXs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportSaleDetailByZXs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportSaleDetailByZXs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportSaleDetailByZXResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sumTotalAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SumTotalAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportSaleDetailByZXs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZX"));
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
