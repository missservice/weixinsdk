/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.utils;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午2:43:31
 */
public class StringUtils {

	/**
	 * @param t
	 * @param split
	 * @return
	 */
	public static <T> String arrToString(T[] t, String split) {
		if (t == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (T t2 : t) {
			sb.append(t2).append(split);
		}
		String str = sb.toString();

		if (!"".equals(split))
			str = str.substring(0, str.length() - 1);

		return str;
	}

	/**
	 * @param t
	 * @return
	 */
	public static <T> String arrToString(T[] t) {
		return arrToString(t, ",");
	}

	public static void main(String[] args) {
		System.out.println(arrToString(new String[] { "aa", "bb" }, ""));
	}
}
