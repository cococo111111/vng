/**
 * SendSmsBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

public class SendSmsBindingStub extends org.apache.axis.client.Stub implements org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface.SendSms {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendSms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "addresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"), org.apache.axis.types.URI[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "senderName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "charging"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ChargingInformation"), org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "message"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "receiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "SimpleReference"), org.csapi.www.schema.parlayx.common.v2_1.SimpleReference.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendSmsLogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "addresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"), org.apache.axis.types.URI[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "senderName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "charging"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ChargingInformation"), org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "image"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "smsFormat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/v2_2", "SmsFormat"), org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "receiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "SimpleReference"), org.csapi.www.schema.parlayx.common.v2_1.SimpleReference.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sendSmsRingtone");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "addresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"), org.apache.axis.types.URI[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "senderName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "charging"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ChargingInformation"), org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "ringtone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "smsFormat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/v2_2", "SmsFormat"), org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "receiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "SimpleReference"), org.csapi.www.schema.parlayx.common.v2_1.SimpleReference.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSmsDeliveryStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "requestIdentifier"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/v2_2", "DeliveryInformation"));
        oper.setReturnClass(org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.PolicyException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"),
                      "org.csapi.www.schema.parlayx.common.v2_1.ServiceException",
                      new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException"), 
                      true
                     ));
        _operations[3] = oper;

    }

    public SendSmsBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SendSmsBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SendSmsBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ChargingInformation");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.common.v2_1.PolicyException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.common.v2_1.ServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1", "SimpleReference");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.common.v2_1.SimpleReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/v2_2", "DeliveryInformation");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/v2_2", "DeliveryStatus");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.sms.v2_2.DeliveryStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/v2_2", "SmsFormat");
            cachedSerQNames.add(qName);
            cls = org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

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

    public java.lang.String sendSms(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, java.lang.String message, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "sendSms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addresses, senderName, charging, message, receiptRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.PolicyException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.PolicyException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.ServiceException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String sendSmsLogo(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, byte[] image, org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat smsFormat, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "sendSmsLogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addresses, senderName, charging, image, smsFormat, receiptRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.PolicyException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.PolicyException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.ServiceException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String sendSmsRingtone(org.apache.axis.types.URI[] addresses, java.lang.String senderName, org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation charging, java.lang.String ringtone, org.csapi.www.schema.parlayx.sms.v2_2.SmsFormat smsFormat, org.csapi.www.schema.parlayx.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "sendSmsRingtone"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addresses, senderName, charging, ringtone, smsFormat, receiptRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.PolicyException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.PolicyException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.ServiceException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[] getSmsDeliveryStatus(java.lang.String requestIdentifier) throws java.rmi.RemoteException, org.csapi.www.schema.parlayx.common.v2_1.PolicyException, org.csapi.www.schema.parlayx.common.v2_1.ServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local", "getSmsDeliveryStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {requestIdentifier});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.csapi.www.schema.parlayx.sms.v2_2.DeliveryInformation[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.PolicyException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.PolicyException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.csapi.www.schema.parlayx.common.v2_1.ServiceException) {
              throw (org.csapi.www.schema.parlayx.common.v2_1.ServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
