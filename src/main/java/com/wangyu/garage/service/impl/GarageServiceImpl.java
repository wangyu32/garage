package com.wangyu.garage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.GarageItem;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.mapper.GarageItemMapper;
import com.wangyu.garage.parameter.GaragePageQueryParameter;
import com.wangyu.garage.service.IGarageService;
import com.wangyu.garage.mapper.GarageMapper;
import com.wangyu.garage.vo.GarageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 16:27
 */
@Slf4j
@Service
public class GarageServiceImpl implements IGarageService {

    //TODO 事务配置  异常

    @Autowired
    private GarageMapper garageMapper;

    @Autowired
    private GarageItemMapper garageItemMapper;

    @Override
    public Garage getById(Long id) {
        return garageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(Garage model) {
        return garageMapper.insert(model);
    }

    @Override
    public int update(Garage model) {
        return garageMapper.updateByPrimaryKey(model);
    }

    @Override
    public void stop(StopRecording stopRecording) {

    }

    @Override
    public GarageItem getRandomAvailableGarageItem(Long garageid) {
        return garageItemMapper.getRandomAvailableGarageItem(garageid);
    }

    @Override
    public List<GarageItem> queryAllGarageItem(Long garageid) {
        return garageItemMapper.queryAllGarageItem(garageid);
    }

    @Override
    public PageInfo<GarageVO> pageQueryByParameter(GaragePageQueryParameter parameter) {
        if(parameter.isPageQuery()){
            PageHelper.startPage(parameter.getPageNumber(), parameter.getLimit());
        }
        List<GarageVO> list = this.garageMapper.queryByParameter(parameter);
        PageInfo<GarageVO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
