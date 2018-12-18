package com.wangyu.prm.model;

/**
 * 角色关联用户数量Model
 * @author 	wangyu wangyu@joygo.com 2016年12月16日
 *
 */
public class RoleUserCountModel {
	
	/**
	 * 角色id
	 */
    private Integer r_id;

    /**
     * 角色名称
     */
    private String r_name;
	
	/**
	 * 角色关联用户数量
	 */
    private Integer user_count;

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public Integer getUser_count() {
		return user_count;
	}

	public void setUser_count(Integer user_count) {
		this.user_count = user_count;
	}
    
}