/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testjzcypher;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;

/**
 *
 * @author namnq
 */
public class Main {

	static {
		try {
			//correct libraries path
			//must use -Djava.library.path=/data/zserver/java/jdk1.6.0_23/jre/lib/amd64/server:/data/zserver/java/jdk1.6.0_23/jre/lib/amd64:/data/zserver/java/jdk1.6.0_23/jre/../lib/amd64:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib:/data/vng/svn_zcypher/zcypher/bin

//			String curLibPath = System.getProperty("java.library.path");
//			curLibPath = curLibPath + ":/data/vng/svn_zcypher/zcypher/bin";
//			System.setProperty("java.library.path", curLibPath);
//			curLibPath = System.getProperty("java.library.path");
//			System.out.println("System java.library.path is modified to \"" + curLibPath + "\"");
			//load libraries
//			Runtime.getRuntime().loadLibrary("ZCommonJN");
//			Runtime.getRuntime().loadLibrary("ZCypherJN");
			System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
			System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void test_zma_encode() {
		std__vectorT_std__string_t params = new std__vectorT_std__string_t();
		params.add("3804482"); //userID
		params.add("billNo"); //billNo
		params.add("itemIDs"); //itemIDs
		params.add("itemNames"); //itemNames
		params.add("1"); //quantities
		params.add("48"); //prices
		params.add("48"); //amount
		params.add("12345678"); //unix timestamp
		zcommon_StringHolder key = new zcommon_StringHolder(); //key for signing data
		key.setValue("private key of app/game");
		zcommon_StringHolder data = new zcommon_StringHolder(); //encoded data
		//encode
		int e = ZCypher.zma_encode(data, params, key, 0, 0);
		System.out.println("Result: " + e);
		String value = data.getValue();
		System.out.println("Encoded data[" + value.length() + "]: " + value);
		//decode
		std__vectorT_std__string_t params2 = new std__vectorT_std__string_t();
		e = ZCypher.zma_decode(params2, data, key, 0, 0);
		//dump result
		System.out.println("Result: " + e);
		long size = params2.size();
		System.out.println("Decoded data: " + size);
		for (long i = 0; i < size; ++i) {
			System.out.println(params2.get((int) i));
		}
	}

	public static void test_compress() {
		zcommon_StringHolder data = new zcommon_StringHolder();
		data.setValue("Với những tính năng mới cùng việc hỗ trợ thêm nhiều ngôn ngữ lập trình, IE9 được coi là niềm hy vọng của Microsoft trong việc giành lại thì phần duyệt web cũng như cạnh tranh với hai đối thủ Mozilla Firefox 4 và Google Chrome 10.");
		zcommon_StringHolder data2 = new zcommon_StringHolder();
		int e = ZCypher.zcypher_compress(data2, data, 0, 0);
		System.out.println("Result: " + e);
		String value = data2.getValue();
		System.out.println("Compressed data[" + value.length() + "]: " + value);

		zcommon_StringHolder data3 = new zcommon_StringHolder();
		e = ZCypher.zcypher_decompress(data3, data2, 0, 0);
		System.out.println("Result: " + e);
		value = data3.getValue();
		System.out.println("Decompressed data[" + value.length() + "]: " + value);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		test_zma_encode();
		test_compress();
	}
}
