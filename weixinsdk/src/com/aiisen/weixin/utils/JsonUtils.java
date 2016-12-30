/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.utils;

import com.aiisen.weixin.GlobalReturnCode;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午11:02:32
 */
public class JsonUtils {

	public static String getString(JSONObject json, String key) {
		if ((json == null) || (key == null)) {
			return null;
		}

		try {
			return json.getString(key);
		} catch (JSONException e) {
		}
		return null;
	}

	public static int getInt(JSONObject json, String key) {
		return getInt(json, key, -Integer.MAX_VALUE);
	}

	public static int getInt(JSONObject json, String key, int defaultValue) {
		int rs = defaultValue;

		if ((json != null) && (key != null)) {

			try {
				rs = json.getIntValue(key);
			} catch (JSONException e) {
			}
		}
		return rs;
	}

	public static boolean getBoolean(JSONObject json, String key) {
		return getBoolean(json, key, false);
	}

	public static boolean getBoolean(JSONObject json, String key, boolean defaultValue) {
		boolean rs = defaultValue;

		if ((json != null) && (key != null)) {
			try {
				rs = json.getBoolean(key);
			} catch (JSONException e) {
			}
		}
		return rs;
	}

	public static GlobalReturnCode buildSendResult(JSONObject resp) {
		GlobalReturnCode gc = new GlobalReturnCode();
		if ((resp != null) && resp.containsKey("errcode")) {
			try {
				gc = JSONObject.toJavaObject(resp, GlobalReturnCode.class);
			} catch (Exception e) {
				gc.systemError();
			}
		} else {
			gc.systemError();
		}
		return gc;
	}

	@SuppressWarnings("unchecked")
	public static <T extends GlobalReturnCode> T buildRequestResult(JSONObject resp, Class<T> t) {
		try {
			T o = t.newInstance();
			if (resp != null) {
				try {
					o = JSONObject.toJavaObject(resp, t);
				} catch (Exception e) {
					o.systemError();
				}
			} else {
				o.systemError();
			}
			return o;
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
