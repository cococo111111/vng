package com.vng.zing.credits.compensation;

import com.vng.jcore.common.LogUtil;
import com.vng.zing.credits.compensation.model.CompensationHandler;
import java.io.IOException;

/**
 *
 * @author quytp2@vng.com.vn
 */
public class CompensateCard {

    public static void main(String[] args) throws IOException {
        LogUtil.init();
        String inputFile = System.getProperty("inputfile");

        if (inputFile != null && inputFile.length() > 0) {
            System.out.println("START");
            CompensationHandler.compensationForCard(inputFile);
            System.out.println("FINISH");
        } else {
            //System.out.println(args);
            System.out.println("Need arg : inputfile");
        }
    }
}
