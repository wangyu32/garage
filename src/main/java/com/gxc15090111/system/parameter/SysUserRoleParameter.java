package com.gxc15090111.system.parameter;

/**
 * 用户分配角色请求参数类
 * @author 	gxc15090111	 2018年10月17日 下午7:41:28
 *
 */
public class SysUserRoleParameter {

	/**
	 * 用户id
	 */
	private Integer userid;
	
	/**
	 * 角色id,使用逗号分隔
	 */
	private String roleids;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	
}
