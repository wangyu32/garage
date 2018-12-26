package com.wangyu.garage.service;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.GarageItem;
import com.wangyu.garage.entity.StopRecording;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 16:26
 */
public interface IGarageService {

    /**
     * 保存车库
     * @param model
     * @return
     */
    int save(Garage model);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Garage getById(Long id);

    /**
     * 停车
     * @param stopRecording
     */
    void stop(StopRecording stopRecording);

    /**
     * 获取一个随机可用的车位
     * @param garageid
     * @return
     */
    GarageItem getRandomAvailableGarageItem(Long garageid);

    /**
     * 根据车库ID查询车位
     * @param garageid
     * @return
     */
    List<GarageItem> queryAllGarageItem(Long garageid);
}
