package com.wangyu.garage.service;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 16:26
 */
public interface GarageService {

    Garage queryById(Long id);

    int save(Garage model);

    /**
     * 停车
     * @param stopRecording
     */
    void stop(StopRecording stopRecording);

}
