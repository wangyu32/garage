package com.wangyu.system.mapper;

import java.util.List;

import com.wangyu.system.model.UserLogModel;
import com.wangyu.system.parameter.UserLogPageQueryParameter;

/**
 * 后台用户操作日志Mapper
 * @author 	wangyu@joygo.com 2017年3月8日
 *
 */
public interface UserLogModelMapper {

	/**
	 * 添加操作日志
	 * @param record
	 * @return
	 */
    int insert(UserLogModel record);

    /**
     * 分页查询总记录数
     * @param parameter - 分页查询参数
     * @return 总记录数
     */
    int findCountByPage(UserLogPageQueryParameter parameter);

    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return List
     */
    List<UserLogModel> findByPage(UserLogPageQueryParameter parameter);

}