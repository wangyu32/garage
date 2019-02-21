package com.gxc15090111.garage.mapper;

import com.gxc15090111.garage.entity.PriceUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface PriceUnitMapper {
    /**
     *
     * @mbg.generated 2018-12-27
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-12-27
     */
    int insert(PriceUnit record);

    /**
     *
     * @mbg.generated 2018-12-27
     */
    int insertSelective(PriceUnit record);

    /**
     *
     * @mbg.generated 2018-12-27
     */
    PriceUnit selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-12-27
     */
    int updateByPrimaryKeySelective(PriceUnit record);

    /**
     *
     * @mbg.generated 2018-12-27
     */
    int updateByPrimaryKey(PriceUnit record);

    @Select("select * from price_unit")
    List<PriceUnit> queryAll();
}