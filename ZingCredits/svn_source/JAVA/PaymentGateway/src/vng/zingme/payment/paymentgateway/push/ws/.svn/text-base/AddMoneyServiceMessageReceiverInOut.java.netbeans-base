
/**
 * AddMoneyServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.3  Built on : Nov 12, 2010 (02:24:07 CET)
 */
        package vng.zingme.payment.paymentgateway.push.ws;

import org.apache.axis2.context.MessageContext;

        /**
        *  AddMoneyServiceMessageReceiverInOut message receiver
        */

        public class AddMoneyServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        MessageContext inMessageContext = MessageContext.getCurrentMessageContext();
        String ip = (String)inMessageContext.getProperty("REMOTE_ADDR");

        AddMoneyServiceSkeletonInterface skel = (AddMoneyServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("getMoney".equals(methodName)){
                
                vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse getMoneyResponse11 = null;
	                        vng.zingme.payment.paymentgateway.push.ws.GetMoney wrappedParam =
                                                             (vng.zingme.payment.paymentgateway.push.ws.GetMoney)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    vng.zingme.payment.paymentgateway.push.ws.GetMoney.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getMoneyResponse11 =
                                                   
                                                   
                                                         skel.GetMoney(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getMoneyResponse11, false);
                                    } else 

            if("addMoney".equals(methodName)){
                
                vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse addMoneyResponse13 = null;
	                        vng.zingme.payment.paymentgateway.push.ws.AddMoney wrappedParam =
                                                             (vng.zingme.payment.paymentgateway.push.ws.AddMoney)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    vng.zingme.payment.paymentgateway.push.ws.AddMoney.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addMoneyResponse13 =
                                                   
                                                   
                                                         skel.AddMoney(wrappedParam, ip)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addMoneyResponse13, false);
                                    } else 

            if("pushMoney".equals(methodName)){
                
                vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse pushMoneyResponse15 = null;
	                        vng.zingme.payment.paymentgateway.push.ws.PushMoney wrappedParam =
                                                             (vng.zingme.payment.paymentgateway.push.ws.PushMoney)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    vng.zingme.payment.paymentgateway.push.ws.PushMoney.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               pushMoneyResponse15 =
                                                   
                                                   
                                                         skel.PushMoney(wrappedParam, ip)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), pushMoneyResponse15, false);
                                    } else 

            if("getSum".equals(methodName)){
                
                vng.zingme.payment.paymentgateway.push.ws.GetSumResponse getSumResponse17 = null;
	                        vng.zingme.payment.paymentgateway.push.ws.GetSum wrappedParam =
                                                             (vng.zingme.payment.paymentgateway.push.ws.GetSum)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    vng.zingme.payment.paymentgateway.push.ws.GetSum.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getSumResponse17 =
                                                   
                                                   
                                                         skel.GetSum(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getSumResponse17, false);
                                    } else 

            if("getUser".equals(methodName)){
                
                vng.zingme.payment.paymentgateway.push.ws.GetUserResponse getUserResponse19 = null;
	                        vng.zingme.payment.paymentgateway.push.ws.GetUser wrappedParam =
                                                             (vng.zingme.payment.paymentgateway.push.ws.GetUser)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    vng.zingme.payment.paymentgateway.push.ws.GetUser.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserResponse19 =
                                                   
                                                   
                                                         skel.GetUser(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserResponse19, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.GetMoney param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetMoney.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.AddMoney param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.AddMoney.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.PushMoney param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.PushMoney.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.GetSum param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetSum.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.GetSumResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetSumResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.GetUser param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetUser.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vng.zingme.payment.paymentgateway.push.ws.GetUserResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetUserResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse wrapGetMoney(){
                                vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse wrappedElement = new vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse wrapAddMoney(){
                                vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse wrappedElement = new vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse wrapPushMoney(){
                                vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse wrappedElement = new vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vng.zingme.payment.paymentgateway.push.ws.GetSumResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetSumResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private vng.zingme.payment.paymentgateway.push.ws.GetSumResponse wrapGetSum(){
                                vng.zingme.payment.paymentgateway.push.ws.GetSumResponse wrappedElement = new vng.zingme.payment.paymentgateway.push.ws.GetSumResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vng.zingme.payment.paymentgateway.push.ws.GetUserResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(vng.zingme.payment.paymentgateway.push.ws.GetUserResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private vng.zingme.payment.paymentgateway.push.ws.GetUserResponse wrapGetUser(){
                                vng.zingme.payment.paymentgateway.push.ws.GetUserResponse wrappedElement = new vng.zingme.payment.paymentgateway.push.ws.GetUserResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (vng.zingme.payment.paymentgateway.push.ws.GetMoney.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.GetMoney.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.GetMoneyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.AddMoney.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.AddMoney.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.AddMoneyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.PushMoney.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.PushMoney.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.PushMoneyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.GetSum.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.GetSum.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.GetSumResponse.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.GetSumResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.GetUser.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.GetUser.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vng.zingme.payment.paymentgateway.push.ws.GetUserResponse.class.equals(type)){
                
                           return vng.zingme.payment.paymentgateway.push.ws.GetUserResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    