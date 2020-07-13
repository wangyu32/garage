package com.wangyu.mapper;

import com.wangyu.entity.RoleUserCountModel;
import com.wangyu.entity.parameter.DeleteParameter;
import com.wangyu.model.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 操作员表 Mapper 接口
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 修改用户的最后登录时间
     * @param userid - 用户id
     * @return 修改的记录数
     */
    int updateLastlogintime(Integer userid);

    /**
     *
     * @param parameter - 查询参数
     * @return List
     */
    List<RoleUserCountModel> findRoleUserCount(DeleteParameter parameter);
}
