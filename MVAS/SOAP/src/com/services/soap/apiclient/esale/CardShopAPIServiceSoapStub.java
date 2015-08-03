/**
 * CardShopAPIServiceSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public class CardShopAPIServiceSoapStub extends org.apache.axis.client.Stub implements com.services.soap.apiclient.esale.CardShopAPIServiceSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[20];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetZXBalance");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ZXBalanceResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ZXBalanceResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ZXBalanceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetMoneyBalance");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "MoneyBalanceResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.MoneyBalanceResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "MoneyBalanceResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetPriceLists");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "priceListCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceListResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.GetPriceListResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceListResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetPaymentMethod");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "userIP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethodResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.GetPaymentMethodResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethodResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InsertAgencyOrder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "paymentMethodId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "areaId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "totalAmount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "comment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "InsertAgencyOrderResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.InsertAgencyOrderResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "InsertAgencyOrderResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RegisterAgency");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyTypeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "accountName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "fullName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "servicePhone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "areaId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "cityId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "districtId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ward"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "identityNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "bussinessCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "taxCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "telephone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "mobile1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "mobile2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "email"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "birthday"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "RegisterAgencyResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.RegisterAgencyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "RegisterAgencyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("UpdateAgency");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "fullName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "servicePhone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "areaId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "cityId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "districtId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ward"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "identityNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "bussinessCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "taxCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "telephone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "mobile1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "mobile2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "email"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "birthday"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "RegisterAgencyResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.RegisterAgencyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "RegisterAgencyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ActivateAgency");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "isActivedMobi"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "isActivedEmail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ActivateAgencyResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ActivateAgencyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ActivateAgencyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReceiverMOEsale");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "requestID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "serviceID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "commandCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "message"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "mobileOperator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "requestTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReceiverMOEsaleResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("TopupZX");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "userIP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "employeeCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "listOfObjects"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "comment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "quantityConvert"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "unitPriceConvert"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "totalAmountConvert"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "isConvertMoney"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.TopupZXResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetSupplierCardsBySupplierTypeCode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierTypeCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.SupplierCardResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckQuantitySupplierCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "datas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantitySupplierCardResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantitySupplierCardResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetOrderNo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TransactionType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetOrderNoResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.GetOrderNoResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetOrderNoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetSuppliersByStatusID");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "StatusID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.SupplierResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CardOrdersInsert");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "orderNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "agencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "saleTypeId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "totalAmount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"), java.math.BigDecimal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "userIP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "listOfOrders"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "createdBy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "accountName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "createdUserRole"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "saleTypeCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "totalQuantity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.AgencyOrdersInsertResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportSaleSumarryForDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageIndex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Saller"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "FromDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ToDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderBy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderDirection"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportSaleSumarry");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Saller"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "FromDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ToDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageIndex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderBy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderDirection"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ReportSaleSumarryResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportAgencyBoughtZX");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CurrentPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "PageSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SortCriteria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "FromDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ToDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "IsCardShop"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportAgencySaleCSDetailByDate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageIndex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Employee"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "FromDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ToDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderBy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderDirection"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardTypeCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDateResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportSaleDetailByZX");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "pageIndex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderBy"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "OrderDirection"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "FromDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ToDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "sig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Employee"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXResult"));
        oper.setReturnClass(com.services.soap.apiclient.esale.ReportSaleDetailByZXResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    public CardShopAPIServiceSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public CardShopAPIServiceSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public CardShopAPIServiceSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ActivateAgencyResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ActivateAgencyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.AgencyOrdersInsertData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.AgencyOrdersInsertResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfAgencyOrdersInsertData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.AgencyOrdersInsertData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsertData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "AgencyOrdersInsert");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfCardData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.CardData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardData");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfCheckQuantityData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.CheckQuantityData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantityData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantity");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfGetPaymentMethodData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetPaymentMethodData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethodData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "PaymentMethod");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfGetPriceListData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetPriceListData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceListData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfReportAgencyBoughtZXData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportAgencyBoughtZXData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZX");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfReportAgencySaleCSDetailByDateData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDateData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDate");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfReportSaleDetailByZXData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleDetailByZXData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZX");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfReportSaleSumarryData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleSumarryData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarry");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfReportSaleSumarryForDetailData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleSumarryForDetailData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetail");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfSupplierCardData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.SupplierCardData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCard");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfSupplierData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.SupplierData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "Supplier");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ArrayOfTopupZXData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.TopupZXData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXData");
            qName2 = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZX");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.CardData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantityData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.CheckQuantityData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantitySupplierCardResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetOrderNoResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetOrderNoResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethodData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetPaymentMethodData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethodResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetPaymentMethodResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceListData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetPriceListData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceListResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.GetPriceListResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "InsertAgencyOrderResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.InsertAgencyOrderResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "MoneyBalanceResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.MoneyBalanceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "RegisterAgencyResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.RegisterAgencyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportAgencyBoughtZXData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZXResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDateData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDateResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleDetailByZXData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZXResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleDetailByZXResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleSumarryData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleSumarryForDetailData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetailResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ReportSaleSumarryResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.SupplierCardData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierCardResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.SupplierCardResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.SupplierData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "SupplierResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.SupplierResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.TopupZXData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZXResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.TopupZXResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ZXBalanceData");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ZXBalanceData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ZXBalanceResult");
            cachedSerQNames.add(qName);
            cls = com.services.soap.apiclient.esale.ZXBalanceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.services.soap.apiclient.esale.ZXBalanceResult getZXBalance(java.lang.String agencyCode, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetZXBalance");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetZXBalance"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {agencyCode, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ZXBalanceResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ZXBalanceResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ZXBalanceResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.MoneyBalanceResult getMoneyBalance(java.lang.String agencyCode, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetMoneyBalance");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetMoneyBalance"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {agencyCode, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.MoneyBalanceResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.MoneyBalanceResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.MoneyBalanceResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.GetPriceListResult getPriceLists(java.lang.String priceListCode, int type, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetPriceLists");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPriceLists"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {priceListCode, new java.lang.Integer(type), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.GetPriceListResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.GetPriceListResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.GetPriceListResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.GetPaymentMethodResult getPaymentMethod(java.lang.String userIP, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetPaymentMethod");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetPaymentMethod"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userIP, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.GetPaymentMethodResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.GetPaymentMethodResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.GetPaymentMethodResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.InsertAgencyOrderResult insertAgencyOrder(java.lang.String agencyCode, int paymentMethodId, int areaId, java.math.BigDecimal totalAmount, java.lang.String user, java.lang.String comment, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/InsertAgencyOrder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "InsertAgencyOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {agencyCode, new java.lang.Integer(paymentMethodId), new java.lang.Integer(areaId), totalAmount, user, comment, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.InsertAgencyOrderResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.InsertAgencyOrderResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.InsertAgencyOrderResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.RegisterAgencyResult registerAgency(int agencyTypeId, java.lang.String agencyCode, java.lang.String accountName, java.lang.String fullName, java.lang.String address, java.lang.String servicePhone, int areaId, int cityId, int districtId, java.lang.String ward, java.lang.String identityNumber, java.lang.String bussinessCode, java.lang.String taxCode, java.lang.String telephone, java.lang.String mobile1, java.lang.String mobile2, java.lang.String email, int sex, java.lang.String birthday, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/RegisterAgency");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "RegisterAgency"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(agencyTypeId), agencyCode, accountName, fullName, address, servicePhone, new java.lang.Integer(areaId), new java.lang.Integer(cityId), new java.lang.Integer(districtId), ward, identityNumber, bussinessCode, taxCode, telephone, mobile1, mobile2, email, new java.lang.Integer(sex), birthday, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.RegisterAgencyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.RegisterAgencyResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.RegisterAgencyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.RegisterAgencyResult updateAgency(java.lang.String agencyCode, java.lang.String fullName, java.lang.String address, java.lang.String servicePhone, int areaId, int cityId, int districtId, java.lang.String ward, java.lang.String identityNumber, java.lang.String bussinessCode, java.lang.String taxCode, java.lang.String telephone, java.lang.String mobile1, java.lang.String mobile2, java.lang.String email, int sex, java.lang.String birthday, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/UpdateAgency");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "UpdateAgency"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {agencyCode, fullName, address, servicePhone, new java.lang.Integer(areaId), new java.lang.Integer(cityId), new java.lang.Integer(districtId), ward, identityNumber, bussinessCode, taxCode, telephone, mobile1, mobile2, email, new java.lang.Integer(sex), birthday, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.RegisterAgencyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.RegisterAgencyResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.RegisterAgencyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.ActivateAgencyResult activateAgency(java.lang.String agencyCode, int isActivedMobi, int isActivedEmail, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ActivateAgency");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ActivateAgency"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {agencyCode, new java.lang.Integer(isActivedMobi), new java.lang.Integer(isActivedEmail), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ActivateAgencyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ActivateAgencyResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ActivateAgencyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int receiverMOEsale(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String mobileOperator, java.util.Calendar requestTime, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ReceiverMOEsale");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReceiverMOEsale"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {requestID, userID, serviceID, commandCode, message, mobileOperator, requestTime, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.TopupZXResult topupZX(java.lang.String userIP, java.lang.String agencyCode, java.lang.String employeeCode, java.lang.String listOfObjects, java.lang.String comment, int type, long quantityConvert, java.math.BigDecimal unitPriceConvert, java.math.BigDecimal totalAmountConvert, int isConvertMoney, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/TopupZX");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "TopupZX"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userIP, agencyCode, employeeCode, listOfObjects, comment, new java.lang.Integer(type), new java.lang.Long(quantityConvert), unitPriceConvert, totalAmountConvert, new java.lang.Integer(isConvertMoney), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.TopupZXResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.TopupZXResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.TopupZXResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.SupplierCardResult getSupplierCardsBySupplierTypeCode(java.lang.String supplierTypeCode, int agencyID, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetSupplierCardsBySupplierTypeCode");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetSupplierCardsBySupplierTypeCode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {supplierTypeCode, new java.lang.Integer(agencyID), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.SupplierCardResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.SupplierCardResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.SupplierCardResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult checkQuantitySupplierCard(java.lang.String datas, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/CheckQuantitySupplierCard");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CheckQuantitySupplierCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datas, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.GetOrderNoResult getOrderNo(int supplierId, java.lang.String transactionType, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetOrderNo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetOrderNo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(supplierId), transactionType, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.GetOrderNoResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.GetOrderNoResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.GetOrderNoResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.SupplierResult getSuppliersByStatusID(int statusID, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/GetSuppliersByStatusID");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "GetSuppliersByStatusID"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(statusID), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.SupplierResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.SupplierResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.SupplierResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.AgencyOrdersInsertResult cardOrdersInsert(java.lang.String orderNo, java.lang.String agencyCode, int saleTypeId, java.math.BigDecimal totalAmount, java.lang.String userIP, java.lang.String listOfOrders, java.lang.String createdBy, java.lang.String accountName, java.lang.String createdUserRole, java.lang.String saleTypeCode, int totalQuantity, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/CardOrdersInsert");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "CardOrdersInsert"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {orderNo, agencyCode, new java.lang.Integer(saleTypeId), totalAmount, userIP, listOfOrders, createdBy, accountName, createdUserRole, saleTypeCode, new java.lang.Integer(totalQuantity), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.AgencyOrdersInsertResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.AgencyOrdersInsertResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.AgencyOrdersInsertResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult reportSaleSumarryForDetail(int pageSize, int pageIndex, java.lang.String saller, java.lang.String fromDate, java.lang.String toDate, java.lang.String orderBy, java.lang.String orderDirection, java.lang.String agencyCode, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ReportSaleSumarryForDetail");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarryForDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(pageSize), new java.lang.Integer(pageIndex), saller, fromDate, toDate, orderBy, orderDirection, agencyCode, sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.ReportSaleSumarryResult reportSaleSumarry(java.lang.String agencyCode, java.lang.String saller, java.lang.String fromDate, java.lang.String toDate, java.lang.String sig, int pageSize, int pageIndex, java.lang.String orderBy, java.lang.String orderDirection) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ReportSaleSumarry");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleSumarry"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {agencyCode, saller, fromDate, toDate, sig, new java.lang.Integer(pageSize), new java.lang.Integer(pageIndex), orderBy, orderDirection});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ReportSaleSumarryResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ReportSaleSumarryResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ReportSaleSumarryResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult reportAgencyBoughtZX(int currentPage, int pageSize, java.lang.String sortCriteria, java.lang.String agencyCode, java.lang.String fromDate, java.lang.String toDate, int isCardShop, java.lang.String sig) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ReportAgencyBoughtZX");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencyBoughtZX"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(currentPage), new java.lang.Integer(pageSize), sortCriteria, agencyCode, fromDate, toDate, new java.lang.Integer(isCardShop), sig});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult reportAgencySaleCSDetailByDate(int pageSize, int pageIndex, int supplierId, int supplierCardId, java.lang.String employee, java.lang.String orderNo, java.lang.String fromDate, java.lang.String toDate, java.lang.String sig, java.lang.String orderBy, java.lang.String orderDirection, java.lang.String cardTypeCode, java.lang.String agencyCode) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ReportAgencySaleCSDetailByDate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportAgencySaleCSDetailByDate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(pageSize), new java.lang.Integer(pageIndex), new java.lang.Integer(supplierId), new java.lang.Integer(supplierCardId), employee, orderNo, fromDate, toDate, sig, orderBy, orderDirection, cardTypeCode, agencyCode});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.services.soap.apiclient.esale.ReportSaleDetailByZXResult reportSaleDetailByZX(int pageSize, int pageIndex, java.lang.String orderBy, java.lang.String orderDirection, java.lang.String agencyCode, java.lang.String fromDate, java.lang.String toDate, java.lang.String sig, java.lang.String employee) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://CardShopAPIService.vng.com.vn/ReportSaleDetailByZX");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://CardShopAPIService.vng.com.vn/", "ReportSaleDetailByZX"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(pageSize), new java.lang.Integer(pageIndex), orderBy, orderDirection, agencyCode, fromDate, toDate, sig, employee});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.services.soap.apiclient.esale.ReportSaleDetailByZXResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.services.soap.apiclient.esale.ReportSaleDetailByZXResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.services.soap.apiclient.esale.ReportSaleDetailByZXResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
