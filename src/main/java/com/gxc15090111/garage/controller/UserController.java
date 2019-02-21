package com.gxc15090111.garage.controller;

import com.github.pagehelper.PageInfo;
import com.gxc15090111.common.Result;
import com.gxc15090111.common.validate.ValidateResult;
import com.gxc15090111.garage.contants.GarageConstants;
import com.gxc15090111.garage.dto.UserChangePasswordDTO;
import com.gxc15090111.garage.dto.UserLoginDTO;
import com.gxc15090111.garage.dto.UserQueryDTO;
import com.gxc15090111.garage.dto.UserRegisterDTO;
import com.gxc15090111.garage.entity.Garage;
import com.gxc15090111.garage.entity.StopRecording;
import com.gxc15090111.garage.entity.User;
import com.gxc15090111.garage.enums.CarStatusEnum;
import com.gxc15090111.garage.enums.UserEnum;
import com.gxc15090111.garage.parameter.StopRecordingQueryParameter;
import com.gxc15090111.garage.parameter.UserPageQueryParameter;
import com.gxc15090111.garage.service.IGarageService;
import com.gxc15090111.garage.service.IStopRecordingService;
import com.gxc15090111.garage.service.IUserService;
import com.gxc15090111.garage.util.NullUtil;
import com.gxc15090111.garage.util.StringUtil;
import com.gxc15090111.garage.util.Util;
import com.gxc15090111.system.common.BaseResponse;
import com.gxc15090111.system.common.Code;
import com.gxc15090111.system.common.DeleteParameter;
import com.gxc15090111.system.common.ListResponse;
import com.gxc15090111.system.constant.CommonConstants;
import com.gxc15090111.system.constant.MessageConstants;
import com.gxc15090111.system.constant.SessionAttributeConstants;
import com.gxc15090111.system.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description 用户管理
 * @Author wangyu
 * @Date 2018/12/4 23:17
 */
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGarageService garageService;

    @Autowired
    private IStopRecordingService stopRecordingService;

    /**
     * 注册账户
     * @param userDto
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/register")
    public Result register(@RequestBody UserRegisterDTO userDto){
        try{
            if(userDto.getId() != null){
            //修改用户
                return update(userDto);
            }


            ValidateResult v = userDto.validateRegister();
            if(v.isInvalid())
                return failed(v);

            //验证手机号是否已经注册过
            User userExist = this.userService.getByPhone(userDto.getPhone());
            if(userExist != null)
                return failed("该手机号码已注册");

            Garage garage = null;
            Long garageId = userDto.getGarageId();
            if(garageId == null){
                //不传位默认ID
                garageId = GarageConstants.DEFAULT_GARAGE_ID;
                userDto.setGarageId(garageId);
            }
            garage = garageService.getById(garageId);
            if(garage == null){
                return failed("车库信息非法，请联系管理员");
            }

            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            user.setPassword(Util.md5(userDto.getPassword()));
            if(userDto.getPrice() != null){
                //web端自定义价格
                user.setPrice(userDto.getPrice());
            } else {
                //手机用户注册的默认价格
                user.setPrice(garage.getPrice());
            }

            user.setType(UserEnum.COMMON.getValue());
            user.setCreatetime(new Date());

            //保存用户
            this.userService.save(user);

            return success("注册成功");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("注册失败");
        }
    }

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public Result login(@RequestBody UserLoginDTO loginDto){
        ValidateResult v = loginDto.validate();
        if(v.isInvalid())
            return failed(v);

        try{
            String password = loginDto.getPassword();
            password = Util.md5(password);

            //查询账户密码是否匹配
            User userExist = this.userService.getByPhoneAndPassword(loginDto.getPhone(), password);
            if(userExist == null){
                return failed("用户不存在或者密码错误");
            }

            return success("登录成功");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("登录失败");
        }
    }

    /**
     * 修改密码
     * @param userChangePasswordDTO
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/changePassword")
    public Result changePassword(@RequestBody UserChangePasswordDTO userChangePasswordDTO){
        ValidateResult v = userChangePasswordDTO.validate();
        if(v.isInvalid())
            return failed(v);

        try{
            String oldPassword = Util.md5(userChangePasswordDTO.getOldPassword());
            String newPassword = Util.md5(userChangePasswordDTO.getNewPassword());

            int num = userService.changePassword(userChangePasswordDTO.getPhone(), oldPassword, newPassword);
            if(num != 1){
                return failed("修改密码失败，用户名或密码错误");
            }

            return success("修改密码成功");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("修改密码失败");
        }
    }

    /**
     * 查询用户
     * @param dto
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getUserByPhone")
    public Result getUserByPhone(@RequestBody UserQueryDTO dto){
        ValidateResult v = dto.validatePhone();
        if(v.isInvalid())
            return failed(v);

        try{
            User user = userService.getByPhone(dto.getPhone());
            if(user == null){
                return failed("用户不存在");
            }
            user.setPassword(null);
            return success(user);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("查询用户失败");
        }
    }

    /**-----------------------以下为web端管理使用------------------------------**/

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

        request.setAttribute("startTime", lg_createtime_begin);
        request.setAttribute("endTime", lg_createtime_end);
        return "garage/user/list";
    }


    /**
     * “编辑”按钮
     * @return 跳转页面
     */
    @GetMapping(value = "/edit", produces = "text/html;charset=utf-8")
    public String edit(Long id) {
        if(id != null){
            User model = userService.getById(id);
//            model.setU_password(null);//修改时，密码默认设置为空
            request.setAttribute("model", model);
            setSessionAttribute(getSessionKey(model.getId()), model);//缓存到session
        }
        return "garage/user/edit";
    }


    /**
     * 分页查询用户信息
     * @param parameter -用户信分页查询参数
     * @return String
     */
    @GetMapping(value = "/datalist", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(UserPageQueryParameter parameter) {
        try {
            log.info("查询用户信息：" + toJson(parameter));
            if (StringUtil.notBlank(parameter.getEndTime())) {
                parameter.setEndTime(parameter.getEndTime() + CommonConstants.TIME_235959);
            }
            PageInfo<User> pageInfo  = userService.pageQueryByParameter(parameter);
            ListResponse listResponse = new ListResponse();
            listResponse.setList(pageInfo.getList());
            listResponse.setTotal((int)pageInfo.getTotal());
            return listResponse;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ListResponse.faild("查询用户失败");
        }
    }

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update(@RequestBody UserRegisterDTO userDto){
        try{
            ValidateResult v = userDto.validateUpdate();
            if(v.isInvalid())
                return failed(v);

            Garage garage = null;
            Long garageId = userDto.getGarageId();
            if(garageId == null){
                //不传位默认ID
                garageId = GarageConstants.DEFAULT_GARAGE_ID;
                userDto.setGarageId(garageId);
            }
            garage = garageService.getById(garageId);
            if(garage == null){
                return failed("车库信息非法，请联系管理员");
            }

            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            user.setPassword(null);
            if(userDto.getPrice() != null){
                //web端自定义价格
                user.setPrice(userDto.getPrice());
            } else {
                //手机用户注册的默认价格
                user.setPrice(garage.getPrice());
            }

            user.setCreatetime(new Date());

            //保存用户
            this.userService.updateByPrimaryKeySelective(user);

            return success("修改成功");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("修改失败");
        }
    }

    /**
     * 根据用户id删除批量删除
     * @param ids
     * @return BaseResponse
     */
    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    public BaseResponse delete(Integer[] ids) {
        DeleteParameter parameter = new DeleteParameter();
        parameter.setIdArray(ids);
        //校验
        if(NullUtil.isNull(parameter.getIdArray())){
            return renderError(Code.FAIL, MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL);
        }

        Long[] useridArray = new Long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            useridArray[i] = Long.valueOf(ids[i]);
        }

        UserPageQueryParameter userPageQueryParameter = new UserPageQueryParameter();
        userPageQueryParameter.setUseridArray(useridArray);
        userPageQueryParameter.setPageQuery(false);//不分页查询
        PageInfo<User> pageInfo = userService.pageQueryByParameter(userPageQueryParameter);
        List<User> userList = pageInfo.getList();
        if(NullUtil.isNull(userList)){
            return renderSuccess("删除成功");//用户不存在也算删除成功
        }

        //如果有未出库的记录，则不能删除用户
        StopRecordingQueryParameter stopRecordingQueryParameter = new StopRecordingQueryParameter();
        stopRecordingQueryParameter.setUseridArray(useridArray);
        stopRecordingQueryParameter.setStatus(CarStatusEnum.COME_IN.getValue());
        //1.查询停车记录
        List<StopRecording> stopRecordingList = stopRecordingService.queryByParameter(stopRecordingQueryParameter);
        if (stopRecordingList.size() > 0){
            Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
            StringJoiner sb = new StringJoiner(",","用户[","]存在未出库停车记录，不能删除");
            stopRecordingList.stream().distinct().forEach(e -> {
                sb.add(userMap.get(e.getUserid()).getName());
            });
            return renderError(Code.FAIL, sb.toString());
        }

        try {
            userService.deleteBatch(parameter);
            return renderSuccess();
        } catch (Exception e) {
            log.error("删除角色信息失败!", e);
            return renderError(Code.FAIL);
        }
    }

    /**
     * 管理员重置用户密码
     * @param model - 用户Model
     * @return json
     */
    @PostMapping(value = "/resetpassword")
    @ResponseBody
    public Result resetPassword(@RequestBody User model) {
        if(StringUtil.isBlank(model.getPassword())){
            return failed(MessageConstants.USER_PASSWORD_CAN_NOT_BE_NULL);
        }
        if(model.getId() == null){
            return failed(MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL);
        }

        User user = userService.getById(model.getId());

        try{
            String newPassword = Util.md5(model.getPassword());
            int num = userService.changePassword(user.getPhone(), user.getPassword(), newPassword);
            if(num != 1){
                return failed("修改密码失败，用户名或密码错误");
            }

            return success("修改密码成功");
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return failed("修改密码失败");
        }
    }

    /**
     * 根据对象ID获取缓存到Session的key
     * @param id - 实体model的ID
     * @return 缓存到Session的key
     */
    private String getSessionKey(Long id){
        return SessionAttributeConstants.USER_ + id + "_" + getSessionid();
    }
}
