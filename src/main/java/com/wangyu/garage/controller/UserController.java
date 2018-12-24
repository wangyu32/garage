package com.wangyu.garage.controller;

import com.wangyu.common.Result;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.garage.contants.GarageConstants;
import com.wangyu.garage.dto.UserChangePasswordDTO;
import com.wangyu.garage.dto.UserLoginDTO;
import com.wangyu.garage.dto.UserQueryDTO;
import com.wangyu.garage.dto.UserRegisterDTO;
import com.wangyu.garage.entity.Garage;
import com.wangyu.garage.entity.User;
import com.wangyu.garage.enums.UserEnum;
import com.wangyu.garage.service.GarageService;
import com.wangyu.garage.service.StopRecordingService;
import com.wangyu.garage.service.UserService;
import com.wangyu.garage.util.Util;
import com.wangyu.system.common.ListResponse;
import com.wangyu.system.constant.SessionAttributeConstants;
import com.wangyu.system.page.PageQueryResult;
import com.wangyu.system.parameter.SysUserPageQueryParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    @ResponseBody
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
            }
            garage = garageService.getById(garageId);
            if(garage == null){
                return failed("车库信息非法，请联系管理员");
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
    @PostMapping(value = "/list",  produces="text/html;charset=utf-8")
    public String list(){
        return "garage/user/list";
    }


    /**
     * “编辑”按钮
     * @return 跳转页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
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
    @RequestMapping(value = "/datalist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(SysUserPageQueryParameter parameter) {
        parameter.setRef_p_id(getCurrentProjectId());
        if (!isAdminUser()) {
            //不是管理员账户，查询用户的结果不能包含自己，也不能包含管理员，否则容易引起修改自己权限问题
            parameter.setU_id_not_equals(getCurrentUserId());
            parameter.setU_isadmin_not_equals(getCurrentUserId());
        }

        PageQueryResult result = null; //userService.findByPage(parameter);
        ListResponse listResponse = new ListResponse(result.getResultList(), result.getTotal());
        return listResponse;
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
