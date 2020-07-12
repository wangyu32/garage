package com.wangyu.service;

import com.wangyu.model.SysModuleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyu.response.ModuleMenuResponse;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
public interface ISysModuleMenuService extends IService<SysModuleMenu> {

    /**
     * 查询所有模块菜单列表
     * @return  List - 角色菜单集合
     */
    List<ModuleMenuResponse> findAllModuleMenu();

    /**
     * 根据角色id查询模块菜单列表
     * @param roleId- 角色id
     * @return  List - 角色菜单集合
     */
    List<ModuleMenuResponse> findModuleMenuByRoleId(Integer roleId);

}
