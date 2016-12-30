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
public class ViewMenu extends BaseMenu {

	private MenuType type;

	private String url;

	public ViewMenu() {
	}

	/**
	 * @param name 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * @param url 网页链接，用户点击菜单可打开链接，不超过256字节
	 */
	public ViewMenu(String name, String url) {
		super(name);
		this.type = MenuType.view;
		this.url = url;
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
	 * 网页链接，用户点击菜单可打开链接，不超过256字节
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 网页链接，用户点击菜单可打开链接，不超过256字节
	 * 
	 * @return
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ViewMenu [type=" + type + ", url=" + url + ", getName()=" + getName() + "]";
	}
}
