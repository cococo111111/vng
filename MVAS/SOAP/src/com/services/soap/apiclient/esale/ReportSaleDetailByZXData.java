/**
 * ReportSaleDetailByZXData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportSaleDetailByZXData  implements java.io.Serializable {
    private java.lang.String transactionDate;  // attribute

    private java.lang.String transactionNo;  // attribute

    private int quantity;  // attribute

    private java.math.BigDecimal unitPrice;  // attribute

    private java.math.BigDecimal totalAmount;  // attribute

    private java.lang.String customerCode;  // attribute

    private java.lang.String seller;  // attribute

    private int totalRecords;  // attribute

    public ReportSaleDetailByZXData() {
    }

    public ReportSaleDetailByZXData(
           java.lang.String transactionDate,
           java.lang.String transactionNo,
           int quantity,
           java.math.BigDecimal unitPrice,
           java.math.BigDecimal totalAmount,
           java.lang.String customerCode,
           java.lang.String seller,
           int totalRecords) {
           this.transactionDate = transactionDate;
           this.transactionNo = transactionNo;
           this.quantity = quantity;
           this.unitPrice = unitPrice;
           this.totalAmount = totalAmount;
           this.customerCode = customerCode;
           this.seller = seller;
           this.totalRecords = totalRecords;
    }


    /**
     * Gets the transactionDate value for this ReportSaleDetailByZXData.
     * 
     * @return transactionDate
     */
    public java.lang.String getTransactionDate() {
        return transactionDate;
    }


    /**
     * Sets the transactionDate value for this ReportSaleDetailByZXData.
     * 
     * @param transactionDate
     */
    public void setTransactionDate(java.lang.String transactionDate) {
        this.transactionDate = transactionDate;
    }


    /**
     * Gets the transactionNo value for this ReportSaleDetailByZXData.
     * 
     * @return transactionNo
     */
    public java.lang.String getTransactionNo() {
        return transactionNo;
    }


    /**
     * Sets the transactionNo value for this ReportSaleDetailByZXData.
     * 
     * @param transactionNo
     */
    public void setTransactionNo(java.lang.String transactionNo) {
        this.transactionNo = transactionNo;
    }


    /**
     * Gets the quantity value for this ReportSaleDetailByZXData.
     * 
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this ReportSaleDetailByZXData.
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the unitPrice value for this ReportSaleDetailByZXData.
     * 
     * @return unitPrice
     */
    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }


    /**
     * Sets the unitPrice value for this ReportSaleDetailByZXData.
     * 
     * @param unitPrice
     */
    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    /**
     * Gets the totalAmount value for this ReportSaleDetailByZXData.
     * 
     * @return totalAmount
     */
    public java.math.BigDecimal getTotalAmount() {
        return totalAmount;
    }


    /**
     * Sets the totalAmount value for this ReportSaleDetailByZXData.
     * 
     * @param totalAmount
     */
    public void setTotalAmount(java.math.BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    /**
     * Gets the customerCode value for this ReportSaleDetailByZXData.
     * 
     * @return customerCode
     */
    public java.lang.String getCustomerCode() {
        return customerCode;
    }


    /**
     * Sets the customerCode value for this ReportSaleDetailByZXData.
     * 
     * @param customerCode
     */
    public void setCustomerCode(java.lang.String customerCode) {
        this.customerCode = customerCode;
    }


    /**
     * Gets the seller value for this ReportSaleDetailByZXData.
     * 
     * @return seller
     */
    public java.lang.String getSeller() {
        return seller;
    }


    /**
     * Sets the seller value for this ReportSaleDetailByZXData.
     * 
     * @param seller
     */
    public void setSeller(java.lang.String seller) {
        this.seller = seller;
    }


    /**
     * Gets the totalRecords value for this ReportSaleDetailByZXData.
     * 
     * @return totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }


    /**
     * Sets the totalRecords value for this ReportSaleDetailByZXData.
     * 
     * @param totalRecords
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportSaleDetailByZXData)) return false;
        ReportSaleDetailByZXData other = (ReportSaleDetailByZXData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transactionDate==null && other.getTransactionDate()==null) || 
             (this.transactionDate!=null &&
              this.transactionDate.equals(other.getTransactionDate()))) &&
            ((this.transactionNo==null && other.getTransactionNo()==null) || 
             (this.transactionNo!=null &&
              this.transactionNo.equals(other.getTransactionNo()))) &&
            this.quantity == other.getQuantity() &&
            ((this.unitPrice==null && other.getUnitPrice()==null) || 
             (this.unitPrice!=null &&
              this.unitPrice.equals(other.getUnitPrice()))) &&
            ((this.totalAmount==null && other.getTotalAmount()==null) || 
             (this.totalAmount!=null &&
              this.totalAmount.equals(other.getTotalAmount()))) &&
            ((this.customerCode==null && other.getCustomerCode()==null) || 
             (this.customerCode!=null &&
              this.customerCode.equals(other.getCustomerCode()))) &&
            ((this.seller==null && other.getSeller()==null) || 
             (this.seller!=null &&
              this.seller.equals(other.getSeller()))) &&
            this.totalRecords == other.getTotalRecords();
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
        if (getTransactionDate() != null) {
            _hashCode += getTransactionDate().hashCode();
        }
        if (getTransactionNo() != null) {
            _hashCode += getTransactionNo().hashCode();
        }
        _hashCode += getQuantity();
        if (getUnitPrice() != null) {
            _hashCode += getUnitPrice().hashCode();
        }
        if (getTotalAmount() != null) {
            _hashCode += getTotalAmount().hashCode();
        }
        if (getCustomerCode() != null) {
            _hashCode += getCustomerCode().hashCode();
        }
        if (getSeller() != null) {
            _hashCode += getSeller().hashCode();
        }
        _hashCode += getTotalRecords();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportSaleDetailByZXData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transactionDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TransactionDate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transactionNo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TransactionNo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantity");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("unitPrice");
        attrField.setXmlName(new javax.xml.namespace.QName("", "UnitPrice"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalAmount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalAmount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("customerCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CustomerCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("seller");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Seller"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalRecords");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalRecords"));
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
