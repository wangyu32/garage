package com.gxc15090111.garage.controller;

import com.github.pagehelper.PageInfo;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.parameter.StopRecordingQueryParameter;
import com.gxc15090111.garage.parameter.UserStopRecordingQueryParameter;
import com.gxc15090111.garage.service.IGarageService;
import com.gxc15090111.garage.service.IStopRecordingService;
import com.gxc15090111.garage.service.IUserService;
import com.gxc15090111.garage.util.StringUtil;
import com.gxc15090111.garage.vo.StopRecordingVO;
import com.gxc15090111.garage.vo.UserStopRecordingVO;
import com.gxc15090111.system.common.ListResponse;
import com.gxc15090111.system.constant.CommonConstants;
import com.gxc15090111.system.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 停车记录
 * @Author gxc15090111
 * @Date 2018/12/25
 */
@Slf4j
@Controller
@RequestMapping(value = "/stoprecording")
public class StopRecordingController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGarageService garageService;

    @Autowired
    private IStopRecordingService stopRecordingService;

    /**
     * 跳转用户列表
     * @param
     * @return
     */
    @GetMapping(value = "/list",  produces="text/html;charset=utf-8")
    public String list(){
        //结束日期-当天
        String lg_createtime_end = DateUtil.getNowTime("yyyy-MM-dd");
        //开始日期-3个月前
        String lg_createtime_begin = DateUtil.addDateStr(lg_createtime_end, -1, "m");

        request.setAttribute("intimeStart", lg_createtime_begin);
        request.setAttribute("intimeEnd", lg_createtime_end);
        return "garage/stoprecording/list";
    }


    /**
     * 分页查询用户信息
     * @param parameter -用户信分页查询参数
     * @return String
     */
    @GetMapping(value = "/datalist", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(UserStopRecordingQueryParameter parameter) {
        try {
            log.info("查询停车记录信息：" + toJson(parameter));
            if (StringUtil.notBlank(parameter.getIntimeEnd())) {
                parameter.setIntimeEnd(parameter.getIntimeEnd() + CommonConstants.TIME_235959);
            }
            if (StringUtil.notBlank(parameter.getOuttimeEnd())) {
                parameter.setOuttimeEnd(parameter.getOuttimeEnd() + CommonConstants.TIME_235959);
            }
            PageInfo<UserStopRecordingVO> pageInfo  = stopRecordingService.queryUserStopRecording(parameter);
            ListResponse listResponse = new ListResponse();
            listResponse.setList(pageInfo.getList());
            listResponse.setTotal((int)pageInfo.getTotal());
            return listResponse;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ListResponse.faild("查询用户失败");
        }
    }

}
