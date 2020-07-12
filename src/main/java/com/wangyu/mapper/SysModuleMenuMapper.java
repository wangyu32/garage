package com.wangyu.mapper;

import com.wangyu.entity.ModuleMenuQueryResult;
import com.wangyu.model.SysModuleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
public interface SysModuleMenuMapper extends BaseMapper<SysModuleMenu> {

    /**
     * 查询所有模块菜单列表
     * @return	List 模块菜单
     */
    List<ModuleMenuQueryResult> findAllModuleMenu();

    /**
     * 根据用户id查询模块菜单列表
     * @param userId - 用户id
     * @return 菜单及模块列表
     */
    List<ModuleMenuQueryResult> findModuleMenuByUserId(Integer userId);

    /**
     * 根据角色id查询模块菜单列表
     * @param 	roleId	角色id
     * @return	List	模块菜单
     */
    List<ModuleMenuQueryResult> findModuleMenuByRoleId(Integer roleId);
}
