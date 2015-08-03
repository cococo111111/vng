

/**
 * SmsNotificationServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package com.vng.mvas.vms.client;

    /*
     *  SmsNotificationServiceTest Junit test case
    */

    public class SmsNotificationServiceTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testnotifySmsDeliveryReceipt() throws java.lang.Exception{

        com.vng.mvas.vms.client.SmsNotificationServiceStub stub =
                    new com.vng.mvas.vms.client.SmsNotificationServiceStub();//the default implementation should point to the right endpoint

           com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptE notifySmsDeliveryReceipt10=
                                                        (com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptE)getTestObject(com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptE.class);
                    // TODO : Fill in the notifySmsDeliveryReceipt10 here
                
                        assertNotNull(stub.notifySmsDeliveryReceipt(
                        notifySmsDeliveryReceipt10));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartnotifySmsDeliveryReceipt() throws java.lang.Exception{
            com.vng.mvas.vms.client.SmsNotificationServiceStub stub = new com.vng.mvas.vms.client.SmsNotificationServiceStub();
             com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptE notifySmsDeliveryReceipt10=
                                                        (com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptE)getTestObject(com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptE.class);
                    // TODO : Fill in the notifySmsDeliveryReceipt10 here
                

                stub.startnotifySmsDeliveryReceipt(
                         notifySmsDeliveryReceipt10,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends com.vng.mvas.vms.client.SmsNotificationServiceCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultnotifySmsDeliveryReceipt(
                         com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsDeliveryReceiptResponseE result
                            ) {
                
            }

            public void receiveErrornotifySmsDeliveryReceipt(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testnotifySmsReception() throws java.lang.Exception{

        com.vng.mvas.vms.client.SmsNotificationServiceStub stub =
                    new com.vng.mvas.vms.client.SmsNotificationServiceStub();//the default implementation should point to the right endpoint

           com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionE notifySmsReception12=
                                                        (com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionE)getTestObject(com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionE.class);
                    // TODO : Fill in the notifySmsReception12 here
                com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySOAPHeaderE notifySOAPHeader13=
                                                        (com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySOAPHeaderE)getTestObject(com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySOAPHeaderE.class);
                    // TODO : Fill in the notifySOAPHeader13 here
                
                        assertNotNull(stub.notifySmsReception(
                        notifySmsReception12,notifySOAPHeader13));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartnotifySmsReception() throws java.lang.Exception{
            com.vng.mvas.vms.client.SmsNotificationServiceStub stub = new com.vng.mvas.vms.client.SmsNotificationServiceStub();
             com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionE notifySmsReception12=
                                                        (com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionE)getTestObject(com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionE.class);
                    // TODO : Fill in the notifySmsReception12 here
                com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySOAPHeaderE notifySOAPHeader13=
                                                        (com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySOAPHeaderE)getTestObject(com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySOAPHeaderE.class);
                    // TODO : Fill in the notifySOAPHeader13 here
                

                stub.startnotifySmsReception(
                         notifySmsReception12,notifySOAPHeader13,
                    new tempCallbackN65589()
                );
              


        }

        private class tempCallbackN65589  extends com.vng.mvas.vms.client.SmsNotificationServiceCallbackHandler{
            public tempCallbackN65589(){ super(null);}

            public void receiveResultnotifySmsReception(
                         com.vng.mvas.vms.client.SmsNotificationServiceStub.NotifySmsReceptionResponseE result
                            ) {
                
            }

            public void receiveErrornotifySmsReception(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    