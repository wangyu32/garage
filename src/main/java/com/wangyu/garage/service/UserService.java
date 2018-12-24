package com.wangyu.garage.service;


import com.github.pagehelper.PageInfo;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.parameter.UserPageQueryParameter;
import com.wangyu.system.common.DeleteParameter;

public interface UserService {

    /**
     * 保存用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据属性修改
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 批量删除用户
     * @param parameter
     * @return
     */
    int deleteBatch(DeleteParameter parameter);

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    User getById(Long id);

    /**
     * 通过手机号查询用户
     * @param phone 手机号
     * @return
     */
    User getByPhone(String phone);

    /**
     *  通过手机号和密码查询用户
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    User getByPhoneAndPassword(String phone, String password);

    /**
     * 修改密码
     * @param phone 手机号码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    int changePassword(String phone, String oldPassword, String newPassword);

    /**
     * 分页查询用户
     * @param parameter
     * @return
     */
    PageInfo<User> pageQueryByParameter(UserPageQueryParameter parameter);

}
