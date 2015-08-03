/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import com.vng.jcore.common.Config;
import com.vng.zing.notify2.object.NotificationObject;
import com.vng.zing.notify2.service.thrift.Notify2ServiceUPool;
//import com.vng.zing.notify2.object.NotificationObject;
//import com.vng.zing.notify2.service.thrift.Notify2ServiceUPool;

import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.thrift.TException;
import org.json.simple.JSONObject;
import org.apache.log4j.Logger;

/**
 *
 * @author locth2
 */
public class PushZMeNotify {

    private final long objId = 0;
    private final int fromId = Integer.parseInt(Config.getParam("zmenotify", "fromuid"));
    private final short appId = 3010;
    private final short actionId = 30100;
    private final long feedId = 4509;
    private final String address = Config.getParam("zmenotify", "address");
    private final String contactList = Config.getParam("zmenotify", "contacts");
    private static final Logger logger = Logger.getLogger(StorageMySQLCli.class);

    public void pushZMeNotify(String content, ArrayList<Integer> contacts) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MSG", content);
        String message_notify = jsonObject.toJSONString();
        long createdtime = System.currentTimeMillis();
        byte type = 1;
        if (!contacts.isEmpty() && contacts != null) {
            for (Integer uid : contacts) {
                NotificationObject notifObj = new NotificationObject(objId, appId, actionId, feedId, objId, fromId, uid, message_notify, createdtime, type);
                final Notify2ServiceUPool instance = Notify2ServiceUPool.getInstance("notify", address, address);
                try {
                    instance.Push(notifObj);
                    logger.info("Pushed notify to " + uid + ": " + content);
//                        System.out.println("da push cho " + uid.toString());
                } catch (TException ex) {
                    java.util.logging.Logger.getLogger(PushZMeNotify.class.getName()).log(Level.SEVERE, null, ex);
                    logger.error(ex.getMessage(), ex);
                }
            }
        }
    }
}
