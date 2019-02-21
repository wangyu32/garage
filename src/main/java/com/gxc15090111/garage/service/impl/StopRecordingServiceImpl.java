package com.gxc15090111.garage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxc15090111.garage.dto.ComeinoutDTO;
import com.gxc15090111.garage.entity.*;
import com.gxc15090111.garage.enums.CarStatusEnum;
import com.gxc15090111.garage.enums.GarageItemStatusEnum;
import com.gxc15090111.garage.enums.UserEnum;
import com.gxc15090111.garage.mapper.*;
import com.gxc15090111.garage.parameter.StopRecordingQueryParameter;
import com.gxc15090111.garage.parameter.UserStopRecordingQueryParameter;
import com.gxc15090111.garage.service.IStopRecordingService;
import com.gxc15090111.garage.vo.ComeinoutVO;
import com.gxc15090111.garage.vo.UserStopRecordingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/3 23:52
 */
@Slf4j
@Service
public class StopRecordingServiceImpl implements IStopRecordingService {

    @Autowired
    private StopRecordingMapper stopRecordingMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GarageMapper garageMapper;

    @Autowired
    private GarageItemMapper garageItemMapper;

    @Autowired
    private PriceUnitMapper priceUnitMapper;

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
    public ComeinoutVO carComein(ComeinoutDTO comeinoutDto){
        Long garageId = comeinoutDto.getGarageid();
        Long userId = comeinoutDto.getUserid();

        User user = userMapper.selectByPrimaryKey(userId);

        Garage garage = garageMapper.selectByPrimaryKey(garageId);
        garage.setUnuse(garage.getUnuse() - 1);//可用车位减1
        garage.setInuse(garage.getTotal() - garage.getUnuse());
        //更新车库信息
        garageMapper.updateByPrimaryKey(garage);


        //获取一个随机可用的车位
        GarageItem garageItem = this.getRandomAvailableGarageItem(garageId);
        garageItem.setStatus(GarageItemStatusEnum.HAS_CAR.getValue());
        //更新车位信息
        garageItemMapper.updateByPrimaryKey(garageItem);


        StopRecording stopRecording = new StopRecording();
        stopRecording.setGarageid(garageId);
        stopRecording.setUserid(userId);
        stopRecording.setPhone(user.getPhone());
        stopRecording.setStatus(CarStatusEnum.COME_IN.getValue());//入库
        stopRecording.setIntime(new Date());//停车时间，当前时间
        stopRecording.setItemId(garageItem.getId());//车位信息
        stopRecording.setPriceUnitId(garage.getPriceUnitId());//计费方式

        //保存停车记录
        stopRecordingMapper.insert(stopRecording);

        Integer priceUnitId = garage.getPriceUnitId();
        PriceUnit priceUnit = priceUnitMapper.selectByPrimaryKey(priceUnitId);

        ComeinoutVO comeinoutVO = new ComeinoutVO();
        comeinoutVO.setStopRecording(stopRecording);
        comeinoutVO.setGarage(garage);
        comeinoutVO.setGarageItem(garageItem);
        comeinoutVO.setPriceUnit(priceUnit);
        return comeinoutVO;
    }

    @Override
    public ComeinoutVO queryComeInReocrd(ComeinoutDTO comeinoutDto, StopRecording stopRecording) {
        Long garageId = comeinoutDto.getGarageid();
        Garage garage = garageMapper.selectByPrimaryKey(garageId);
        GarageItem garageItem = garageItemMapper.selectByPrimaryKey(stopRecording.getItemId());
        PriceUnit priceUnit = priceUnitMapper.selectByPrimaryKey(stopRecording.getPriceUnitId());
        ComeinoutVO comeinoutVO = new ComeinoutVO();
        comeinoutVO.setStopRecording(stopRecording);
        comeinoutVO.setGarage(garage);
        comeinoutVO.setGarageItem(garageItem);
        comeinoutVO.setPriceUnit(priceUnit);
        return comeinoutVO;
    }

    @Transactional
    @Override
    public ComeinoutVO carComeout(ComeinoutDTO comeinoutDto) {
        Long garageId = comeinoutDto.getGarageid();
        Long userId = comeinoutDto.getUserid();

        User user = userMapper.selectByPrimaryKey(userId);
        Garage garage = garageMapper.selectByPrimaryKey(garageId);
        StopRecording stopRecording = stopRecordingMapper.selectByPrimaryKey(comeinoutDto.getStopRecordingId());

        long inTime = stopRecording.getIntime().getTime();
        Date outDate = new Date();//出库时间
        long outTime = outDate.getTime();
        long totalTime = (outTime - inTime) / 1000;//停车时间按秒算

        //TODO 不同类型用户可采取不同策略模式去计算钱数 未来可实现
        BigDecimal price = garage.getPrice();

        if(UserEnum.getByValue(user.getType()) == UserEnum.MEMBERSHIP){
            price = user.getPrice();//会员用户使用自定义价格
        }

        Integer priceUnitId = stopRecording.getPriceUnitId();
        PriceUnit priceUnit = priceUnitMapper.selectByPrimaryKey(priceUnitId);

        //计算停车时间和停车费
        setTotaltimeAndAmountAfterCalculate(stopRecording, totalTime, priceUnit, price);

        stopRecording.setPrice(price);
        stopRecording.setOuttime(outDate);
        stopRecording.setStatus(CarStatusEnum.COME_OUT.getValue());

        //保存停车记录
        stopRecordingMapper.updateByPrimaryKey(stopRecording);

        garage.setUnuse(garage.getUnuse() + 1);//可用车位加1
        garage.setInuse(garage.getTotal() - garage.getUnuse());//可用车位
        //更新车库信息
        garageMapper.updateByPrimaryKey(garage);

        GarageItem garageItem = garageItemMapper.selectByPrimaryKey(stopRecording.getItemId());
        garageItem.setStatus(GarageItemStatusEnum.NO_CAR.getValue());
        //更新车位信息
        garageItemMapper.updateByPrimaryKey(garageItem);

        ComeinoutVO comeinoutVO = new ComeinoutVO();
        comeinoutVO.setStopRecording(stopRecording);
        comeinoutVO.setGarage(garage);
        comeinoutVO.setGarageItem(garageItem);
        comeinoutVO.setPriceUnit(priceUnit);
        return comeinoutVO;
    }

    /**
     * 计算停车时间和停车费
     * @param stopRecording
     * @param totalTime
     * @param priceUnit
     * @param price
     */
    private void setTotaltimeAndAmountAfterCalculate(StopRecording stopRecording, long totalTime, PriceUnit priceUnit, BigDecimal price) {
        long unit = priceUnit.getUnit(); //计费单位
        BigDecimal amount = null;
        long totalTimeAfterConvert = (long)Math.ceil(totalTime * 1.0 / unit);
//        long remainder = totalTime % unit;
//        long totalTimeAfterConvert = totalTime / unit;
//        if(remainder != 0){
//            totalTimeAfterConvert++;
//        }
        amount = price.multiply(new BigDecimal(totalTimeAfterConvert));

        stopRecording.setAmount(amount);
        stopRecording.setTotaltime(totalTimeAfterConvert);
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

    @Override
    public PageInfo<UserStopRecordingVO> queryUserStopRecording(UserStopRecordingQueryParameter parameter) {
        if(parameter.isPageQuery()){
            PageHelper.startPage(parameter.getPageNumber(), parameter.getLimit());
        }
        List<UserStopRecordingVO> list = this.stopRecordingMapper.queryUserStopRecording(parameter);
        PageInfo<UserStopRecordingVO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public GarageItem getRandomAvailableGarageItem(Long garageid) {
        List<GarageItem> garageItemList = garageItemMapper.queryAllAvailableGarageItem(garageid);
        Random random = new Random();
        int randomNum = random.nextInt(garageItemList.size());
        return garageItemList.get(randomNum);
//        return garageItemMapper.getRandomAvailableGarageItem(garageid);
    }
}
