/**
 * CheckQuantitySupplierCardResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class CheckQuantitySupplierCardResult  implements java.io.Serializable {
    private int returnCode;

    private com.services.soap.apiclient.esale.CheckQuantityData[] checkQuantitys;

    public CheckQuantitySupplierCardResult() {
    }

    public CheckQuantitySupplierCardResult(
           int returnCode,
           com.services.soap.apiclient.esale.CheckQuantityData[] checkQuantitys) {
           this.returnCode = returnCode;
           this.checkQuantitys = checkQuantitys;
    }


    /**
     * Gets the returnCode value for this CheckQuantitySupplierCardResult.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this CheckQuantitySupplierCardResult.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the checkQuantitys value for this CheckQuantitySupplierCardResult.
     * 
     * @return checkQuantitys
     */
    public com.services.soap.apiclient.esale.CheckQuantityData[] getCheckQuantitys() {
        return checkQuantitys;
    }


    /**
     * Sets the checkQuantitys value for this CheckQuantitySupplierCardResult.
     * 
     * @param checkQuantitys
     */
    public void setCheckQuantitys(com.services.soap.apiclient.esale.CheckQuantityData[] checkQuantitys) {
        this.checkQuantitys = checkQuantitys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckQuantitySupplierCardResult)) return false;
        CheckQuantitySupplierCardResult other = (CheckQuantitySupplierCardResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.returnCode == other.getReturnCode() &&
            ((this.checkQuantitys==null && other.getCheckQuantitys()==null) || 
             (this.checkQuantitys!=null &&
              java.util.Arrays.equals(this.checkQuantitys, other.getCheckQuantitys())));
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
        if (getCheckQuantitys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCheckQuantitys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCheckQuantitys(), i);
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
        new org.apache.axis.description.TypeDesc(CheckQuantitySupplierCardResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantitySupplierCardResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkQuantitys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantitys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantityData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantity"));
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
