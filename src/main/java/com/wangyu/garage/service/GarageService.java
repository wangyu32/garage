package com.wangyu.garage.service;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 16:26
 */
public interface GarageService {

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

}
