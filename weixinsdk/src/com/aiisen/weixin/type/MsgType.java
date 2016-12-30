/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.type;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午10:03:39
 */
public enum MsgType {

	text, image, voice, video, location, link, event, music, news;

	public static MsgType getMsgTypeByName(String name) {

		for (MsgType type : values()) {
			if (type.name().equals(name))
				return type;
		}
		return null;
	}
}
