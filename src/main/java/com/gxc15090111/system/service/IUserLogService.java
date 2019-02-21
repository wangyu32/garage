package com.gxc15090111.system.service;

import com.gxc15090111.system.model.UserLogModel;
import com.gxc15090111.system.page.PageQueryResult;
import com.gxc15090111.system.parameter.UserLogPageQueryParameter;

/**
 * 后台用户操作日志接口
 * @author 	 2018年11月8日
 *
 */
public interface IUserLogService{

	/**
	 * 增加用户操作日志
	 * @param userModel	用户操作日志Model封装对象
	 * @return			是否增加成功的信息
	 *
	 */
	String save(UserLogModel userModel) ;

	/**
	 * 分页查询
	 * @param parameter - 分页查询参数
	 * @return PageQueryResult
	 */
	PageQueryResult findByPage(UserLogPageQueryParameter parameter);
}
