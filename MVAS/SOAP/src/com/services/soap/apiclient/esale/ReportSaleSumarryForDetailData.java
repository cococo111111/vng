/**
 * ReportSaleSumarryForDetailData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportSaleSumarryForDetailData  implements java.io.Serializable {
    private java.lang.String seller;  // attribute

    private java.math.BigDecimal totalAmount;  // attribute

    private int totalRecords;  // attribute

    private int quantity;  // attribute

    private java.math.BigDecimal totalMoney;  // attribute

    private java.lang.String transactionNo;  // attribute

    private java.lang.String accountName;  // attribute

    private java.lang.String transctionType;  // attribute

    private java.lang.String transactionDate;  // attribute

    public ReportSaleSumarryForDetailData() {
    }

    public ReportSaleSumarryForDetailData(
           java.lang.String seller,
           java.math.BigDecimal totalAmount,
           int totalRecords,
           int quantity,
           java.math.BigDecimal totalMoney,
           java.lang.String transactionNo,
           java.lang.String accountName,
           java.lang.String transctionType,
           java.lang.String transactionDate) {
           this.seller = seller;
           this.totalAmount = totalAmount;
           this.totalRecords = totalRecords;
           this.quantity = quantity;
           this.totalMoney = totalMoney;
           this.transactionNo = transactionNo;
           this.accountName = accountName;
           this.transctionType = transctionType;
           this.transactionDate = transactionDate;
    }


    /**
     * Gets the seller value for this ReportSaleSumarryForDetailData.
     * 
     * @return seller
     */
    public java.lang.String getSeller() {
        return seller;
    }


    /**
     * Sets the seller value for this ReportSaleSumarryForDetailData.
     * 
     * @param seller
     */
    public void setSeller(java.lang.String seller) {
        this.seller = seller;
    }


    /**
     * Gets the totalAmount value for this ReportSaleSumarryForDetailData.
     * 
     * @return totalAmount
     */
    public java.math.BigDecimal getTotalAmount() {
        return totalAmount;
    }


    /**
     * Sets the totalAmount value for this ReportSaleSumarryForDetailData.
     * 
     * @param totalAmount
     */
    public void setTotalAmount(java.math.BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    /**
     * Gets the totalRecords value for this ReportSaleSumarryForDetailData.
     * 
     * @return totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }


    /**
     * Sets the totalRecords value for this ReportSaleSumarryForDetailData.
     * 
     * @param totalRecords
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }


    /**
     * Gets the quantity value for this ReportSaleSumarryForDetailData.
     * 
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this ReportSaleSumarryForDetailData.
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the totalMoney value for this ReportSaleSumarryForDetailData.
     * 
     * @return totalMoney
     */
    public java.math.BigDecimal getTotalMoney() {
        return totalMoney;
    }


    /**
     * Sets the totalMoney value for this ReportSaleSumarryForDetailData.
     * 
     * @param totalMoney
     */
    public void setTotalMoney(java.math.BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }


    /**
     * Gets the transactionNo value for this ReportSaleSumarryForDetailData.
     * 
     * @return transactionNo
     */
    public java.lang.String getTransactionNo() {
        return transactionNo;
    }


    /**
     * Sets the transactionNo value for this ReportSaleSumarryForDetailData.
     * 
     * @param transactionNo
     */
    public void setTransactionNo(java.lang.String transactionNo) {
        this.transactionNo = transactionNo;
    }


    /**
     * Gets the accountName value for this ReportSaleSumarryForDetailData.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this ReportSaleSumarryForDetailData.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the transctionType value for this ReportSaleSumarryForDetailData.
     * 
     * @return transctionType
     */
    public java.lang.String getTransctionType() {
        return transctionType;
    }


    /**
     * Sets the transctionType value for this ReportSaleSumarryForDetailData.
     * 
     * @param transctionType
     */
    public void setTransctionType(java.lang.String transctionType) {
        this.transctionType = transctionType;
    }


    /**
     * Gets the transactionDate value for this ReportSaleSumarryForDetailData.
     * 
     * @return transactionDate
     */
    public java.lang.String getTransactionDate() {
        return transactionDate;
    }


    /**
     * Sets the transactionDate value for this ReportSaleSumarryForDetailData.
     * 
     * @param transactionDate
     */
    public void setTransactionDate(java.lang.String transactionDate) {
        this.transactionDate = transactionDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportSaleSumarryForDetailData)) return false;
        ReportSaleSumarryForDetailData other = (ReportSaleSumarryForDetailData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.seller==null && other.getSeller()==null) || 
             (this.seller!=null &&
              this.seller.equals(other.getSeller()))) &&
            ((this.totalAmount==null && other.getTotalAmount()==null) || 
             (this.totalAmount!=null &&
              this.totalAmount.equals(other.getTotalAmount()))) &&
            this.totalRecords == other.getTotalRecords() &&
            this.quantity == other.getQuantity() &&
            ((this.totalMoney==null && other.getTotalMoney()==null) || 
             (this.totalMoney!=null &&
              this.totalMoney.equals(other.getTotalMoney()))) &&
            ((this.transactionNo==null && other.getTransactionNo()==null) || 
             (this.transactionNo!=null &&
              this.transactionNo.equals(other.getTransactionNo()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.transctionType==null && other.getTransctionType()==null) || 
             (this.transctionType!=null &&
              this.transctionType.equals(other.getTransctionType()))) &&
            ((this.transactionDate==null && other.getTransactionDate()==null) || 
             (this.transactionDate!=null &&
              this.transactionDate.equals(other.getTransactionDate())));
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
        if (getSeller() != null) {
            _hashCode += getSeller().hashCode();
        }
        if (getTotalAmount() != null) {
            _hashCode += getTotalAmount().hashCode();
        }
        _hashCode += getTotalRecords();
        _hashCode += getQuantity();
        if (getTotalMoney() != null) {
            _hashCode += getTotalMoney().hashCode();
        }
        if (getTransactionNo() != null) {
            _hashCode += getTransactionNo().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getTransctionType() != null) {
            _hashCode += getTransctionType().hashCode();
        }
        if (getTransactionDate() != null) {
            _hashCode += getTransactionDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportSaleSumarryForDetailData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("seller");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Seller"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalAmount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalAmount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalRecords");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalRecords"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantity");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalMoney");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TotalMoney"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transactionNo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TransactionNo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("accountName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "AccountName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transctionType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TransctionType"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transactionDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "TransactionDate"));
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
