package com.gxc15090111.system.constant;

/**
 * 通用常量类
 * @author 	gxc15090111	 2018年10月17日 上午11:32:03
 *
 */
public class CommonConstants {


	/********************通用状态值 	开始*****************/
	/**
	 * 正常状态
	 */
	public static final int COMMON_STATUS_NORMAL = 0;
	/**
	 * 不使用状态
	 */
	public static final int COMMON_STATUS_NOT_USE = 1;
	/********************通用状态值 	结束*****************/


	/**
	 * 拼接当日最后一秒的时间
	 */
	public static final String TIME_235959 = " 23:59:59";
	
	/**
	 * 菜单树根节点名称
	 */
	public static final String MENU_ROOT_NAME = "所有菜单";
	/**
	 * 菜单树根节点id
	 */
	public static final String MENU_ROOT_ID = "-1";
	/**
	 * 菜单类型-根节点
	 */
	public static final String MENU_TYPE_ROOT = "0";
	/**
	 * 菜单类型-模块
	 */
	public static final String MENU_TYPE_MODULE = "1";
	/**
	 * 菜单类型-菜单
	 */
	public static final String MENU_TYPE_MENU = "2";

	/*	主播审核		end	*/
	
	/*	PRM(系统管理) user表参数		begin	*/
	/**
	 * 是否管理员，1表示管理员（不允许删除）
	 */
	public static final int PRM_USER_IS_ADMIN = 1;
	/*	PRM(系统管理) user表参数		end		*/

}
