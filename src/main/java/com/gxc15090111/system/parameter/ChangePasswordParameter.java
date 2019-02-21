package com.gxc15090111.system.parameter;

/**
 * 用户修改请求参数
 * @author 	gxc15090111	 2018年10月30日 上午11:49:18
 *
 */
public class ChangePasswordParameter {

	/**
	 * 项目id
	 */
	private Integer pid; 

	/**
	 * 用户id
	 */
	private Integer uid; 
	
	/**
	 * 旧密码
	 */
	private String oldPassword = "";
	
	/**
	 * 新密码
	 */
	private String newPassword = "";

	/**
	 * 确认密码
	 */
	private String newPasswordConfirm = "";
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
}
