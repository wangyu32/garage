package com.wangyu.prm.mapper;

import com.wangyu.prm.entity.ModuleMenuQueryResult;

import java.util.List;

/**
 * 模块菜单信息Mapper
 * @author 	wangyu wangyu@joygo.com 2016年10月13日
 *
 */
public interface ModuleMenuModelMapper {
    
	/**
	 * 查询所有模块菜单列表
	 * @return	List 模块菜单
	 */
	public List<ModuleMenuQueryResult> findAllModuleMenu();

	/**
     * 根据角色id查询模块菜单列表
     * @param 	roleId	角色id
     * @return	List	模块菜单
     */
    public List<ModuleMenuQueryResult> findModuleMenuByRoleId(Integer roleId); 

    /**
     * 根据用户id查询模块菜单列表
     * @param userId - 用户id
     * @return 菜单及模块列表
     */
    public List<ModuleMenuQueryResult> findModuleMenuByUserId(Integer userId); 
    
}