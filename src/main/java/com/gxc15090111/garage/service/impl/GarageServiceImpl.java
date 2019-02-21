package com.gxc15090111.garage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxc15090111.garage.entity.Garage;
import com.gxc15090111.garage.entity.GarageItem;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.mapper.GarageItemMapper;
import com.gxc15090111.garage.parameter.GaragePageQueryParameter;
import com.gxc15090111.garage.service.IGarageService;
import com.gxc15090111.garage.mapper.GarageMapper;
import com.gxc15090111.garage.vo.GarageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author gxc15090111
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
