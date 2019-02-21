package com.gxc15090111.system.mapper;

import com.gxc15090111.system.common.DeleteParameter;
import com.gxc15090111.system.model.SysRoleMenuModel;

import java.util.List;

/**
 * 角色与菜单关系Mapper
 * @author 	gxc15090111	 2018年10月17日 下午8:10:58
 *
 */
public interface SysRoleMenuModelMapper {
	
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
    int insert(SysRoleMenuModel record);
    
    /**
     * 批量插入角色与菜单关系
     * @param list - 角色与菜单关系集合
     * @return 增加记录数
     */
    int insertList(List<SysRoleMenuModel> list);
    


}