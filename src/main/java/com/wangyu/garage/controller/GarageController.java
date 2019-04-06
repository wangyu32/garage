package com.wangyu.garage.controller;

import com.github.pagehelper.PageInfo;
import com.wangyu.common.Result;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.garage.dto.ComeinoutDTO;
import com.wangyu.garage.dto.UserComeInDTO;
import com.wangyu.garage.dto.UserComeOutDTO;
import com.wangyu.garage.entity.*;
import com.wangyu.garage.enums.CarStatusEnum;
import com.wangyu.garage.parameter.GaragePageQueryParameter;
import com.wangyu.garage.parameter.StopRecordingQueryParameter;
import com.wangyu.garage.response.GarageResponse;
import com.wangyu.garage.service.IGarageService;
import com.wangyu.garage.service.IPriceUnitService;
import com.wangyu.garage.service.IStopRecordingService;
import com.wangyu.garage.service.IUserService;
import com.wangyu.garage.util.NullUtil;
import com.wangyu.garage.util.StringUtil;
import com.wangyu.garage.vo.ComeinoutVO;
import com.wangyu.garage.vo.GarageVO;
import com.wangyu.system.common.BaseResponse;
import com.wangyu.system.common.Code;
import com.wangyu.system.common.ListResponse;
import com.wangyu.system.constant.MessageConstants;
import com.wangyu.system.constant.SessionAttributeConstants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private IPriceUnitService priceUnitService;

    /**
     * 跳转用户列表
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list",  produces="text/html;charset=utf-8")
    public String list(){
        return "garage/garage/list";
    }

    /**
     * “编辑”按钮
     * @return 跳转页面
     */
    @GetMapping(value = "/edit", produces = "text/html;charset=utf-8")
    public String edit(Long id) {
        if(id != null){
            Garage model = garageService.getById(id);

            request.setAttribute("model", model);
            setSessionAttribute(getSessionKey(model.getId()), model);//缓存到session
        }
        List<PriceUnit> priceUnitList = priceUnitService.queryAll();
        request.setAttribute("priceUnitList", priceUnitList);
        return "garage/garage/edit";
    }

    /**
     * 分页查询用户信息
     * @param parameter -用户信分页查询参数
     * @return String
     */
    @GetMapping(value = "/datalist", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(GaragePageQueryParameter parameter) {
        try {
            log.info("查询车库信息：" + toJson(parameter));
            PageInfo<GarageVO> pageInfo  = garageService.pageQueryByParameter(parameter);
            ListResponse listResponse = new ListResponse();
            listResponse.setList(pageInfo.getList());
            listResponse.setTotal((int)pageInfo.getTotal());
            return listResponse;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ListResponse.faild("查询用户失败");
        }
    }


    @ResponseBody
    @GetMapping(value = "/query")
    public GarageResponse query(Long id){
        Garage garage = garageService.getById(id);
        return new GarageResponse(garage);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse save(@RequestBody Garage model){
        if(StringUtil.isBlank(model.getName())){
            return renderError(Code.FAIL, MessageConstants.GARAGE_NAME_CAN_NOT_BE_NULL);
        }
        if(model.getTotal() == null){
            return renderError(Code.FAIL, MessageConstants.GARAGE_TOTAL_CAN_NOT_BE_NULL);
        }
        if(model.getInuse() == null){
            return renderError(Code.FAIL, MessageConstants.GARAGE_INUSE_CAN_NOT_BE_NULL);
        }
        if(model.getUnuse() == null){
            return renderError(Code.FAIL, MessageConstants.GARAGE_UNUSE_CAN_NOT_BE_NULL);
        }
        if(model.getPriceUnitId() == null){
            return renderError(Code.FAIL, MessageConstants.GARAGE_PRICE_UNIT_CAN_NOT_BE_NULL);
        }
        if(model.getPrice() == null){
            return renderError(Code.FAIL, MessageConstants.GARAGE_PRICE_CAN_NOT_BE_NULL);
        }
        if(StringUtil.isBlank(model.getServerIp())){
            return renderError(Code.FAIL, MessageConstants.GARAGE_SERVER_IP_CAN_NOT_BE_NULL);
        }
        if(model.getServerPort() == null){
            return renderError(Code.FAIL, MessageConstants.GARAGE_SERVER_PORT_CAN_NOT_BE_NULL);
        }
        model.setCreatetime(new Date());
        try {
            int num = 0;
            if(model.getId() == null){
                num = garageService.save(model);
            } else{
                num = garageService.update(model);
            }
            if(num != 1){
                return renderError("操作失败");
            }
        } catch (Exception e){
            log.error(e.getMessage(), e);
            renderError("操作失败");
        }

        return renderSuccess();
    }

    /**
     * 入库
     * @param dto
     * @return
     */
    @ApiOperation(value = "扫描入库", notes = "用户扫描入库二维码，记录入库时间，随机分配车位")
    @ApiImplicitParam(name="dto", value = "用户入库DTO", required = true, dataType = "UserComeInDTO")
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

            ComeinoutDTO comeinoutDto = new ComeinoutDTO(garageId, userId);
            ComeinoutVO comeinoutVO = null;
            if (stopRecordingList.size() > 0){
                comeinoutVO = stopRecordingService.queryComeInReocrd(comeinoutDto, stopRecordingList.get(0));
                return success("已经成功扫描入库", comeinoutVO);
            }
            comeinoutVO = stopRecordingService.carComein(comeinoutDto);
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
            ComeinoutDTO comeinoutDto = new ComeinoutDTO(garageId, userId, stopRecording.getId());
            ComeinoutVO comeoutVO = stopRecordingService.carComeout(comeinoutDto);
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

    /**
     * 根据对象ID获取缓存到Session的key
     * @param id - 实体model的ID
     * @return 缓存到Session的key
     */
    private String getSessionKey(Long id){
        return SessionAttributeConstants.GARAGE_ + id + "_" + getSessionid();
    }

}
