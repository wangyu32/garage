package com.gxc15090111.garage.mapper;

import com.gxc15090111.garage.entity.GarageItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * 获取第一个可用的车位
     * @param garageid
     * @return
     */
    @Select("select * from garage_item where garageid = #{garageid} and status = ${@com.gxc15090111.garage.enums.GarageItemStatusEnum@NO_CAR.value()} limit 1")
    GarageItem getFirstAvailableGarageItem(Long garageid);

    /**
     * 查询所有空闲车位
     * @param garageid
     * @return
     */
    @Select("select * from garage_item where garageid = #{garageid} and status = ${@com.gxc15090111.garage.enums.GarageItemStatusEnum@NO_CAR.value()} ")
    List<GarageItem> queryAllAvailableGarageItem(Long garageid);

    /**
     * 根据车库ID查询车位
     * @param garageid
     * @return
     */
    @Select("select * from garage_item where garageid = #{garageid}")
    List<GarageItem> queryAllGarageItem(Long garageid);
}