package com.wangyu.service;

import com.wangyu.entity.RoleUserCountModel;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.DeleteParameter;
import com.wangyu.entity.parameter.RolePageQueryParameter;
import com.wangyu.entity.parameter.UserPageQueryParameter;
import com.wangyu.entity.parameter.UserRolePageQueryParameter;
import com.wangyu.model.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyu.response.UserRoleCheckedVOListResponse;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return PageQueryResult
     */
    PageQueryResult findByPage(RolePageQueryParameter parameter);

    /**
     * 查询同一项目下角色名称的个数（主要用来校验角色名称，防止名称重复）
     * @param pageQueryParameter - 查询参数
     * @return 记录数
     */
    int findCountOfRname(RolePageQueryParameter pageQueryParameter);

    /**
     * 增加角色信息及关联菜单
     * @param model	角色信息Model及关联菜单ID数组
     * @return 是否增加成功的信息
     */
    String insertRoleAndRoleMenu(SysRole model);

    /**
     * 修改角色信息
     * @param model	角色信息Model及关联菜单ID数组
     * @return 是否修改成功的信息
     */
    String updateRoleAndRoleMenu(SysRole model);

    /**
     * 根据角色Id数组查询关联的用户个数
     * @param parameter - 查询参数
     * @return List
     */
    List<RoleUserCountModel> findRoleUserCount(DeleteParameter parameter);

    /**
     * 查询用户关联角色在所有角色中的选择情况
     * @param parameter 查询参数
     * @return UserRoleCheckedVOListResponse
     */
    UserRoleCheckedVOListResponse queryUserRoleChecked(UserRolePageQueryParameter parameter);
}
