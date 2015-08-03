package vng.zingme.securities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jpos.iso.ISOUtil;

public class CryptoManager {
	public static final String ALGORITHM_DES 		= "DES";
	public static final String ALGORITHM_3DES 		= "DESede";
	public static final String MODE_CBC 			= "CBC";
	public static final String MODE_ECB 			= "ECB";
	public static final String PADDING_PKCS5 		= "PKCS5Padding";
	public static final String PADDING_NONE 		= "NoPadding";
	
	public static final String PROTECTED_QBEAN = "";
	public static final String DEFAULT_IV = "zingme00";
	public static final String DEFAULT_KEY = "payment_gateway000000000";
	public static byte[] encrypt(byte[] key, byte[] input, String algorithm, String mode, 
			String padding, byte[] iv)
		throws Exception{
		AlgorithmParameterSpec paramSpec = null;
		DESedeKeySpec keySpec = new DESedeKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		String al = algorithm;
		if(mode != null){
			al += "/" + mode + "/" + padding; 
		}
		Cipher cipher = Cipher.getInstance(al );
		if(mode == null || mode == MODE_ECB){
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		}else{
			paramSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
		}
		byte[] encrypted = cipher.doFinal(input);
		return encrypted;
	}
	public static byte[] decrypt(byte[] key, byte[] encrypted, String algorithm, String mode, 
			String padding, byte[] iv)throws Exception{
		AlgorithmParameterSpec paramSpec = null;
		DESedeKeySpec keySpec = new DESedeKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		String al = algorithm;
		if(mode != null ){
			al += "/" + mode + "/" + padding; 
		}
		Cipher cipher = Cipher.getInstance(al);
		if(mode == null || mode == MODE_ECB){
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		}else{
			paramSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
		}
		return cipher.doFinal(encrypted);
	}
	 private byte[] dodes (byte[] data, int mode) throws GeneralSecurityException
	 {
	     Cipher cipher = Cipher.getInstance ("DES");
	     cipher.init (mode, new SecretKeySpec(getKey(), "DES"));
	     return cipher.doFinal (data);
	 }
	  protected byte[] getKey() {
	      return "CAFEBABE".getBytes();
	  }
	  
	 public Document encrypt (Document doc)
     		throws GeneralSecurityException, IOException
	 {
	     ByteArrayOutputStream os = new ByteArrayOutputStream ();
	     OutputStreamWriter writer = new OutputStreamWriter (os);
	     XMLOutputter out = new XMLOutputter (Format.getPrettyFormat());
	     out.output (doc, writer);
	     writer.close ();
	
	     byte[] crypt = dodes (os.toByteArray(), Cipher.ENCRYPT_MODE);
	
	     Document secureDoc = new Document ();
	     Element root = new Element (PROTECTED_QBEAN);
	     secureDoc.setRootElement (root);
	     Element secureData = new Element ("data");
	     root.addContent (secureData);
	
	     secureData.setText (
	         ISOUtil.hexString (crypt)
	     );
	     return secureDoc;
	 }
	 public Document decrypt (Document doc) 
     		throws GeneralSecurityException, IOException, JDOMException
	 {
	     Element root = doc.getRootElement ();
	     if (PROTECTED_QBEAN.equals (root.getName ())) {
	         Element data = root.getChild ("data");
	         if (data != null) {
	             ByteArrayInputStream is = new ByteArrayInputStream (
	                 dodes (
	                     ISOUtil.hex2byte (data.getTextTrim()),
	                     Cipher.DECRYPT_MODE)
	             );
	             SAXBuilder builder = new SAXBuilder ();
	             doc = builder.build (is);
	         }
	     }
	     return doc;
	 }
}
