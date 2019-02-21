package com.gxc15090111.garage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxc15090111.garage.entity.User;
import com.gxc15090111.garage.mapper.UserMapper;
import com.gxc15090111.garage.parameter.UserPageQueryParameter;
import com.gxc15090111.garage.service.IUserService;
import com.gxc15090111.system.common.DeleteParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/4 23:48
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteBatch(DeleteParameter parameter) {
        return userMapper.deleteBatch(parameter);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getByPhone(String phone) {
        return userMapper.queryByPhone(phone);
    }

    @Override
    public User getByPhoneAndPassword(String phone, String password) {
        return userMapper.queryByPhoneAndPassword(phone, password);
    }

    @Override
    public int changePassword(String phone, String oldPassword, String newPassword) {
        return userMapper.updateUserPassword(phone, oldPassword, newPassword);
    }

    @Override
    public PageInfo<User> pageQueryByParameter(UserPageQueryParameter parameter) {
        if(parameter.isPageQuery()){
            PageHelper.startPage(parameter.getPageNumber(), parameter.getLimit());
        }
        List<User> list = this.userMapper.queryByParameter(parameter);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
