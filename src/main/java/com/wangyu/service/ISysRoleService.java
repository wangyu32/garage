package com.wangyu.service;

import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.RolePageQueryParameter;
import com.wangyu.model.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
