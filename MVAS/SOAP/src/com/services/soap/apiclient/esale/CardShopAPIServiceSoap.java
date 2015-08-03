/**
 * CardShopAPIServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services.soap.apiclient.esale;

public interface CardShopAPIServiceSoap extends java.rmi.Remote {
    public com.services.soap.apiclient.esale.ZXBalanceResult getZXBalance(java.lang.String agencyCode, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.MoneyBalanceResult getMoneyBalance(java.lang.String agencyCode, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.GetPriceListResult getPriceLists(java.lang.String priceListCode, int type, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.GetPaymentMethodResult getPaymentMethod(java.lang.String userIP, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.InsertAgencyOrderResult insertAgencyOrder(java.lang.String agencyCode, int paymentMethodId, int areaId, java.math.BigDecimal totalAmount, java.lang.String user, java.lang.String comment, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.RegisterAgencyResult registerAgency(int agencyTypeId, java.lang.String agencyCode, java.lang.String accountName, java.lang.String fullName, java.lang.String address, java.lang.String servicePhone, int areaId, int cityId, int districtId, java.lang.String ward, java.lang.String identityNumber, java.lang.String bussinessCode, java.lang.String taxCode, java.lang.String telephone, java.lang.String mobile1, java.lang.String mobile2, java.lang.String email, int sex, java.lang.String birthday, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.RegisterAgencyResult updateAgency(java.lang.String agencyCode, java.lang.String fullName, java.lang.String address, java.lang.String servicePhone, int areaId, int cityId, int districtId, java.lang.String ward, java.lang.String identityNumber, java.lang.String bussinessCode, java.lang.String taxCode, java.lang.String telephone, java.lang.String mobile1, java.lang.String mobile2, java.lang.String email, int sex, java.lang.String birthday, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.ActivateAgencyResult activateAgency(java.lang.String agencyCode, int isActivedMobi, int isActivedEmail, java.lang.String sig) throws java.rmi.RemoteException;
    public int receiverMOEsale(java.lang.String requestID, java.lang.String userID, java.lang.String serviceID, java.lang.String commandCode, java.lang.String message, java.lang.String mobileOperator, java.util.Calendar requestTime, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.TopupZXResult topupZX(java.lang.String userIP, java.lang.String agencyCode, java.lang.String employeeCode, java.lang.String listOfObjects, java.lang.String comment, int type, long quantityConvert, java.math.BigDecimal unitPriceConvert, java.math.BigDecimal totalAmountConvert, int isConvertMoney, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.SupplierCardResult getSupplierCardsBySupplierTypeCode(java.lang.String supplierTypeCode, int agencyID, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.CheckQuantitySupplierCardResult checkQuantitySupplierCard(java.lang.String datas, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.GetOrderNoResult getOrderNo(int supplierId, java.lang.String transactionType, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.SupplierResult getSuppliersByStatusID(int statusID, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.AgencyOrdersInsertResult cardOrdersInsert(java.lang.String orderNo, java.lang.String agencyCode, int saleTypeId, java.math.BigDecimal totalAmount, java.lang.String userIP, java.lang.String listOfOrders, java.lang.String createdBy, java.lang.String accountName, java.lang.String createdUserRole, java.lang.String saleTypeCode, int totalQuantity, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.ReportSaleSumarryForDetailResult reportSaleSumarryForDetail(int pageSize, int pageIndex, java.lang.String saller, java.lang.String fromDate, java.lang.String toDate, java.lang.String orderBy, java.lang.String orderDirection, java.lang.String agencyCode, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.ReportSaleSumarryResult reportSaleSumarry(java.lang.String agencyCode, java.lang.String saller, java.lang.String fromDate, java.lang.String toDate, java.lang.String sig, int pageSize, int pageIndex, java.lang.String orderBy, java.lang.String orderDirection) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.ReportAgencyBoughtZXResult reportAgencyBoughtZX(int currentPage, int pageSize, java.lang.String sortCriteria, java.lang.String agencyCode, java.lang.String fromDate, java.lang.String toDate, int isCardShop, java.lang.String sig) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.ReportAgencySaleCSDetailByDateResult reportAgencySaleCSDetailByDate(int pageSize, int pageIndex, int supplierId, int supplierCardId, java.lang.String employee, java.lang.String orderNo, java.lang.String fromDate, java.lang.String toDate, java.lang.String sig, java.lang.String orderBy, java.lang.String orderDirection, java.lang.String cardTypeCode, java.lang.String agencyCode) throws java.rmi.RemoteException;
    public com.services.soap.apiclient.esale.ReportSaleDetailByZXResult reportSaleDetailByZX(int pageSize, int pageIndex, java.lang.String orderBy, java.lang.String orderDirection, java.lang.String agencyCode, java.lang.String fromDate, java.lang.String toDate, java.lang.String sig, java.lang.String employee) throws java.rmi.RemoteException;
}
