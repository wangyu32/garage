package com.wangyu.garage.mapper;

import com.wangyu.garage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    User selectByPrimaryKey(Long id);

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

    /**
     * 修改密码
     * @param phone 手机号码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Update("update user set password = #{arg2} where phone = #{arg0} and password = #{arg1}")
    int updateUserPassword(String phone, String oldPassword, String newPassword);
}