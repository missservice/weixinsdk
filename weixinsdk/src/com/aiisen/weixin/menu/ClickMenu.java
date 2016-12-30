/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.menu;

import com.aiisen.weixin.type.MenuType;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午5:11:42
 */
public class ClickMenu extends BaseMenu {

	private MenuType type;

	private String key;

	public ClickMenu() {
	}

	/**
	 * @param name 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * @param key 菜单KEY值，用于消息接口推送，不超过128字节
	 */
	public ClickMenu(String name, String key) {
		super(name);
		this.type = MenuType.click;
		this.key = key;
	}

	/**
	 * 菜单的响应动作类型，目前有click、view两种类型
	 * 
	 * @return
	 */
	public MenuType getType() {
		return type;
	}

	/**
	 * 菜单的响应动作类型，目前有click、view两种类型
	 * 
	 * @return
	 */
	public void setType(String type) {
		this.type = MenuType.getMenuTypeByName(type);
	}

	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节
	 * 
	 * @return
	 */
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "ClickMenu [type=" + type + ", key=" + key + ", getName()=" + getName() + "]";
	}
}
