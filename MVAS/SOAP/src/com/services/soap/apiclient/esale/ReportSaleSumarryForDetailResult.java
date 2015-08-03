/**
 * ReportSaleSumarryForDetailResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportSaleSumarryForDetailResult  implements java.io.Serializable {
    private int returnCode;

    private com.services.soap.apiclient.esale.ReportSaleSumarryForDetailData[] reportSaleSumarryForDetails;

    public ReportSaleSumarryForDetailResult() {
    }

    public ReportSaleSumarryForDetailResult(
           int returnCode,
           com.services.soap.apiclient.esale.ReportSaleSumarryForDetailData[] reportSaleSumarryForDetails) {
           this.returnCode = returnCode;
           this.reportSaleSumarryForDetails = reportSaleSumarryForDetails;
    }


    /**
     * Gets the returnCode value for this ReportSaleSumarryForDetailResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this ReportSaleSumarryForDetailResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the reportSaleSumarryForDetails value for this ReportSaleSumarryForDetailResult.
     * 
     * @return reportSaleSumarryForDetails
     */
    public com.services.soap.apiclient.esale.ReportSaleSumarryForDetailData[] getReportSaleSumarryForDetails() {
        return reportSaleSumarryForDetails;
    }


    /**
     * Sets the reportSaleSumarryForDetails value for this ReportSaleSumarryForDetailResult.
     * 
     * @param reportSaleSumarryForDetails
     */
    public void setReportSaleSumarryForDetails(com.services.soap.apiclient.esale.ReportSaleSumarryForDetailData[] reportSaleSumarryForDetails) {
        this.reportSaleSumarryForDetails = reportSaleSumarryForDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportSaleSumarryForDetailResult)) return false;
        ReportSaleSumarryForDetailResult other = (ReportSaleSumarryForDetailResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            ((this.reportSaleSumarryForDetails==null && other.getReportSaleSumarryForDetails()==null) || 
             (this.reportSaleSumarryForDetails!=null &&
              java.util.Arrays.equals(this.reportSaleSumarryForDetails, other.getReportSaleSumarryForDetails())));
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
        if (getReportSaleSumarryForDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportSaleSumarryForDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportSaleSumarryForDetails(), i);
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
        new org.apache.axis.description.TypeDesc(ReportSaleSumarryForDetailResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportSaleSumarryForDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetail"));
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
