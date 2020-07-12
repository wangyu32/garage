package com.wangyu.entity.parameter;

import com.wangyu.entity.page.PageQueryParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息查询参数
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class UserPageQueryParameter extends PageQueryParameter {
	
	/**
	 * 用户id
	 */
	private Integer id;

	/**
	 * 用户id不等于
	 */
	private Integer idNotEquals;
	
	/**
	 * 操作用户数组
	 */
	private String[] idArray;
	
	/**
	 * 用户名称
	 */
	private String logname = "";

	/**
	 * 用户名称
	 */
	private String realname = "";
	
	/**
	 * 邮箱
	 */
	private String email = "";

	/**
	 * 手机号
	 */
	private String mobilephone = "";
	
	/**
	 * 用户状态（0-正常；1-停用）
	 */
	private Integer status;
	
    /**
     * 是否管理员字段不等于
     */
    private Integer isAdminNotEquals;
    
}
