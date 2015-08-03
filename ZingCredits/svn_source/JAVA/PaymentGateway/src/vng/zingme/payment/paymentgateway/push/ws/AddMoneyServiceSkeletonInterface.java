
/**
 * AddMoneyServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.3  Built on : Nov 12, 2010 (02:24:07 CET)
 */
    package vng.zingme.payment.paymentgateway.push.ws;
    /**
     *  AddMoneyServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface AddMoneyServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param getMoney
         */

        
                public vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse GetMoney
                (
                  vng.zingme.payment.paymentgateway.push.ws.GetMoney getMoney
                 )
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param addMoney
         */

        
                public vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse AddMoney
                (
                  vng.zingme.payment.paymentgateway.push.ws.AddMoney addMoney, String clientIP
                 )
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param pushMoney
         */

        
                public vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse PushMoney
                (
                  vng.zingme.payment.paymentgateway.push.ws.PushMoney pushMoney, String clientIP
                 )
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param getSum
         */

        
                public vng.zingme.payment.paymentgateway.push.ws.GetSumResponse GetSum
                (
                  vng.zingme.payment.paymentgateway.push.ws.GetSum getSum
                 )
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param getUser
         */

        
                public vng.zingme.payment.paymentgateway.push.ws.GetUserResponse GetUser
                (
                  vng.zingme.payment.paymentgateway.push.ws.GetUser getUser
                 )
            ;
        
         }
    