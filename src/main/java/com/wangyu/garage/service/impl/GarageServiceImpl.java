package com.wangyu.garage.service.impl;

import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.service.GarageService;
import com.wangyu.garage.mapper.GarageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 16:27
 */
@Slf4j
@Service
public class GarageServiceImpl implements GarageService {

    //TODO 事务配置  异常

    @Autowired
    private GarageMapper garageMapper;

    @Value("")
    private String value;

    @Override
    public Garage queryById(Long id) {
        return garageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(Garage model) {
        return garageMapper.insert(model);
    }

    @Override
    public void stop(StopRecording stopRecording) {

    }
}
