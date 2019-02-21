package com.gxc15090111.system.parameter;

import com.gxc15090111.system.page.PageQueryParameter;

/**
 * 用户角色关联关系查询参数，可分页
 * @author 	gxc15090111  2018年11月07日
 *
 */
public class SysUserRolePageQueryParameter extends PageQueryParameter {
	
	/**
	 * 项目id
	 */
	private Integer ref_p_id;
	
	/**
	 * 用户id
	 */
	private Integer u_id;
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
	private Integer r_status;

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getRef_p_id() {
		return ref_p_id;
	}

	public void setRef_p_id(Integer ref_p_id) {
		this.ref_p_id = ref_p_id;
	}

	public Integer getR_status() {
		return r_status;
	}

	public void setR_status(Integer r_status) {
		this.r_status = r_status;
	}
	
}
