package com.wangyu.prm.parameter;

/**
 * 用户分配角色请求参数类
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 下午7:41:28
 *
 */
public class UserRoleParameter {

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
