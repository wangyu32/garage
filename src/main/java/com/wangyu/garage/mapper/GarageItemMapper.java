package com.wangyu.garage.mapper;

import com.wangyu.garage.entity.GarageItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GarageItemMapper {
    /**
     *
     * @mbg.generated 2018-12-26
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-12-26
     */
    int insert(GarageItem record);

    /**
     *
     * @mbg.generated 2018-12-26
     */
    int insertSelective(GarageItem record);

    /**
     *
     * @mbg.generated 2018-12-26
     */
    GarageItem selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-12-26
     */
    int updateByPrimaryKeySelective(GarageItem record);

    /**
     *
     * @mbg.generated 2018-12-26
     */
    int updateByPrimaryKey(GarageItem record);

    /**
     * 获取一个随机可用的车位
     * @param garageid
     * @return
     */
    @Select("select * from garage_item where garageid = #{garageid} and status = ${@com.wangyu.garage.enums.GarageItemStatusEnum@NO_CAR.value()} limit 1")
    GarageItem getRandomAvailableGarageItem(Long garageid);

}