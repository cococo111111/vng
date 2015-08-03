
/**
 * PaymentServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package vng.zingme.payment.stub;

    /**
     *  PaymentServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class PaymentServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public PaymentServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public PaymentServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getMoney method
            * override this method for handling normal response from getMoney operation
            */
           public void receiveResultgetMoney(
                    vng.zingme.payment.stub.PaymentServiceStub.GetMoneyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMoney operation
           */
            public void receiveErrorgetMoney(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUser method
            * override this method for handling normal response from getUser operation
            */
           public void receiveResultgetUser(
                    vng.zingme.payment.stub.PaymentServiceStub.GetUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUser operation
           */
            public void receiveErrorgetUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addMoney method
            * override this method for handling normal response from addMoney operation
            */
           public void receiveResultaddMoney(
                    vng.zingme.payment.stub.PaymentServiceStub.AddMoneyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addMoney operation
           */
            public void receiveErroraddMoney(java.lang.Exception e) {
            }
                


    }
    