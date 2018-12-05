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
     * @param phone
     * @return
     */
    User queryByPhone(String phone);

    /**
     *  通过手机号和密码查询用户
     * @param phone
     * @param password
     * @return
     */
    User queryByPhoneAndPassword(String phone, String password);

}
