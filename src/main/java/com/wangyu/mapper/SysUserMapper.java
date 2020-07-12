package com.wangyu.mapper;

import com.wangyu.entity.parameter.UserLoginParameter;
import com.wangyu.model.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyu.response.UserLoginResponse;

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
}
