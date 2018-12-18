package com.wangyu.prm.parameter;

import com.wangyu.prm.page.PageQueryParameter;

/**
 * 角色信息查询参数
 * @author 	wangyu wangyu@joygo.com 2016年10月16日
 *
 */
public class RolePageQueryParameter extends PageQueryParameter {
	
	/**
	 * 项目id
	 */
	private Integer ref_p_id;

	/**
	 * 角色id
	 */
	private Integer r_id;
	
	/**
	 * 角色名称
	 */
	private String r_name = "";
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
	private Integer r_status;

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}



	public Integer getRef_p_id() {
		return ref_p_id;
	}

	public void setRef_p_id(Integer ref_p_id) {
		this.ref_p_id = ref_p_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public Integer getR_status() {
		return r_status;
	}

	public void setR_status(Integer r_status) {
		this.r_status = r_status;
	}

}
