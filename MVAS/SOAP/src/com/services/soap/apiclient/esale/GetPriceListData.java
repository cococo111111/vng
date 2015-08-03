/**
 * GetPriceListData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class GetPriceListData  implements java.io.Serializable {
    private long quantityFrom;  // attribute

    private long quantityTo;  // attribute

    private java.math.BigDecimal unitPrice;  // attribute

    private java.lang.String effectiveFrom;  // attribute

    private java.lang.String effectiveTo;  // attribute

    private java.lang.String priceListCode;  // attribute

    private java.lang.String priceListDesc;  // attribute

    private int priceListType;  // attribute

    public GetPriceListData() {
    }

    public GetPriceListData(
           long quantityFrom,
           long quantityTo,
           java.math.BigDecimal unitPrice,
           java.lang.String effectiveFrom,
           java.lang.String effectiveTo,
           java.lang.String priceListCode,
           java.lang.String priceListDesc,
           int priceListType) {
           this.quantityFrom = quantityFrom;
           this.quantityTo = quantityTo;
           this.unitPrice = unitPrice;
           this.effectiveFrom = effectiveFrom;
           this.effectiveTo = effectiveTo;
           this.priceListCode = priceListCode;
           this.priceListDesc = priceListDesc;
           this.priceListType = priceListType;
    }


    /**
     * Gets the quantityFrom value for this GetPriceListData.
     * 
     * @return quantityFrom
     */
    public long getQuantityFrom() {
        return quantityFrom;
    }


    /**
     * Sets the quantityFrom value for this GetPriceListData.
     * 
     * @param quantityFrom
     */
    public void setQuantityFrom(long quantityFrom) {
        this.quantityFrom = quantityFrom;
    }


    /**
     * Gets the quantityTo value for this GetPriceListData.
     * 
     * @return quantityTo
     */
    public long getQuantityTo() {
        return quantityTo;
    }


    /**
     * Sets the quantityTo value for this GetPriceListData.
     * 
     * @param quantityTo
     */
    public void setQuantityTo(long quantityTo) {
        this.quantityTo = quantityTo;
    }


    /**
     * Gets the unitPrice value for this GetPriceListData.
     * 
     * @return unitPrice
     */
    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }


    /**
     * Sets the unitPrice value for this GetPriceListData.
     * 
     * @param unitPrice
     */
    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    /**
     * Gets the effectiveFrom value for this GetPriceListData.
     * 
     * @return effectiveFrom
     */
    public java.lang.String getEffectiveFrom() {
        return effectiveFrom;
    }


    /**
     * Sets the effectiveFrom value for this GetPriceListData.
     * 
     * @param effectiveFrom
     */
    public void setEffectiveFrom(java.lang.String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }


    /**
     * Gets the effectiveTo value for this GetPriceListData.
     * 
     * @return effectiveTo
     */
    public java.lang.String getEffectiveTo() {
        return effectiveTo;
    }


    /**
     * Sets the effectiveTo value for this GetPriceListData.
     * 
     * @param effectiveTo
     */
    public void setEffectiveTo(java.lang.String effectiveTo) {
        this.effectiveTo = effectiveTo;
    }


    /**
     * Gets the priceListCode value for this GetPriceListData.
     * 
     * @return priceListCode
     */
    public java.lang.String getPriceListCode() {
        return priceListCode;
    }


    /**
     * Sets the priceListCode value for this GetPriceListData.
     * 
     * @param priceListCode
     */
    public void setPriceListCode(java.lang.String priceListCode) {
        this.priceListCode = priceListCode;
    }


    /**
     * Gets the priceListDesc value for this GetPriceListData.
     * 
     * @return priceListDesc
     */
    public java.lang.String getPriceListDesc() {
        return priceListDesc;
    }


    /**
     * Sets the priceListDesc value for this GetPriceListData.
     * 
     * @param priceListDesc
     */
    public void setPriceListDesc(java.lang.String priceListDesc) {
        this.priceListDesc = priceListDesc;
    }


    /**
     * Gets the priceListType value for this GetPriceListData.
     * 
     * @return priceListType
     */
    public int getPriceListType() {
        return priceListType;
    }


    /**
     * Sets the priceListType value for this GetPriceListData.
     * 
     * @param priceListType
     */
    public void setPriceListType(int priceListType) {
        this.priceListType = priceListType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPriceListData)) return false;
        GetPriceListData other = (GetPriceListData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.quantityFrom == other.getQuantityFrom() &&
            this.quantityTo == other.getQuantityTo() &&
            ((this.unitPrice==null && other.getUnitPrice()==null) || 
             (this.unitPrice!=null &&
              this.unitPrice.equals(other.getUnitPrice()))) &&
            ((this.effectiveFrom==null && other.getEffectiveFrom()==null) || 
             (this.effectiveFrom!=null &&
              this.effectiveFrom.equals(other.getEffectiveFrom()))) &&
            ((this.effectiveTo==null && other.getEffectiveTo()==null) || 
             (this.effectiveTo!=null &&
              this.effectiveTo.equals(other.getEffectiveTo()))) &&
            ((this.priceListCode==null && other.getPriceListCode()==null) || 
             (this.priceListCode!=null &&
              this.priceListCode.equals(other.getPriceListCode()))) &&
            ((this.priceListDesc==null && other.getPriceListDesc()==null) || 
             (this.priceListDesc!=null &&
              this.priceListDesc.equals(other.getPriceListDesc()))) &&
            this.priceListType == other.getPriceListType();
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
        _hashCode += new Long(getQuantityFrom()).hashCode();
        _hashCode += new Long(getQuantityTo()).hashCode();
        if (getUnitPrice() != null) {
            _hashCode += getUnitPrice().hashCode();
        }
        if (getEffectiveFrom() != null) {
            _hashCode += getEffectiveFrom().hashCode();
        }
        if (getEffectiveTo() != null) {
            _hashCode += getEffectiveTo().hashCode();
        }
        if (getPriceListCode() != null) {
            _hashCode += getPriceListCode().hashCode();
        }
        if (getPriceListDesc() != null) {
            _hashCode += getPriceListDesc().hashCode();
        }
        _hashCode += getPriceListType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPriceListData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceListData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantityFrom");
        attrField.setXmlName(new javax.xml.namespace.QName("", "QuantityFrom"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantityTo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "QuantityTo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("unitPrice");
        attrField.setXmlName(new javax.xml.namespace.QName("", "UnitPrice"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("effectiveFrom");
        attrField.setXmlName(new javax.xml.namespace.QName("", "EffectiveFrom"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("effectiveTo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "EffectiveTo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("priceListCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PriceListCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("priceListDesc");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PriceListDesc"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("priceListType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "PriceListType"));
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
