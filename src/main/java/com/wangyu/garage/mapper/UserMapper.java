package com.wangyu.garage.mapper;

import com.wangyu.garage.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     *
     * @mbg.generated 2018-12-04
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-12-04
     */
    int insert(User record);

    /**
     *
     * @mbg.generated 2018-12-04
     */
    int insertSelective(User record);

    /**
     *
     * @mbg.generated 2018-12-04
     */
    User selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-12-04
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbg.generated 2018-12-04
     */
    int updateByPrimaryKey(User record);

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