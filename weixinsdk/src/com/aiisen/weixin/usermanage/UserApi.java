/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.usermanage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aiisen.weixin.ApiUrlConstants;
import com.aiisen.weixin.CommonApi;
import com.aiisen.weixin.httpclient.HttpClientUtils;
import com.aiisen.weixin.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午1:24:40
 */
public class UserApi {

	private static final Log log = LogFactory.getLog(UserApi.class);

	private UserApi() {
	}

	/**
	 * 获取用户基本信息
	 * 
	 * @param openId 普通用户的标识，对当前公众号唯一
	 * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	public static User userInfo(String openId, String lang) {
		String url = ApiUrlConstants.USER_INFO + "?access_token=" + CommonApi.getAccessToken() + "&openid=" + openId;

		if (lang != null) {
			url += "&lang=" + lang;
		}

		JSONObject resp = HttpClientUtils.getForJsonResult(url);

		return JsonUtils.buildRequestResult(resp, User.class);
	}

	/**
	 * 获取关注者列表 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，
	 * 可以通过多次拉取的方式来满足需求。 当公众号关注者数量超过10000时，可通过填写next_openid的值，从而多次拉取列表的方式来满足需求。具体而言，就是在调用接口时，将上一次调用得到的返回中的next_openid值，
	 * 作为下一次调用中的next_openid值。
	 * 
	 * @param nextOpenId
	 * @return
	 */
	public static SubscribeList userGet(String nextOpenId) {
		if (nextOpenId == null) {
			nextOpenId = "";
		}

		String url = ApiUrlConstants.USER_GET + "?access_token=" + CommonApi.getAccessToken() + "&next_openid="
				+ nextOpenId;

		JSONObject resp = HttpClientUtils.getForJsonResult(url);

		SubscribeList list = new SubscribeList();

		if (resp != null) {
			if (resp.containsKey("total")) {
				try {
					list.setTotal(resp.getIntValue("total"));
					list.setCount(resp.getIntValue("count"));
					list.setNextOpenId(resp.getString("next_openid"));

					if (list.getCount() > 0) {
						JSONArray array = resp.getJSONObject("data").getJSONArray("openid");
						for (int i = 0; i < array.size(); i++) {
							list.addOpenId(array.getString(i));
						}
					}
				} catch (JSONException e) {
					list.systemError();
					log.error(e.getMessage(), e);
				}
			} else {
				list = JsonUtils.buildRequestResult(resp, SubscribeList.class);
			}
		} else {
			list.systemError();
		}

		return list;
	}

}
