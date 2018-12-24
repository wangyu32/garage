package com.wangyu.garage.service;


import com.github.pagehelper.PageInfo;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.parameter.UserPageQueryParameter;

public interface UserService {

    /**
     * 保存用户
     * @param user
     * @return
     */
    int save(User user);

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
