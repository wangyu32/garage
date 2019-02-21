package com.gxc15090111.garage.service;

import com.github.pagehelper.PageInfo;
import com.gxc15090111.garage.entity.Garage;
import com.gxc15090111.garage.entity.GarageItem;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.parameter.GaragePageQueryParameter;
import com.gxc15090111.garage.vo.GarageVO;

import java.util.List;

/**
 * @Description
 * @Author gxc15090111
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
