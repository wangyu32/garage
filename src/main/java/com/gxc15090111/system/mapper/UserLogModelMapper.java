package com.gxc15090111.system.mapper;

import java.util.List;

import com.gxc15090111.system.model.UserLogModel;
import com.gxc15090111.system.parameter.UserLogPageQueryParameter;

/**
 * 后台用户操作日志Mapper
 * @author 	 2018年11月8日
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