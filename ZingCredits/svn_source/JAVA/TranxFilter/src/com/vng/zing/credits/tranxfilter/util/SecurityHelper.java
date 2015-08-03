package com.vng.zing.credits.tranxfilter.util;

import com.twmacinta.util.MD5;

/**
 *
 * @author quytp2
 */
public class SecurityHelper {
    public static String md5(String key) {
        MD5 md = new MD5(key);
        return md.asHex();
    }
}
