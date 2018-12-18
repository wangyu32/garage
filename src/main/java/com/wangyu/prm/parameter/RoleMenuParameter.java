package com.wangyu.prm.parameter;

/**
 * 角色分配菜单请求参数类
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 下午7:41:28
 *
 */
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
