package com.wangyu.entity.parameter;

import lombok.Data;

/**
 * 角色分配菜单请求参数类
 * @author 	wangyu
 *
 */
@Data
public class RoleMenuParameter {

	/**
	 * 角色id
	 */
	private Integer roleid;
	
	/**
	 * 菜单id数组
	 */
	private String[] menuidArray;

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String[] getMenuidArray() {
		return menuidArray;
	}

	public void setMenuidArray(String[] menuidArray) {
		this.menuidArray = menuidArray;
	}
	
}
