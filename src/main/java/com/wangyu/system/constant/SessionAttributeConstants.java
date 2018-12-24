package com.wangyu.system.constant;

/**
 * session内缓存内容key值的常量类
 * @author 	wangyu@joygo.com 2017年3月9日
 *
 */
public class SessionAttributeConstants {

	/**
	 * 验证码
	 */
	public static final String LOGINCHECKCODE = "LOGINCHECKCODE";
	
	/**
	 * 当前session里的用户存储的key值
	 */
	public static final String CURRENT_USER = "CURRENT_USER";

	/**
	 * 当前session里的当前APP存储的key值
	 */
	public static final String CURRENT_APP = "CURRENT_APP";
	
	/**
	 * 后台角色修改时缓存model的key值前缀
	 */
	public static final String SYS_ROLE_ = "sysrole_";
	
	/**
	 * 后台用户修改时缓存model的key值前缀
	 */
	public static final String SYS_USER_ = "sysuser_";


    /**
     * 车库用户
     */
	public static final String USER_ = "user_";


}
