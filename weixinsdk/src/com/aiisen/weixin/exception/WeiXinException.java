/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.exception;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午10:35:26
 */
public class WeiXinException extends Exception {

	static final long serialVersionUID = -1634447102016222968L;

	/**
	 * 
	 */
	public WeiXinException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WeiXinException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public WeiXinException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WeiXinException(Throwable cause) {
		super(cause);
	}
}
