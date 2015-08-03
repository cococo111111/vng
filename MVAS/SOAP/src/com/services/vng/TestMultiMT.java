package com.services.vng;

import java.util.ArrayList;
import java.util.Collection;

import com.vmg.sms.process.ContentAbstract;
import com.vmg.sms.process.Keyword;
import com.vmg.sms.process.MsgObject;

public class TestMultiMT extends ContentAbstract {

	protected Collection getMessages(MsgObject msgObject, Keyword keyword)throws Exception {
        Collection messages = new ArrayList();
        
        msgObject.setUsertext("MT thu 1");
        msgObject.setMsgtype(1);
        messages.add(new MsgObject(msgObject));

        msgObject.setUsertext("MT thu 2");
        msgObject.setMsgtype(0);
        messages.add(new MsgObject(msgObject));
        
        
        msgObject.setUsertext("MT thu 3");
        msgObject.setMsgtype(0);
        messages.add(new MsgObject(msgObject));
        
		return messages;
		
	}

}
