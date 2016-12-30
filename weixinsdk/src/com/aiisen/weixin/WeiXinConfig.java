/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin;

/**
 * <p>
 * 微信平台配置信息
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午11:24:38
 */
public class WeiXinConfig {
	private String account;

	private String appId;

	private String appsecret;

	private String token;

	private String mchId;

	private String apiKey;

	public WeiXinConfig() {
	}

	// 配置公众平台帐号信息
	public static void init(String account, String appId, String appsecret, String token) {
		// 设置配置信息
		CommonApi.WEI_XIN_ACCOUNT = account;
		CommonApi.APP_ID = appId;
		CommonApi.APPSECRET = appsecret;
		CommonApi.TOKEN = token;
		CommonApi.ACCESS_TOKEN_URL = ApiUrlConstants.ACCESS_TOKEN + "?grant_type=client_credential&appid="
				+ CommonApi.APP_ID + "&secret=" + CommonApi.APPSECRET;

	}

	public WeiXinConfig(String account, String appId, String appsecret, String token) {
		this.account = account;
		this.appId = appId;
		this.appsecret = appsecret;
		this.token = token;

	}

	public WeiXinConfig(String account, String appId, String appsecret, String token, String mchId, String apiKey) {
		this.account = account;
		this.appId = appId;
		this.appsecret = appsecret;
		this.token = token;
		this.mchId = mchId;
		this.apiKey = apiKey;
	}

	public String getAccount() {
		return account;
	}

	public WeiXinConfig setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getAppId() {
		return appId;
	}

	public WeiXinConfig setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public WeiXinConfig setAppsecret(String appsecret) {
		this.appsecret = appsecret;
		return this;
	}

	public String getToken() {
		return token;
	}

	public WeiXinConfig setToken(String token) {
		this.token = token;
		return this;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
