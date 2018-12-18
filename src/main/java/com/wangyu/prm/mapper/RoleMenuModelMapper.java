package com.wangyu.prm.mapper;

import com.wangyu.prm.common.DeleteParameter;
import com.wangyu.prm.model.RoleMenuModel;

import java.util.List;

/**
 * 角色与菜单关系Mapper
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 下午8:10:58
 *
 */
public interface RoleMenuModelMapper {
	
	/**
	 * 根据角色id删除菜单
	 * @param ref_r_id - 角色id
	 * @return 删除记录数
	 */
	int deleteByRoleid(Integer ref_r_id);
	
	/**
	 * 批量删除
	 * @param parameter 删除参数
	 * @return 删除的记录数
	 */
	int deleteByRoleidArray(DeleteParameter parameter);
	
	/**
	 * 增加角色与菜单关系
	 * @param record - 角色与菜单关系
	 * @return 增加记录数
	 */
    int insert(RoleMenuModel record);
    
    /**
     * 批量插入角色与菜单关系
     * @param list - 角色与菜单关系集合
     * @return 增加记录数
     */
    int insertList(List<RoleMenuModel> list);
    


}