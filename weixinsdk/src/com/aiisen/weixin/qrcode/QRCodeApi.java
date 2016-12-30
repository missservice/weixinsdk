/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.qrcode;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aiisen.weixin.ApiUrlConstants;
import com.aiisen.weixin.CommonApi;
import com.aiisen.weixin.httpclient.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 生成带参数的二维码
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午6:24:16
 */
public class QRCodeApi {

	private static final Log log = LogFactory.getLog(QRCodeApi.class);

	private static final String QR_SCENE = "QR_SCENE"; // 临时

	private static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE"; // 永久

	private QRCodeApi() {
	}

	/**
	 * 生成永久二维码 无过期时间，数量较少（目前参数只支持1--100000）
	 * 
	 * @param sceneId 非0整型，最大值为100000（目前参数只支持1--100000）
	 * @return QRCode
	 */
	public static QRCode createQRCode(int sceneId) {
		return createQRCode(QR_LIMIT_SCENE, sceneId, 0);
	}

	/**
	 * 生成临时二维码 最大为1800秒，但能够生成较多数量
	 * 
	 * @param sceneId 32位非0整型
	 * @param expireSeconds 最大为1800秒
	 * @return QRCode
	 */
	public static QRCode createTempQRCode(int sceneId, int expireSeconds) {
		return createQRCode(QR_SCENE, sceneId, expireSeconds);
	}

	public static String showQRCode(String ticket) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ticket", ticket);

		String url = HttpClientUtils.buildReqUrl(ApiUrlConstants.QRCODE_SHOW, params);
		return url;
	}

	private static QRCode createQRCode(String actionName, int sceneId, int expireSeconds) {
		JSONObject req = new JSONObject();
		JSONObject sub = new JSONObject();
		JSONObject thr = new JSONObject();

		thr.put("scene_id", sceneId);
		sub.put("scene", thr);

		if (expireSeconds != 0) {
			req.put("expire_seconds", expireSeconds);
		}

		req.put("action_name", actionName);
		req.put("action_info", sub);

		String url = ApiUrlConstants.QRCODE_CREATE + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		QRCode qrc = new QRCode();

		if (resp != null) {
			try {
				qrc = JSONObject.toJavaObject(resp, QRCode.class);
				if ((qrc != null) && (qrc.getTicket() != null)) {
					qrc.setUrl(showQRCode(qrc.getTicket()));
				}
				return qrc;
			} catch (Exception e) {
				qrc.systemError();
				log.error(e.getMessage(), e);
			}
		} else {
			qrc.systemError();
		}
		return qrc;
	}
}
