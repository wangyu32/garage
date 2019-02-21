package com.gxc15090111.system.mapper;

import com.gxc15090111.system.common.DeleteParameter;
import com.gxc15090111.system.model.SysUserRoleModel;

import java.util.List;

/**
 * 用户与角色关系Mapper
 * @author 	wangyu	wangyu@joygo.com 2016年10月18日 上午10:24:17
 *
 */
public interface SysUserRoleModelMapper {

	/**
	 * 增加用户与角色的关系
	 * @param record - 用户与角色的关系
	 * @return 增加记录数
	 */
    int insert(SysUserRoleModel record);

    /**
     * 根据用户id删除用户与角色的关系
     * @param ref_u_id - 用户id
     * @return 删除记录数
     */
	int deleteByUserid(Integer ref_u_id);
	
	/**
	 * 根据用户ID数组批量删除用户与角色关联关系
	 * @param parameter - 删除参数
	 * @return 删除记录数
	 */
	int deleteBatchByRefUid(DeleteParameter parameter);

	/**
	 * 批量插入用户与角色的关系
	 * @param list - 用户与角色的关系集合
	 * @return 增加记录数
	 */
	int insertList(List<SysUserRoleModel> list);
}