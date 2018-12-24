package com.wangyu.system.parameter;

/**
 * 用户登录请求参数
 * @author 	wangyu	wangyu@joygo.com 2016年10月30日 上午11:49:18
 *
 */
public class UserLoginParameter {

	/**
	 * 用户登录名
	 */
	private String logname = ""; 
	
	/**
	 * 项目域名
	 */
	private String domain = "";
	
	/**
	 * 用户密码
	 */
	private String password = "";

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
