package com.wangyu.garage.controller;

import com.wangyu.garage.common.Result;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.service.GarageService;
import com.wangyu.garage.service.StopRecordingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 15:49
 */
@Slf4j
@RestController
@RequestMapping(value = "/garage")
public class GarageController extends BaseController {

    @Autowired
    private GarageService garageService;

    @Autowired
    private StopRecordingService stopRecordingService;

    @RequestMapping(value = "/test")
    public String test(){
        return "hello world";
    }

    @GetMapping(value = "/query")
    public Result query(Long id){
        Garage garage = garageService.queryById(id);
        return success(garage);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Garage model){
        int total = model.getTotal();
        int inuse = model.getInuse() != null ? model.getInuse() : 0;
        int unuse = total - inuse;
        model.setInuse(inuse);
        model.setUnuse(unuse);
        model.setCreatetime(new Date());
        int num = garageService.save(model);
        return success(num);
    }

    /**
     * 停车
     * @param stopRecording
     * @return
     */
    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public Result stop(@RequestBody StopRecording stopRecording){
        Long garageId = stopRecording.getId() == null ? 1 : stopRecording.getId();
        stopRecording.setGarageid(garageId);//没传为默认车库
        stopRecording.setIntime(new Date());//停车时间，当前时间
        int num = stopRecordingService.save(stopRecording);
        return success(stopRecording);
    }

    /**
     * 取车离开结算
     * @param stopRecording
     * @return
     */
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public Result out(@RequestBody StopRecording stopRecording){
        Long garageId = stopRecording.getId() == null ? 1 : stopRecording.getId();
        String userId = stopRecording.getUserid().toString();

        StopRecording stopRecording1 = stopRecordingService.queryStopRecordingByUserId(userId);
        long inTime = stopRecording1.getIntime().getTime();
        Date outDate = new Date();//出库时间
        long outTime = outDate.getTime();
        long totalTime = outTime - inTime;
        Garage garage = garageService.queryById(garageId);
        BigDecimal unit = new BigDecimal(1000);
        BigDecimal price = garage.getPrice();
        BigDecimal amount = new BigDecimal(totalTime).multiply(price).divide(unit);
        stopRecording1.setAmount(amount);
        stopRecording1.setOuttime(outDate);
        stopRecording1.setTotaltime(totalTime);

        int num  = stopRecordingService.update(stopRecording1);

        return success(stopRecording1);
    }

}
