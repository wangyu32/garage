package com.wangyu.garage.service;


import com.wangyu.garage.entity.User;

public interface UserService {

    /**
     * 保存用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 通过手机号查询用户
     * @param phone 手机号
     * @return
     */
    User queryByPhone(String phone);

    /**
     *  通过手机号和密码查询用户
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    User queryByPhoneAndPassword(String phone, String password);

    /**
     * 修改密码
     * @param phone 手机号码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    int changePassword(String phone, String oldPassword, String newPassword);
}
