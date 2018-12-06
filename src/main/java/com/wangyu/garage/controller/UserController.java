package com.wangyu.garage.controller;

import com.wangyu.common.Result;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.garage.contants.GarageConstants;
import com.wangyu.garage.dto.UserChangePasswordDTO;
import com.wangyu.garage.dto.UserLoginDTO;
import com.wangyu.garage.dto.UserRegisterDTO;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.enums.UserEnum;
import com.wangyu.garage.service.GarageService;
import com.wangyu.garage.service.StopRecordingService;
import com.wangyu.garage.service.UserService;
import com.wangyu.garage.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description 用户管理
 * @Author wangyu
 * @Date 2018/12/4 23:17
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private GarageService garageService;

    @Autowired
    private StopRecordingService stopRecordingService;

    /**
     * 注册账户
     * @param userDto
     * @return
     */
    @PostMapping(value = "/register")
    public Result register(@RequestBody UserRegisterDTO userDto){
        try{
            ValidateResult v = userDto.validate();
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
            } else {
                garage = garageService.getById(garageId);
                if(garage == null){
                    return failed("车库信息非法，请联系管理员");
                }
            }

            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            user.setPassword(Util.md5(userDto.getPassword()));
            user.setPrice(garage.getPrice());
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
     * 查询
     * @param
     * @return
     */
    @PostMapping(value = "/stopRecording")
    public Result stopRecording(){
        return success();
    }
}
