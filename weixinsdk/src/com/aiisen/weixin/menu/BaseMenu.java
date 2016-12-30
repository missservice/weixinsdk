/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.menu;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午5:11:42
 */
public class BaseMenu {

	private String name;

	public BaseMenu() {
	}

	/**
	 * @param name 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	public BaseMenu(String name) {
		this.name = name;
	}

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + "]";
	}

}
