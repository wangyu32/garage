package com.gxc15090111.system.bean;

/**
 * 用户登录页输入信息
 * @author 	gxc15090111	 2018年10月31日 下午12:17:18
 *
 */
public class UserLoginBean {
	
	/**
	 * 用户名称
	 */
	private String username;
	
	/**
	 * 用户密码
	 */
	private String password;
	
	/**
	 * 验证码
	 */
	private String checkcode;
	
	/**
	 * 是否记住用户 1-记住；2-不记住
	 */
	private String recordAccount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getRecordAccount() {
		return recordAccount;
	}

	public void setRecordAccount(String recordAccount) {
		this.recordAccount = recordAccount;
	}
	
	
}
