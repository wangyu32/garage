package com.wangyu.garage.service.impl;

import com.wangyu.garage.entity.PriceUnit;
import com.wangyu.garage.mapper.PriceUnitMapper;
import com.wangyu.garage.service.IPriceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/27 20:51
 */
@Service
public class PriceUnitServiceImpl implements IPriceUnitService {

    @Autowired
    private PriceUnitMapper priceUnitMapper;

    @Override
    public List<PriceUnit> queryAll() {
        return priceUnitMapper.queryAll();
    }
}
