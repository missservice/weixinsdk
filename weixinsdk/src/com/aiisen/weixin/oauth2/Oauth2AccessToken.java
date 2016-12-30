/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.oauth2;

import com.aiisen.weixin.GlobalReturnCode;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午10:07:28
 */
public class Oauth2AccessToken extends GlobalReturnCode {

	private String access_token;

	private String refresh_token;

	private String openid;

	private String scope;

	private int expires_in;

	public Oauth2AccessToken() {
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return "Oauth2AccessToken [access_token=" + access_token + ", refresh_token=" + refresh_token + ", openid="
				+ openid + ", scope=" + scope + ", expires_in=" + expires_in + "]";
	}
}
