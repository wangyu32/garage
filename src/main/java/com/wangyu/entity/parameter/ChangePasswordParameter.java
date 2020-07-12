package com.wangyu.entity.parameter;

import lombok.Data;

/**
 * 用户修改请求参数
 * @author 	wangyu
 *
 */
@Data
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

}
