/**
 * AgencyOrdersInsertResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class AgencyOrdersInsertResult  implements java.io.Serializable {
    private int returnCode;

    private AgencyOrdersInsertData[] agencyOrdersInserts;

    public AgencyOrdersInsertResult() {
    }

    public AgencyOrdersInsertResult(
           int returnCode,
           AgencyOrdersInsertData[] agencyOrdersInserts) {
           this.returnCode = returnCode;
           this.agencyOrdersInserts = agencyOrdersInserts;
    }


    /**
     * Gets the returnCode value for this AgencyOrdersInsertResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this AgencyOrdersInsertResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the agencyOrdersInserts value for this AgencyOrdersInsertResult.
     * 
     * @return agencyOrdersInserts
     */
    public AgencyOrdersInsertData[] getAgencyOrdersInserts() {
        return agencyOrdersInserts;
    }


    /**
     * Sets the agencyOrdersInserts value for this AgencyOrdersInsertResult.
     * 
     * @param agencyOrdersInserts
     */
    public void setAgencyOrdersInserts(AgencyOrdersInsertData[] agencyOrdersInserts) {
        this.agencyOrdersInserts = agencyOrdersInserts;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgencyOrdersInsertResult)) return false;
        AgencyOrdersInsertResult other = (AgencyOrdersInsertResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            ((this.agencyOrdersInserts==null && other.getAgencyOrdersInserts()==null) || 
             (this.agencyOrdersInserts!=null &&
              java.util.Arrays.equals(this.agencyOrdersInserts, other.getAgencyOrdersInserts())));
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
        if (getAgencyOrdersInserts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAgencyOrdersInserts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAgencyOrdersInserts(), i);
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
        new org.apache.axis.description.TypeDesc(AgencyOrdersInsertResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agencyOrdersInserts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInserts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsert"));
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
