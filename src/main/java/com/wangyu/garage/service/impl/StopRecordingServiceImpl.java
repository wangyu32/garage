package com.wangyu.garage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangyu.garage.dto.ComeinoutDto;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.enums.CarStatusEnum;
import com.wangyu.garage.enums.UserEnum;
import com.wangyu.garage.mapper.GarageMapper;
import com.wangyu.garage.mapper.StopRecordingMapper;
import com.wangyu.garage.mapper.UserMapper;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import com.wangyu.garage.service.StopRecordingService;
import com.wangyu.garage.vo.ComeinoutVO;
import com.wangyu.garage.vo.ComeoutVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/3 23:52
 */
@Slf4j
@Service
public class StopRecordingServiceImpl implements StopRecordingService {

    @Autowired
    private StopRecordingMapper stopRecordingMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GarageMapper garageMapper;

    @Override
    public int save(StopRecording stopRecording) {
        return stopRecordingMapper.insert(stopRecording);
    }

    @Override
    public int update(StopRecording stopRecording) {
        return stopRecordingMapper.updateByPrimaryKeySelective(stopRecording);
    }

    /**
     * 入库停车
     */
    @Transactional
    @Override
    public ComeinoutVO carComein(ComeinoutDto comeinoutDto){
        Long garageId = comeinoutDto.getGarageid();
        Long userId = comeinoutDto.getUserid();

        User user = userMapper.selectByPrimaryKey(userId);
        Garage garage = garageMapper.selectByPrimaryKey(garageId);

        StopRecording stopRecording = new StopRecording();
        stopRecording.setGarageid(garageId);
        stopRecording.setUserid(userId);
        stopRecording.setPhone(user.getPhone());
        stopRecording.setStatus(CarStatusEnum.COME_IN.getValue());//入库
        stopRecording.setIntime(new Date());//停车时间，当前时间

        //保存停车记录
        stopRecordingMapper.insert(stopRecording);

        garage.setInuse(garage.getInuse() + 1);//可用车位减1
        garage.setUnuse(garage.getUnuse() - 1);//已用车位加1

        //更新车库信息
        garageMapper.updateByPrimaryKey(garage);

        ComeinoutVO comeinoutVO = new ComeinoutVO();
        comeinoutVO.setStopRecording(stopRecording);
        comeinoutVO.setGarage(garage);
        return comeinoutVO;
    }

    @Transactional
    @Override
    public ComeinoutVO carComeout(ComeinoutDto comeinoutDto) {
        Long garageId = comeinoutDto.getGarageid();
        Long userId = comeinoutDto.getUserid();

        User user = userMapper.selectByPrimaryKey(userId);
        Garage garage = garageMapper.selectByPrimaryKey(garageId);
        StopRecording stopRecording = stopRecordingMapper.selectByPrimaryKey(comeinoutDto.getStopRecordingId());

        long inTime = stopRecording.getIntime().getTime();
        Date outDate = new Date();//出库时间
        long outTime = outDate.getTime();
        long totalTime = (outTime - inTime) / 1000 * 1000;//停车时间去掉毫秒部分,按秒计算

        //TODO 不同类型用户可采取不同策略模式去计算钱数 未来可实现
        BigDecimal price = garage.getPrice();

        if(UserEnum.getByCode(user.getType()) == UserEnum.MEMBERSHIP){
            price = user.getPrice();//会员用户使用自定义价格
        }

        BigDecimal unit = new BigDecimal(1000);
        BigDecimal amount = new BigDecimal(totalTime).multiply(price).divide(unit);//按秒算钱
        stopRecording.setAmount(amount);
        stopRecording.setOuttime(outDate);
        stopRecording.setTotaltime(totalTime);
        stopRecording.setStatus(CarStatusEnum.COME_OUT.getValue());

        //保存停车记录
        stopRecordingMapper.insert(stopRecording);

        garage.setInuse(garage.getInuse() + 1);//可用车位减1
        garage.setUnuse(garage.getUnuse() - 1);//已用车位加1
        //更新车库信息
        garageMapper.updateByPrimaryKey(garage);

        ComeinoutVO comeinoutVO = new ComeinoutVO();
        comeinoutVO.setStopRecording(stopRecording);
        comeinoutVO.setGarage(garage);
        return comeinoutVO;
    }

    @Override
    public StopRecording queryStopRecordingByUserId(String userId) {
        return stopRecordingMapper.queryStopRecordingByUserId(userId);
    }

    @Override
    public List<StopRecording> queryByParameter(StopRecordingQueryParameter stopRecordingQueryParameter) {
        return stopRecordingMapper.queryByParameter(stopRecordingQueryParameter);
    }

    @Override
    public PageInfo<StopRecording> pageQueryByParameter(StopRecordingQueryParameter stopRecordingQueryParameter) {
        PageHelper.startPage(stopRecordingQueryParameter.getPageNumber(), stopRecordingQueryParameter.getLimit());
        List<StopRecording> list = this.stopRecordingMapper.queryByParameter(stopRecordingQueryParameter);
        PageInfo<StopRecording> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
