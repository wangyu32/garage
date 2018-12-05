package com.wangyu.garage.service.impl;

import com.wangyu.garage.entity.User;
import com.wangyu.garage.mapper.UserMapper;
import com.wangyu.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/4 23:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User queryByPhone(String phone) {
        return userMapper.queryByPhone(phone);
    }

    @Override
    public User queryByPhoneAndPassword(String phone, String password) {
        return userMapper.queryByPhoneAndPassword(phone, password);
    }
}
