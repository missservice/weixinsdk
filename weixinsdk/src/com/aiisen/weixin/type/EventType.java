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
 * @date 2014年4月18日 下午5:15:20
 */
public enum EventType {

	subscribe, unsubscribe, SCAN, LOCATION, CLICK, VIEW;

	public static EventType getEventTypeByName(String name) {

		for (EventType type : values()) {
			if (type.name().equals(name))
				return type;
		}
		return null;
	}
}
