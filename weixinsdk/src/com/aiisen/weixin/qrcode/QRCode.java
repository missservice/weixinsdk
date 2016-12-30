/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.qrcode;

import com.aiisen.weixin.GlobalReturnCode;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午1:11:04
 */
public class QRCode extends GlobalReturnCode {

	private String ticket;

	private int expire_seconds;

	private String url;

	public QRCode() {
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "QRCode [ticket=" + ticket + ", expire_seconds=" + expire_seconds + ", url=" + url + ", getErrcode()="
				+ getErrcode() + ", getErrmsg()=" + getErrmsg() + "]";
	}
}
