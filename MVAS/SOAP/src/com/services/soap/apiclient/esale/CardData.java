/**
 * CardData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class CardData  implements java.io.Serializable {
    private java.lang.String orderNo;  // attribute

    private int cardId;  // attribute

    private java.lang.String serial;  // attribute

    private java.lang.String cardCode;  // attribute

    private java.lang.String expiredDate;  // attribute

    public CardData() {
    }

    public CardData(
           java.lang.String orderNo,
           int cardId,
           java.lang.String serial,
           java.lang.String cardCode,
           java.lang.String expiredDate) {
           this.orderNo = orderNo;
           this.cardId = cardId;
           this.serial = serial;
           this.cardCode = cardCode;
           this.expiredDate = expiredDate;
    }


    /**
     * Gets the orderNo value for this CardData.
     * 
     * @return orderNo
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }


    /**
     * Sets the orderNo value for this CardData.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Gets the cardId value for this CardData.
     * 
     * @return cardId
     */
    public int getCardId() {
        return cardId;
    }


    /**
     * Sets the cardId value for this CardData.
     * 
     * @param cardId
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }


    /**
     * Gets the serial value for this CardData.
     * 
     * @return serial
     */
    public java.lang.String getSerial() {
        return serial;
    }


    /**
     * Sets the serial value for this CardData.
     * 
     * @param serial
     */
    public void setSerial(java.lang.String serial) {
        this.serial = serial;
    }


    /**
     * Gets the cardCode value for this CardData.
     * 
     * @return cardCode
     */
    public java.lang.String getCardCode() {
        return cardCode;
    }


    /**
     * Sets the cardCode value for this CardData.
     * 
     * @param cardCode
     */
    public void setCardCode(java.lang.String cardCode) {
        this.cardCode = cardCode;
    }


    /**
     * Gets the expiredDate value for this CardData.
     * 
     * @return expiredDate
     */
    public java.lang.String getExpiredDate() {
        return expiredDate;
    }


    /**
     * Sets the expiredDate value for this CardData.
     * 
     * @param expiredDate
     */
    public void setExpiredDate(java.lang.String expiredDate) {
        this.expiredDate = expiredDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CardData)) return false;
        CardData other = (CardData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.orderNo==null && other.getOrderNo()==null) || 
             (this.orderNo!=null &&
              this.orderNo.equals(other.getOrderNo()))) &&
            this.cardId == other.getCardId() &&
            ((this.serial==null && other.getSerial()==null) || 
             (this.serial!=null &&
              this.serial.equals(other.getSerial()))) &&
            ((this.cardCode==null && other.getCardCode()==null) || 
             (this.cardCode!=null &&
              this.cardCode.equals(other.getCardCode()))) &&
            ((this.expiredDate==null && other.getExpiredDate()==null) || 
             (this.expiredDate!=null &&
              this.expiredDate.equals(other.getExpiredDate())));
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
        if (getOrderNo() != null) {
            _hashCode += getOrderNo().hashCode();
        }
        _hashCode += getCardId();
        if (getSerial() != null) {
            _hashCode += getSerial().hashCode();
        }
        if (getCardCode() != null) {
            _hashCode += getCardCode().hashCode();
        }
        if (getExpiredDate() != null) {
            _hashCode += getExpiredDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CardData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("orderNo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OrderNo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("cardId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CardId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("serial");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Serial"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("cardCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CardCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("expiredDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ExpiredDate"));
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
