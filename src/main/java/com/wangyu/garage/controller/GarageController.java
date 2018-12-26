package com.wangyu.garage.controller;

import com.github.pagehelper.PageInfo;
import com.wangyu.common.Result;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.garage.dto.ComeinoutDto;
import com.wangyu.garage.dto.UserComeInDTO;
import com.wangyu.garage.dto.UserComeOutDTO;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.GarageItem;
import com.wangyu.garage.entity.StopRecording;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.enums.CarStatusEnum;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import com.wangyu.garage.response.GarageResponse;
import com.wangyu.garage.service.IGarageService;
import com.wangyu.garage.service.IStopRecordingService;
import com.wangyu.garage.service.IUserService;
import com.wangyu.garage.util.NullUtil;
import com.wangyu.garage.vo.ComeinoutVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 15:49
 */
@Slf4j
@Controller
@RequestMapping(value = "/garage")
public class GarageController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGarageService garageService;

    @Autowired
    private IStopRecordingService stopRecordingService;

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(){
        return "hello world";
    }

    @ResponseBody
    @GetMapping(value = "/query")
    public GarageResponse query(Long id){
        Garage garage = garageService.getById(id);
        return new GarageResponse(garage);
    }

    @ResponseBody
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
    @ResponseBody
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

            if (garage.getUnuse() == 0){
                //车位已满，提示用户
                return failed("车位已满，暂时无法入库停车");
            }

            StopRecordingQueryParameter stopRecordingQueryParameter = new StopRecordingQueryParameter();
            stopRecordingQueryParameter.setGarageid(garageId);
            stopRecordingQueryParameter.setUserid(userId);
            stopRecordingQueryParameter.setStatus(CarStatusEnum.COME_IN.getValue());
            //1.查询停车记录
            List<StopRecording> stopRecordingList = stopRecordingService.queryByParameter(stopRecordingQueryParameter);
            if (stopRecordingList.size() > 0){
                return success("已经成功扫描入库", stopRecordingList.get(0));
            }

            ComeinoutDto comeinoutDto = new ComeinoutDto(garageId, userId);

            ComeinoutVO comeinoutVO = stopRecordingService.carComein(comeinoutDto);
            return success(comeinoutVO);
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
    @ResponseBody
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
            ComeinoutDto comeinoutDto = new ComeinoutDto(garageId, userId, stopRecording.getId());
            ComeinoutVO comeoutVO = stopRecordingService.carComeout(comeinoutDto);
//            int num  = stopRecordingService.update(stopRecording);
            return success(comeoutVO);
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
    @ResponseBody
    @RequestMapping(value = "/queryStopRecording", method = RequestMethod.GET)
    public Result queryStopRecording(StopRecordingQueryParameter stopRecordingQueryParameter){
        try {
            log.info("查询停车记录：" + toJson(stopRecordingQueryParameter));
            List<StopRecording> pageInfo = stopRecordingService.queryByParameter(stopRecordingQueryParameter);
            return success(pageInfo);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("查询停车记录失败");
        }
    }

    /**
     * 分页查询停车记录
     * @param stopRecordingQueryParameter
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pageQueryStopRecording", method = RequestMethod.GET)
    public Result pageQueryStopRecording(StopRecordingQueryParameter stopRecordingQueryParameter){
        try {
            log.info("查询停车记录：" + toJson(stopRecordingQueryParameter));
            PageInfo<StopRecording> pageInfo = stopRecordingService.pageQueryByParameter(stopRecordingQueryParameter);
            return success(pageInfo);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("查询停车记录失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/queryAllGarageItem", method = RequestMethod.GET)
    public Result queryAllGarageItem(Long garageid){
        try {
            log.info("查询车位信息：" + toJson(garageid));
            List<GarageItem> list = garageService.queryAllGarageItem(garageid);
            return success(list);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("查询车位信息失败");
        }
    }

}
