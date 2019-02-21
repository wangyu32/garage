package com.gxc15090111.system.parameter;


import com.gxc15090111.system.page.PageQueryParameter;

/**
 * 用户信息查询参数
 * @author 	gxc15090111  2018年10月16日
 *
 */
public class SysUserPageQueryParameter extends PageQueryParameter {
	
	/**
	 * 项目id
	 */
	private Integer ref_p_id;
	
	/**
	 * 用户id
	 */
	private Integer u_id;

	/**
	 * 用户id不等于
	 */
	private Integer u_id_not_equals;
	
	/**
	 * 操作用户数组
	 */
	private String[] ref_u_id_array;
	
	/**
	 * 用户名称
	 */
	private String u_logname = "";

	/**
	 * 用户名称
	 */
	private String u_realname = "";
	
	/**
	 * 邮箱
	 */
	private String u_email = "";

	/**
	 * 手机号
	 */
	private String u_mobilephone = "";
	
	/**
	 * 用户状态（0-正常；1-停用）
	 */
	private Integer u_status;
	
    /**
     * 是否管理员字段不等于
     */
    private Integer u_isadmin_not_equals;
    
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

	public String getU_logname() {
		return u_logname;
	}

	public void setU_logname(String u_logname) {
		this.u_logname = u_logname;
	}

	public String getU_realname() {
		return u_realname;
	}

	public void setU_realname(String u_realname) {
		this.u_realname = u_realname;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_mobilephone() {
		return u_mobilephone;
	}

	public void setU_mobilephone(String u_mobilephone) {
		this.u_mobilephone = u_mobilephone;
	}

	public Integer getU_status() {
		return u_status;
	}

	public void setU_status(Integer u_status) {
		this.u_status = u_status;
	}

	public String[] getRef_u_id_array() {
		return ref_u_id_array;
	}

	public void setRef_u_id_array(String[] ref_u_id_array) {
		this.ref_u_id_array = ref_u_id_array;
	}

	public Integer getU_id_not_equals() {
		return u_id_not_equals;
	}

	public void setU_id_not_equals(Integer u_id_not_equals) {
		this.u_id_not_equals = u_id_not_equals;
	}

	public Integer getU_isadmin_not_equals() {
		return u_isadmin_not_equals;
	}

	public void setU_isadmin_not_equals(Integer u_isadmin_not_equals) {
		this.u_isadmin_not_equals = u_isadmin_not_equals;
	}
	
}
