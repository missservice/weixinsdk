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
 * @date 2014年4月18日 下午4:08:09
 */
public enum MediaType {

	image, voice, video, thumb;

	public static MediaType getMediaTypeByName(String name) {

		for (MediaType type : values()) {
			if (type.name().equals(name))
				return type;
		}
		return null;
	}
}
