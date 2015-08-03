package vng.bankinggateway.common.util;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    public static String encrypt(String plaintext, byte[] key,
            byte[] sharedvector) {
        Cipher c;
        try {
            c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"),
                    new IvParameterSpec(sharedvector));
            byte[] encrypted = c.doFinal(plaintext.getBytes("UTF-8"));
            return Base64Coder.encodeLines(encrypted);
        } catch (Exception e) {
            return "";
        }

    }

    public static String decrypt(String ciphertext, byte[] key,
            byte[] sharedvector) throws Exception {
        ciphertext = ciphertext.replace('@', '+');
        Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"),
                new IvParameterSpec(sharedvector));
        byte[] decrypted = c.doFinal(Base64Coder.decodeLines(ciphertext));
        return new String(decrypted, "UTF-8");
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('A' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String MD5(String text) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] md5hash = new byte[32];
            md.update(text.getBytes("UTF-8"), 0, text.length());
            md5hash = md.digest();
            return convertToHex(md5hash);
        } catch (Exception ex) {
        }
        return "";
    }

    public static String SHA1(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
            return convertToHex(sha1hash);
            } catch (Exception ex) {
            }
        return "";
    }
}