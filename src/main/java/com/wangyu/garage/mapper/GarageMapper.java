package com.wangyu.garage.mapper;

import com.wangyu.garage.entity.Garage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GarageMapper {
    /**
     *
     * @mbg.generated 2018-12-02
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-12-02
     */
    int insert(Garage record);

    /**
     *
     * @mbg.generated 2018-12-02
     */
    int insertSelective(Garage record);

    /**
     *
     * @mbg.generated 2018-12-02
     */
    Garage selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2018-12-02
     */
    int updateByPrimaryKeySelective(Garage record);

    /**
     *
     * @mbg.generated 2018-12-02
     */
    int updateByPrimaryKey(Garage record);
}