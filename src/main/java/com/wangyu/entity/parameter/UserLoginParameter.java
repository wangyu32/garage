package com.wangyu.entity.parameter;

import lombok.Data;

/**
 * 用户登录请求参数
 * @author 	wangyu
 *
 */
@Data
public class UserLoginParameter {

	/**
	 * 用户登录名
	 */
	private String logname = ""; 
	
	/**
	 * 用户密码
	 */
	private String password = "";

}
