package com.wangyu.garage.service;

import com.github.pagehelper.PageInfo;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.GarageItem;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.parameter.GaragePageQueryParameter;
import com.wangyu.garage.vo.GarageVO;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 16:26
 */
public interface IGarageService {

    /**
     * 添加车库
     * @param model
     * @return
     */
    int save(Garage model);

    /**
     * 修改车库
     * @param model
     * @return
     */
    int update(Garage model);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Garage getById(Long id);

    /**
     * 根据车库ID查询车位
     * @param garageid
     * @return
     */
    List<GarageItem> queryAllGarageItem(Long garageid);

    /**
     * 分页查询车库
     * @param parameter
     * @return
     */
    PageInfo<GarageVO> pageQueryByParameter(GaragePageQueryParameter parameter);
}
