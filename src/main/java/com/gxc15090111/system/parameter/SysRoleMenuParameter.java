package com.gxc15090111.system.parameter;

/**
 * 角色分配菜单请求参数类
 * @author 	gxc15090111	 2018年10月17日 下午7:41:28
 *
 */
public class SysRoleMenuParameter {

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
