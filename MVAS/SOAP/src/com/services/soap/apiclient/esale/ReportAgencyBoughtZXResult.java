/**
 * ReportAgencyBoughtZXResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportAgencyBoughtZXResult  implements java.io.Serializable {
    private int returnCode;

    private java.lang.String totalOrder;

    private java.lang.String totalQuantity;

    private java.lang.String totalAmount;

    private com.services.soap.apiclient.esale.ReportAgencyBoughtZXData[] reportAgencyBoughtZXs;

    public ReportAgencyBoughtZXResult() {
    }

    public ReportAgencyBoughtZXResult(
           int returnCode,
           java.lang.String totalOrder,
           java.lang.String totalQuantity,
           java.lang.String totalAmount,
           com.services.soap.apiclient.esale.ReportAgencyBoughtZXData[] reportAgencyBoughtZXs) {
           this.returnCode = returnCode;
           this.totalOrder = totalOrder;
           this.totalQuantity = totalQuantity;
           this.totalAmount = totalAmount;
           this.reportAgencyBoughtZXs = reportAgencyBoughtZXs;
    }


    /**
     * Gets the returnCode value for this ReportAgencyBoughtZXResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this ReportAgencyBoughtZXResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the totalOrder value for this ReportAgencyBoughtZXResult.
     * 
     * @return totalOrder
     */
    public java.lang.String getTotalOrder() {
        return totalOrder;
    }


    /**
     * Sets the totalOrder value for this ReportAgencyBoughtZXResult.
     * 
     * @param totalOrder
     */
    public void setTotalOrder(java.lang.String totalOrder) {
        this.totalOrder = totalOrder;
    }


    /**
     * Gets the totalQuantity value for this ReportAgencyBoughtZXResult.
     * 
     * @return totalQuantity
     */
    public java.lang.String getTotalQuantity() {
        return totalQuantity;
    }


    /**
     * Sets the totalQuantity value for this ReportAgencyBoughtZXResult.
     * 
     * @param totalQuantity
     */
    public void setTotalQuantity(java.lang.String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }


    /**
     * Gets the totalAmount value for this ReportAgencyBoughtZXResult.
     * 
     * @return totalAmount
     */
    public java.lang.String getTotalAmount() {
        return totalAmount;
    }


    /**
     * Sets the totalAmount value for this ReportAgencyBoughtZXResult.
     * 
     * @param totalAmount
     */
    public void setTotalAmount(java.lang.String totalAmount) {
        this.totalAmount = totalAmount;
    }


    /**
     * Gets the reportAgencyBoughtZXs value for this ReportAgencyBoughtZXResult.
     * 
     * @return reportAgencyBoughtZXs
     */
    public com.services.soap.apiclient.esale.ReportAgencyBoughtZXData[] getReportAgencyBoughtZXs() {
        return reportAgencyBoughtZXs;
    }


    /**
     * Sets the reportAgencyBoughtZXs value for this ReportAgencyBoughtZXResult.
     * 
     * @param reportAgencyBoughtZXs
     */
    public void setReportAgencyBoughtZXs(com.services.soap.apiclient.esale.ReportAgencyBoughtZXData[] reportAgencyBoughtZXs) {
        this.reportAgencyBoughtZXs = reportAgencyBoughtZXs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportAgencyBoughtZXResult)) return false;
        ReportAgencyBoughtZXResult other = (ReportAgencyBoughtZXResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            ((this.totalOrder==null && other.getTotalOrder()==null) || 
             (this.totalOrder!=null &&
              this.totalOrder.equals(other.getTotalOrder()))) &&
            ((this.totalQuantity==null && other.getTotalQuantity()==null) || 
             (this.totalQuantity!=null &&
              this.totalQuantity.equals(other.getTotalQuantity()))) &&
            ((this.totalAmount==null && other.getTotalAmount()==null) || 
             (this.totalAmount!=null &&
              this.totalAmount.equals(other.getTotalAmount()))) &&
            ((this.reportAgencyBoughtZXs==null && other.getReportAgencyBoughtZXs()==null) || 
             (this.reportAgencyBoughtZXs!=null &&
              java.util.Arrays.equals(this.reportAgencyBoughtZXs, other.getReportAgencyBoughtZXs())));
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
        if (getTotalOrder() != null) {
            _hashCode += getTotalOrder().hashCode();
        }
        if (getTotalQuantity() != null) {
            _hashCode += getTotalQuantity().hashCode();
        }
        if (getTotalAmount() != null) {
            _hashCode += getTotalAmount().hashCode();
        }
        if (getReportAgencyBoughtZXs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportAgencyBoughtZXs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportAgencyBoughtZXs(), i);
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
        new org.apache.axis.description.TypeDesc(ReportAgencyBoughtZXResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TotalOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TotalQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TotalAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportAgencyBoughtZXs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZX"));
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
