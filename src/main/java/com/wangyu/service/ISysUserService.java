package com.wangyu.service;

import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.UserLoginParameter;
import com.wangyu.entity.parameter.UserPageQueryParameter;
import com.wangyu.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyu.response.ModuleMenuResponse;
import com.wangyu.response.UserLoginResponse;

import java.util.List;

/**
 * <p>
 * 操作员表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 修改用户的最后登录时间
     * @param userid - 用户id
     * @return 是否修改成功信息
     */
    String updateLastlogintime(Integer userid);

    /**
     * 根据用户id查询模块菜单列表
     * @param userId- 用户id
     * @return 模块和菜单集合
     */
    List<ModuleMenuResponse> findModuleMenuByUserId(Integer userId);

    /**
     * 用户登录查询用户
     * @param userLoginParameter - 用户登录参数
     * @return SysUser
     */
    SysUser findUserForLogin(UserLoginParameter userLoginParameter);

    /**
     * 用户登录
     * @param userLoginParameter - 用户登录参数
     * @return UserLoginResponse
     */
    UserLoginResponse login(UserLoginParameter userLoginParameter);

    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return PageQueryResult
     */
    PageQueryResult findByPage(UserPageQueryParameter parameter);
}