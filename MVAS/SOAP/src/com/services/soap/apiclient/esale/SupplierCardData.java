/**
 * SupplierCardData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class SupplierCardData  implements java.io.Serializable {
    private int supplierId;

    private int supplierCardId;

    private java.lang.String prefixCode;

    private java.lang.String supplierName;

    private java.lang.String logo;

    private int returnCode;

    private java.math.BigDecimal price;

    private java.math.BigDecimal priceDiscount;

    private java.math.BigDecimal discount;

    public SupplierCardData() {
    }

    public SupplierCardData(
           int supplierId,
           int supplierCardId,
           java.lang.String prefixCode,
           java.lang.String supplierName,
           java.lang.String logo,
           int returnCode,
           java.math.BigDecimal price,
           java.math.BigDecimal priceDiscount,
           java.math.BigDecimal discount) {
           this.supplierId = supplierId;
           this.supplierCardId = supplierCardId;
           this.prefixCode = prefixCode;
           this.supplierName = supplierName;
           this.logo = logo;
           this.returnCode = returnCode;
           this.price = price;
           this.priceDiscount = priceDiscount;
           this.discount = discount;
    }


    /**
     * Gets the supplierId value for this SupplierCardData.
     * 
     * @return supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }


    /**
     * Sets the supplierId value for this SupplierCardData.
     * 
     * @param supplierId
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    /**
     * Gets the supplierCardId value for this SupplierCardData.
     * 
     * @return supplierCardId
     */
    public int getSupplierCardId() {
        return supplierCardId;
    }


    /**
     * Sets the supplierCardId value for this SupplierCardData.
     * 
     * @param supplierCardId
     */
    public void setSupplierCardId(int supplierCardId) {
        this.supplierCardId = supplierCardId;
    }


    /**
     * Gets the prefixCode value for this SupplierCardData.
     * 
     * @return prefixCode
     */
    public java.lang.String getPrefixCode() {
        return prefixCode;
    }


    /**
     * Sets the prefixCode value for this SupplierCardData.
     * 
     * @param prefixCode
     */
    public void setPrefixCode(java.lang.String prefixCode) {
        this.prefixCode = prefixCode;
    }


    /**
     * Gets the supplierName value for this SupplierCardData.
     * 
     * @return supplierName
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }


    /**
     * Sets the supplierName value for this SupplierCardData.
     * 
     * @param supplierName
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }


    /**
     * Gets the logo value for this SupplierCardData.
     * 
     * @return logo
     */
    public java.lang.String getLogo() {
        return logo;
    }


    /**
     * Sets the logo value for this SupplierCardData.
     * 
     * @param logo
     */
    public void setLogo(java.lang.String logo) {
        this.logo = logo;
    }


    /**
     * Gets the returnCode value for this SupplierCardData.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this SupplierCardData.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the price value for this SupplierCardData.
     * 
     * @return price
     */
    public java.math.BigDecimal getPrice() {
        return price;
    }


    /**
     * Sets the price value for this SupplierCardData.
     * 
     * @param price
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }


    /**
     * Gets the priceDiscount value for this SupplierCardData.
     * 
     * @return priceDiscount
     */
    public java.math.BigDecimal getPriceDiscount() {
        return priceDiscount;
    }


    /**
     * Sets the priceDiscount value for this SupplierCardData.
     * 
     * @param priceDiscount
     */
    public void setPriceDiscount(java.math.BigDecimal priceDiscount) {
        this.priceDiscount = priceDiscount;
    }


    /**
     * Gets the discount value for this SupplierCardData.
     * 
     * @return discount
     */
    public java.math.BigDecimal getDiscount() {
        return discount;
    }


    /**
     * Sets the discount value for this SupplierCardData.
     * 
     * @param discount
     */
    public void setDiscount(java.math.BigDecimal discount) {
        this.discount = discount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SupplierCardData)) return false;
        SupplierCardData other = (SupplierCardData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.supplierId == other.getSupplierId() &&
            this.supplierCardId == other.getSupplierCardId() &&
            ((this.prefixCode==null && other.getPrefixCode()==null) || 
             (this.prefixCode!=null &&
              this.prefixCode.equals(other.getPrefixCode()))) &&
            ((this.supplierName==null && other.getSupplierName()==null) || 
             (this.supplierName!=null &&
              this.supplierName.equals(other.getSupplierName()))) &&
            ((this.logo==null && other.getLogo()==null) || 
             (this.logo!=null &&
              this.logo.equals(other.getLogo()))) &&
            this.returnCode == other.getReturnCode() &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.priceDiscount==null && other.getPriceDiscount()==null) || 
             (this.priceDiscount!=null &&
              this.priceDiscount.equals(other.getPriceDiscount()))) &&
            ((this.discount==null && other.getDiscount()==null) || 
             (this.discount!=null &&
              this.discount.equals(other.getDiscount())));
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
        _hashCode += getSupplierId();
        _hashCode += getSupplierCardId();
        if (getPrefixCode() != null) {
            _hashCode += getPrefixCode().hashCode();
        }
        if (getSupplierName() != null) {
            _hashCode += getSupplierName().hashCode();
        }
        if (getLogo() != null) {
            _hashCode += getLogo().hashCode();
        }
        _hashCode += getReturnCode();
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getPriceDiscount() != null) {
            _hashCode += getPriceDiscount().hashCode();
        }
        if (getDiscount() != null) {
            _hashCode += getDiscount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SupplierCardData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supplierId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supplierCardId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefixCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "PrefixCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supplierName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Logo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceDiscount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "PriceDiscount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
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
