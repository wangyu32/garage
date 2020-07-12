package com.wangyu.util;

import java.security.MessageDigest;

/** 
* @author 作者 jinweida
* @version 创建时间：2016年10月11日 下午6:24:07
*/
public class Md5Util {
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 获取MD5串
	 * @param origin 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String MD5Encode(String origin) {
		return MD5Encode(origin, null);
	}
	
	/**
	 * 获取MD5串
	 * @param origin 要加密的字符串
	 * @param charsetname 编码，默认是UTF-8
	 * @return 加密后的字符串
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
 