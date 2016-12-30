/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin;

/**
 * <p>
 * 全局返回码
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午2:55:20
 */
public class GlobalReturnCode {

	private int errcode = 0;

	private String errmsg = "ok";

	public GlobalReturnCode() {
	}

	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public GlobalReturnCode systemError() {
		this.errcode = -4;
		this.errmsg = "system error";
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GlobalReturnCode [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

}
