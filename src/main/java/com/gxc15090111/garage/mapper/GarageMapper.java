package com.gxc15090111.garage.mapper;

import com.gxc15090111.garage.entity.Garage;
import com.gxc15090111.garage.parameter.GaragePageQueryParameter;
import com.gxc15090111.garage.vo.GarageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 分页查询车库
     * @param parameter
     * @return
     */
    List<GarageVO> queryByParameter(GaragePageQueryParameter parameter);
}