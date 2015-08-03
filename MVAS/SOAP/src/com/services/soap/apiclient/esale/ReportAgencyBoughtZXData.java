/**
 * ReportAgencyBoughtZXData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class ReportAgencyBoughtZXData  implements java.io.Serializable {
    private java.lang.String orderNo;  // attribute

    private java.lang.String createdDate;  // attribute

    private java.lang.String seller;  // attribute

    private java.math.BigDecimal quantity;  // attribute

    private java.math.BigDecimal unitPrice;  // attribute

    private java.math.BigDecimal totalAmount;  // attribute

    private java.lang.String orderStatusAgency;  // attribute

    private java.lang.String approvedDate;  // attribute

    private java.lang.String comment;  // attribute

    public ReportAgencyBoughtZXData() {
    }

    public ReportAgencyBoughtZXData(
           java.lang.String orderNo,
           java.lang.String createdDate,
           java.lang.String seller,
           java.math.BigDecimal quantity,
           java.math.BigDecimal unitPrice,
           java.math.BigDecimal totalAmount,
           java.lang.String orderStatusAgency,
           java.lang.String approvedDate,
           java.lang.String comment) {
           this.orderNo = orderNo;
           this.createdDate = createdDate;
           this.seller = seller;
           this.quantity = quantity;
           this.unitPrice = unitPrice;
           this.totalAmount = totalAmount;
           this.orderStatusAgency = orderStatusAgency;
           this.approvedDate = approvedDate;
           this.comment = comment;
    }


    /**
     * Gets the orderNo value for this ReportAgencyBoughtZXData.
     * 
     * @return orderNo
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }


    /**
     * Sets the orderNo value for this ReportAgencyBoughtZXData.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Gets the createdDate value for this ReportAgencyBoughtZXData.
     * 
     * @return createdDate
     */
    public java.lang.String getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ReportAgencyBoughtZXData.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.lang.String createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the seller value for this ReportAgencyBoughtZXData.
     * 
     * @return seller
     */
    public java.lang.String getSeller() {
        return seller;
    }


    /**
     * Sets the seller value for this ReportAgencyBoughtZXData.
     * 
     * @param seller
     */
    public void setSeller(java.lang.String seller) {
        this.seller = seller;
    }


    /**
     * Gets the quantity value for this ReportAgencyBoughtZXData.
     * 
     * @return quantity
     */
    public java.math.BigDecimal getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this ReportAgencyBoughtZXData.
     * 
     * @param quantity
     */
    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the unitPrice value for this ReportAgencyBoughtZXData.
     * 
     * @return unitPrice
     */
    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }


    /**
     * Sets the unitPrice value for this ReportAgencyBoughtZXData.
     * 
     * @param unitPrice
     */
    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    /**
     * Gets the totalAmount value for this ReportAgencyBoughtZXData.
     * 
     * @return totalAmount
     */
    public java.math.BigDecimal getTotalAmount() {
        return totalAmount;
    }


    /**
     * Sets the totalAmount value for this ReportAgencyBoughtZXData.
     * 
     * @param totalAmount
     */
    public void setTotalAmount(java.math.BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    /**
     * Gets the orderStatusAgency value for this ReportAgencyBoughtZXData.
     * 
     * @return orderStatusAgency
     */
    public java.lang.String getOrderStatusAgency() {
        return orderStatusAgency;
    }


    /**
     * Sets the orderStatusAgency value for this ReportAgencyBoughtZXData.
     * 
     * @param orderStatusAgency
     */
    public void setOrderStatusAgency(java.lang.String orderStatusAgency) {
        this.orderStatusAgency = orderStatusAgency;
    }


    /**
     * Gets the approvedDate value for this ReportAgencyBoughtZXData.
     * 
     * @return approvedDate
     */
    public java.lang.String getApprovedDate() {
        return approvedDate;
    }


    /**
     * Sets the approvedDate value for this ReportAgencyBoughtZXData.
     * 
     * @param approvedDate
     */
    public void setApprovedDate(java.lang.String approvedDate) {
        this.approvedDate = approvedDate;
    }


    /**
     * Gets the comment value for this ReportAgencyBoughtZXData.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this ReportAgencyBoughtZXData.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportAgencyBoughtZXData)) return false;
        ReportAgencyBoughtZXData other = (ReportAgencyBoughtZXData) obj;
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
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.seller==null && other.getSeller()==null) || 
             (this.seller!=null &&
              this.seller.equals(other.getSeller()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.unitPrice==null && other.getUnitPrice()==null) || 
             (this.unitPrice!=null &&
              this.unitPrice.equals(other.getUnitPrice()))) &&
            ((this.totalAmount==null && other.getTotalAmount()==null) || 
             (this.totalAmount!=null &&
              this.totalAmount.equals(other.getTotalAmount()))) &&
            ((this.orderStatusAgency==null && other.getOrderStatusAgency()==null) || 
             (this.orderStatusAgency!=null &&
              this.orderStatusAgency.equals(other.getOrderStatusAgency()))) &&
            ((this.approvedDate==null && other.getApprovedDate()==null) || 
             (this.approvedDate!=null &&
              this.approvedDate.equals(other.getApprovedDate()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment())));
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
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getSeller() != null) {
            _hashCode += getSeller().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getUnitPrice() != null) {
            _hashCode += getUnitPrice().hashCode();
        }
        if (getTotalAmount() != null) {
            _hashCode += getTotalAmount().hashCode();
        }
        if (getOrderStatusAgency() != null) {
            _hashCode += getOrderStatusAgency().hashCode();
        }
        if (getApprovedDate() != null) {
            _hashCode += getApprovedDate().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportAgencyBoughtZXData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXData"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("orderNo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OrderNo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("createdDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "CreatedDate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("seller");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Seller"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("quantity");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        attrField.setFieldName("orderStatusAgency");
        attrField.setXmlName(new javax.xml.namespace.QName("", "OrderStatusAgency"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("approvedDate");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ApprovedDate"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("comment");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Comment"));
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
