package com.wangyu.prm.service;

import com.wangyu.prm.model.UserLogModel;
import com.wangyu.prm.page.PageQueryResult;
import com.wangyu.prm.parameter.UserLogPageQueryParameter;

/**
 * 后台用户操作日志接口
 * @author 	wangyu@joygo.com 2017年3月8日
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
