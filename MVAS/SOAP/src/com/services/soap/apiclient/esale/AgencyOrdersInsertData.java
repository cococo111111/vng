/**
 * AgencyOrdersInsertData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class AgencyOrdersInsertData  implements java.io.Serializable {
    private com.services.soap.apiclient.esale.CardData[] cardDatas;

    private int returnCode;  // attribute

    private java.lang.String key;  // attribute

    private int numOfCardCurrent;  // attribute

    private int supplierId;  // attribute

    private int supplierCardId;  // attribute

    private int quantity;  // attribute

    private java.math.BigDecimal amount;  // attribute

    public AgencyOrdersInsertData() {
    }

    public AgencyOrdersInsertData(
           com.services.soap.apiclient.esale.CardData[] cardDatas,
           int returnCode,
           java.lang.String key,
           int numOfCardCurrent,
           int supplierId,
           int supplierCardId,
           int quantity,
           java.math.BigDecimal amount) {
           this.cardDatas = cardDatas;
           this.returnCode = returnCode;
           this.key = key;
           this.numOfCardCurrent = numOfCardCurrent;
           this.supplierId = supplierId;
           this.supplierCardId = supplierCardId;
           this.quantity = quantity;
           this.amount = amount;
    }


    /**
     * Gets the cardDatas value for this AgencyOrdersInsertData.
     * 
     * @return cardDatas
     */
    public com.services.soap.apiclient.esale.CardData[] getCardDatas() {
        return cardDatas;
    }


    /**
     * Sets the cardDatas value for this AgencyOrdersInsertData.
     * 
     * @param cardDatas
     */
    public void setCardDatas(com.services.soap.apiclient.esale.CardData[] cardDatas) {
        this.cardDatas = cardDatas;
    }


    /**
     * Gets the returnCode value for this AgencyOrdersInsertData.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this AgencyOrdersInsertData.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the key value for this AgencyOrdersInsertData.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this AgencyOrdersInsertData.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the numOfCardCurrent value for this AgencyOrdersInsertData.
     * 
     * @return numOfCardCurrent
     */
    public int getNumOfCardCurrent() {
        return numOfCardCurrent;
    }


    /**
     * Sets the numOfCardCurrent value for this AgencyOrdersInsertData.
     * 
     * @param numOfCardCurrent
     */
    public void setNumOfCardCurrent(int numOfCardCurrent) {
        this.numOfCardCurrent = numOfCardCurrent;
    }


    /**
     * Gets the supplierId value for this AgencyOrdersInsertData.
     * 
     * @return supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }


    /**
     * Sets the supplierId value for this AgencyOrdersInsertData.
     * 
     * @param supplierId
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    /**
     * Gets the supplierCardId value for this AgencyOrdersInsertData.
     * 
     * @return supplierCardId
     */
    public int getSupplierCardId() {
        return supplierCardId;
    }


    /**
     * Sets the supplierCardId value for this AgencyOrdersInsertData.
     * 
     * @param supplierCardId
     */
    public void setSupplierCardId(int supplierCardId) {
        this.supplierCardId = supplierCardId;
    }


    /**
     * Gets the quantity value for this AgencyOrdersInsertData.
     * 
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this AgencyOrdersInsertData.
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the amount value for this AgencyOrdersInsertData.
     * 
     * @return amount
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this AgencyOrdersInsertData.
     * 
     * @param amount
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgencyOrdersInsertData)) return false;
        AgencyOrdersInsertData other = (AgencyOrdersInsertData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cardDatas==null && other.getCardDatas()==null) || 
             (this.cardDatas!=null &&
              java.util.Arrays.equals(this.cardDatas, other.getCardDatas()))) &&
            this.returnCode == other.getReturnCode() &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            this.numOfCardCurrent == other.getNumOfCardCurrent() &&
            this.supplierId == other.getSupplierId() &&
            this.supplierCardId == other.getSupplierCardId() &&
            this.quantity == other.getQuantity() &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount())));
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
        if (getCardDatas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCardDatas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCardDatas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getReturnCode();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        _hashCode += getNumOfCardCurrent();
        _hashCode += getSupplierId();
        _hashCode += getSupplierCardId();
        _hashCode += getQuantity();
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgencyOrdersInsertData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("returnCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ReturnCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("key");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Key"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("numOfCardCurrent");
        attrField.setXmlName(new javax.xml.namespace.QName("", "NumOfCardCurrent"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("supplierCardId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "SupplierCardId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantity");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("amount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Amount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardDatas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardDatas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardData"));
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
