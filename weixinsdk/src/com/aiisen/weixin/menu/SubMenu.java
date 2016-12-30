/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午5:11:42
 */
public class SubMenu extends BaseMenu {

	private List<BaseMenu> sub_button;

	private List<ViewMenu> viewMenuList;

	private String data_icon;

	public SubMenu() {
	}

	public SubMenu(String name) {
		super(name);
	}

	public List<BaseMenu> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<BaseMenu> sub_button) {
		this.sub_button = sub_button;
	}

	public void addSubButton(BaseMenu btn) {
		if (sub_button == null) {
			sub_button = new ArrayList<BaseMenu>(3);
		}

		if (sub_button.size() < 5) {
			sub_button.add(btn);
		}
	}

	@Override
	public String toString() {
		return "SubMenu [name=" + getName() + ", sub_button=" + sub_button + "]";
	}

	public String getData_icon() {
		return data_icon;
	}

	public void setData_icon(String data_icon) {
		this.data_icon = data_icon;
	}

	public List<ViewMenu> getViewMenuList() {
		return viewMenuList;
	}

	public void setViewMenuList(List<ViewMenu> viewMenuList) {
		this.viewMenuList = viewMenuList;
	}

}
