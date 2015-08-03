
/**
 * MerchantSVCCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package vng.bankinggateway.stub;

    /**
     *  MerchantSVCCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MerchantSVCCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MerchantSVCCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MerchantSVCCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for helloWorld method
            * override this method for handling normal response from helloWorld operation
            */
           public void receiveResulthelloWorld(
                    vng.bankinggateway.stub.MerchantSVCStub.HelloWorldResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from helloWorld operation
           */
            public void receiveErrorhelloWorld(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for execute method
            * override this method for handling normal response from execute operation
            */
           public void receiveResultexecute(
                    vng.bankinggateway.stub.MerchantSVCStub.ExecuteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from execute operation
           */
            public void receiveErrorexecute(java.lang.Exception e) {
            }
                


    }
    