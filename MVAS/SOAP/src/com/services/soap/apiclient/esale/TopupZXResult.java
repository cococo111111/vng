/**
 * TopupZXResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class TopupZXResult  implements java.io.Serializable {
    private int returnCode;

    private com.services.soap.apiclient.esale.TopupZXData[] topupZXs;

    public TopupZXResult() {
    }

    public TopupZXResult(
           int returnCode,
           com.services.soap.apiclient.esale.TopupZXData[] topupZXs) {
           this.returnCode = returnCode;
           this.topupZXs = topupZXs;
    }


    /**
     * Gets the returnCode value for this TopupZXResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this TopupZXResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the topupZXs value for this TopupZXResult.
     * 
     * @return topupZXs
     */
    public com.services.soap.apiclient.esale.TopupZXData[] getTopupZXs() {
        return topupZXs;
    }


    /**
     * Sets the topupZXs value for this TopupZXResult.
     * 
     * @param topupZXs
     */
    public void setTopupZXs(com.services.soap.apiclient.esale.TopupZXData[] topupZXs) {
        this.topupZXs = topupZXs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TopupZXResult)) return false;
        TopupZXResult other = (TopupZXResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            ((this.topupZXs==null && other.getTopupZXs()==null) || 
             (this.topupZXs!=null &&
              java.util.Arrays.equals(this.topupZXs, other.getTopupZXs())));
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
        if (getTopupZXs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTopupZXs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTopupZXs(), i);
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
        new org.apache.axis.description.TypeDesc(TopupZXResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topupZXs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZX"));
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
