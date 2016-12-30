/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.utils;

import java.security.MessageDigest;

/**
 * <p>
 * 消息摘要类,有md5和sha1
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午3:53:55
 */
public class DigestUtils {

	private static final String MD5_ALGORITHM = "MD5";

	private static final String SHA1_ALGORITHM = "SHA1";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * SHA1编码
	 * 
	 * @param str
	 * @return String
	 */
	public static String sha1(String str) {
		return encode(SHA1_ALGORITHM, str);
	}

	/**
	 * MD5编码
	 * 
	 * @param str
	 * @return String
	 */
	public static String md5(String str) {
		return encode(MD5_ALGORITHM, str);
	}

	/**
	 * @param algorithm
	 * @param str
	 * @return
	 */
	private static String encode(final String algorithm, final String str) {
		if (str == null)
			return null;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes("UTF-8"));
			return byte2String(messageDigest.digest());
		} catch (Exception e) {
			// throw new RuntimeException(e);
		}

		return null;
	}

	private static String byte2String(byte[] bytes) {
		int len = bytes.length;
		StringBuilder sb = new StringBuilder(len * 2);
		for (int i = 0; i < len; i++) {
			sb.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println("md5:" + md5("呵呵"));
		System.out.println("sha1:" + sha1("呵呵"));
	}

}
