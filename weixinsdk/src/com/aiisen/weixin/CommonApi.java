/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aiisen.weixin.httpclient.HttpClientUtils;
import com.aiisen.weixin.type.MediaType;
import com.aiisen.weixin.utils.DigestUtils;
import com.aiisen.weixin.utils.StringUtils;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 微信公共API
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午3:44:08
 */
public class CommonApi {

	private static final Log log = LogFactory.getLog(CommonApi.class);

	private static final String SPLIT = "";

	public static String WEI_XIN_ACCOUNT;

	public static String APP_ID;

	public static String APPSECRET;

	public static String TOKEN;

	public static String ACCESS_TOKEN_URL;

	private static long expiresIn = 7100 * 1000;

	private static String accessToken = null;

	public static String jsapi_ticket = null;

	private static long tokenSecond = System.currentTimeMillis();

	private static long tokenSecondJs = System.currentTimeMillis();

	static {
		// String classPath = WeiXinCommonApi.class.getResource("/").getPath();
		// String log4jDir = classPath.substring(0, classPath.indexOf("classes")) + "logs";
		//
		// System.setProperty("log4jDir", log4jDir);
		//
		// String log4jPath = classPath + "log4j.xml";
		//
		// DOMConfigurator.configure(log4jPath);

		Properties prop = new Properties();
		try {
			prop.load(CommonApi.class.getResource("/pay.properties").openStream());

			// 配置公众平台帐号信息
			WeiXinConfig.init(prop.getProperty("WEI_XIN_ACCOUNT"), prop.getProperty("APP_ID"),
					prop.getProperty("APPSECRET"), prop.getProperty("TOKEN"));

			// WEI_XIN_ACCOUNT = prop.getProperty("WEI_XIN_ACCOUNT");
			// APP_ID = prop.getProperty("APP_ID");
			// APPSECRET = prop.getProperty("APPSECRET");
			// TOKEN = prop.getProperty("TOKEN");
			// ACCESS_TOKEN_URL = ApiUrlConstants.ACCESS_TOKEN + "?grant_type=client_credential&appid=" + APP_ID
			// + "&secret=" + APPSECRET;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 验证微信签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {

		if ((signature == null) || (timestamp == null) || (nonce == null)) {
			return false;
		}

		String[] params = { TOKEN, timestamp, nonce };

		Arrays.sort(params);

		String str = StringUtils.arrToString(params, SPLIT);

		str = DigestUtils.sha1(str);

		if (signature.equals(str)) {
			return true;
		}

		return false;
	}

	public static synchronized String getAccessToken() {
		long now = System.currentTimeMillis();

		if ((accessToken == null) || ((now - tokenSecond) > expiresIn)) {
			JSONObject data = HttpClientUtils.getForJsonResult(ACCESS_TOKEN_URL);

			if (data != null) {
				if (data.containsKey("access_token")) {
					accessToken = data.getString("access_token");
					expiresIn = (data.getIntValue("expires_in") - 60) * 1000;
				} else {
					log.info("getAccessToken error:" + data);
				}
				tokenSecond = now;
			}
		}

		return accessToken;
	}

	/**
	 * 公众号在使用接口时，对多媒体文件、多媒体消息的获取和调用等操作，是通过media_id来进行的。通过本接口，公众号可以上传或下载多媒体文件。但请注意，每个多媒体文件（media_id）会在上传、
	 * 用户发送到微信服务器3天后自动删除，以节省服务器资源。
	 * 公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务器会返回对应的media_id，公众号此后可根据该media_id来获取多媒体。请注意，media_id是可复用的，调用该接口需http协议。
	 * 
	 * @param json
	 * @return
	 */
	public static String mediaUpload(MediaType type, File media) {
		String url = ApiUrlConstants.MEDIA_UPLOAD + "?access_token=" + CommonApi.getAccessToken() + "&type=" + type;
		String resp = HttpClientUtils.postWeiXinMedia(url, media);

		if (resp != null) {
			try {
				JSONObject rs = JSONObject.parseObject(resp);

				if (rs.containsKey("media_id")) {
					String mediaId = rs.getString("media_id");
					log.info("Media upload success! mediaId:" + mediaId);
					return mediaId;
				} else {
					log.info("userInfo error:" + resp);
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public static String time = null;

	/**
	 * 调用微信JS接口的临时票据
	 * 
	 * @param access_token 接口访问凭证
	 * @return
	 */
	public static String getJsApiTicket() {
		long now = System.currentTimeMillis();
		if ((jsapi_ticket == null) || ((now - tokenSecondJs) > expiresIn)) {
			String access_token = CommonApi.getAccessToken();
			// log.info("==access_token:" + access_token);
			String url = ApiUrlConstants.JS_API_TICKET + "?access_token=" + access_token + "&type=jsapi";
			JSONObject data = HttpClientUtils.getForJsonResult(url);

			if (data != null) {
				if (data.containsKey("ticket")) {
					jsapi_ticket = data.getString("ticket");
					expiresIn = (data.getIntValue("expires_in") - 60) * 1000;
				} else {
					log.info("getticket error:" + data);
				}
				tokenSecondJs = now;
			}
		}
		// log.info("==jsapi_ticket:" + jsapi_ticket);
		return jsapi_ticket;
	}

	/**
	 * @param appId 公账号appId
	 * @param appSecret
	 * @param url 当前网页的URL，不包含#及其后面部分
	 * @return
	 */
	public static JSONObject getJsApiTicketRetrunParam(String url) {
		jsapi_ticket = CommonApi.getJsApiTicket();

		Map<String, String> params = sign(jsapi_ticket, url);
		params.put("appid", APP_ID);

		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(params);
		// String jsonStr = jsonObject.toString();

		System.out.println("==jsonObject.toString():" + jsonObject.toString());
		return jsonObject;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String str;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	// 获取当前系统时间 用来判断access_token是否过期
	public static String getTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}
}
