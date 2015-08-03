package com.vng.zing.credits.compensation;

import com.vng.jcore.common.LogUtil;
import com.vng.zing.credits.compensation.model.AdjustData;
import com.vng.zing.credits.compensation.model.CompensationHandler;
import java.io.*;
import java.util.List;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class Main {

    public static void main(String[] args) throws IOException {
        LogUtil.init();
        if ("1".equals(System.getProperty("sort"))) {
            String path = System.getProperty("file");
            BufferedReader in = new BufferedReader(new FileReader(path));
            String userId;
            while ((userId = in.readLine()) != null) {
                try {
                    CompensationHandler.processSortCache(Integer.parseInt(userId));
                } catch (Exception ex) {
                    System.out.println("userID: " + userId);
                    System.out.println(ex);
                }
            }
        } else {
            String date = System.getProperty("date");
            String path = System.getProperty("file");
            String status = System.getProperty("status");
                        
            List<AdjustData> data = CompensationHandler.getCompensationList(date, status);
            OutputStreamWriter os = new FileWriter(path);
            for (AdjustData ad : data) {
                os.write(ad.toString() + "\n");
            }
            os.flush();
            os.close();
            if ("1".equals(System.getProperty("compensate"))) {
                CompensationHandler.processCompensaionList(data);
            }
            if ("1".equals(System.getProperty("updatecache"))) {
                CompensationHandler.processUpdateCache(data);
            }
            if ("1".equals(System.getProperty("updateStorage"))) {
                CompensationHandler.processUpdateStorage(data);
            }
        }
    }
}
