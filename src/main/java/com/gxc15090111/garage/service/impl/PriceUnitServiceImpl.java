package com.gxc15090111.garage.service.impl;

import com.gxc15090111.garage.entity.PriceUnit;
import com.gxc15090111.garage.mapper.PriceUnitMapper;
import com.gxc15090111.garage.service.IPriceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author gxc15090111
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
