package com.wangyu.garage.controller;

import com.github.pagehelper.PageInfo;
import com.wangyu.common.Result;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.garage.dto.UserComeInDTO;
import com.wangyu.garage.dto.UserComeOutDTO;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.enums.CarStatusEnum;
import com.wangyu.garage.enums.UserEnum;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import com.wangyu.garage.service.GarageService;
import com.wangyu.garage.service.StopRecordingService;
import com.wangyu.garage.service.UserService;
import com.wangyu.garage.util.NullUtil;
import com.wangyu.garage.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private UserService userService;

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
        Garage garage = garageService.getById(id);
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
     * 入库
     * @param dto
     * @return
     */
    @RequestMapping(value = "/comein", method = RequestMethod.POST)
    public Result comein(@RequestBody UserComeInDTO dto){
        ValidateResult v = dto.validate();
        if(v.isInvalid())
            return failed(v);

        try{
            Long userId = dto.getUserId();
            Long garageId = dto.getGarageId();

            User user = userService.getById(userId);
            if (user == null)
                return failed("用户不存在，请注册");

            Garage garage = garageService.getById(garageId);
            if (garage == null)
                return failed("车库不存在，请联系管理员");

            StopRecordingQueryParameter stopRecordingQueryParameter = new StopRecordingQueryParameter();
            stopRecordingQueryParameter.setGarageid(garageId);
            stopRecordingQueryParameter.setUserid(userId);
            stopRecordingQueryParameter.setStatus(CarStatusEnum.COME_IN.getValue());
            //1.查询停车记录
            List<StopRecording> stopRecordingList = stopRecordingService.queryByParameter(stopRecordingQueryParameter);
            if (stopRecordingList.size() > 0){
                return success("已经成功扫描入库", stopRecordingList.get(0));
            }

            StopRecording stopRecording = new StopRecording();
            stopRecording.setGarageid(garageId);
            stopRecording.setUserid(userId);
            stopRecording.setPhone(user.getPhone());
            stopRecording.setStatus(CarStatusEnum.COME_IN.getValue());//入库
            stopRecording.setIntime(new Date());//停车时间，当前时间

            stopRecordingService.save(stopRecording);
            return success(stopRecording);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("入库失败");
        }
    }

    /**
     * 取车离开结算
     * @param dto
     * @return
     */
    @RequestMapping(value = "/comeout", method = RequestMethod.POST)
    public Result comeout(@RequestBody UserComeOutDTO dto){
        ValidateResult v = dto.validate();
        if(v.isInvalid())
            return failed(v);

        try {
            Long userId = dto.getUserId();
            Long garageId = dto.getGarageId();

            User user = userService.getById(userId);
            if (user == null)
                return failed("用户不存在，请注册");

            Garage garage = garageService.getById(garageId);
            if (garage == null)
                return failed("车库不存在，请联系管理员");

            StopRecordingQueryParameter stopRecordingQueryParameter = new StopRecordingQueryParameter();
            stopRecordingQueryParameter.setGarageid(garageId);
            stopRecordingQueryParameter.setUserid(userId);
            stopRecordingQueryParameter.setStatus(CarStatusEnum.COME_IN.getValue());
            //1.查询停车记录
            List<StopRecording> stopRecordingList = stopRecordingService.queryByParameter(stopRecordingQueryParameter);
            if(NullUtil.isNull(stopRecordingList)){
                return failed("查询不到如入库记录");
            }

            if(stopRecordingList.size() > 1){
                return failed("同时查询到多条入库未未结费记录");//TODO 管理端需要提供调整功能
            }

            StopRecording stopRecording = stopRecordingList.get(0);

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

            int num  = stopRecordingService.update(stopRecording);

            return success(stopRecording);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("出库失败");
        }
    }

    /**
     * 查询停车记录
     * @param stopRecordingQueryParameter
     * @return
     */
    @RequestMapping(value = "/queryStopRecording", method = RequestMethod.GET)
    public Result queryStopRecording(StopRecordingQueryParameter stopRecordingQueryParameter){
        try {
            log.info("查询停车记录：" + toJson(stopRecordingQueryParameter));
            PageInfo<StopRecording> pageInfo = stopRecordingService.pageQueryByParameter(stopRecordingQueryParameter);
            return success(pageInfo);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("出库失败");
        }
    }

}
