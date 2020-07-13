package com.wangyu.controller;

import com.wangyu.common.BaseResponse;
import com.wangyu.common.Code;
import com.wangyu.common.ListResponse;
import com.wangyu.constant.MessageConstants;
import com.wangyu.constant.SessionAttributeConstants;
import com.wangyu.constant.UserLogTypeConstants;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.ChangePasswordParameter;
import com.wangyu.entity.parameter.DeleteParameter;
import com.wangyu.entity.parameter.UserPageQueryParameter;
import com.wangyu.entity.parameter.UserRolePageQueryParameter;
import com.wangyu.entity.vo.UserRoleCheckedVO;
import com.wangyu.model.SysUser;
import com.wangyu.response.UserResponse;
import com.wangyu.response.UserRoleCheckedVOListResponse;
import com.wangyu.service.ISysRoleService;
import com.wangyu.service.ISysUserService;
import com.wangyu.util.Md5Util;
import com.wangyu.util.NullUtil;
import com.wangyu.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 操作员表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Slf4j
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController{
    //对应菜单名
    private static final String MENU_NAME = "操作员管理";

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 菜单进入
     * @return 跳转页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String list() {
        return "system/sysuser/list";
    }

    /**
     * “编辑”按钮
     * @return 跳转页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String edit(Integer id) {
        if(id != null){
            SysUser model = sysUserService.getById(id);
            model.setPassword(null);//修改时，密码默认设置为空
            request.setAttribute("model", model);
            setSessionAttribute(getSessionKey(model.getId()), model);//缓存到session
        }
        return "system/sysuser/edit";
    }

    /**
     * 查询用户关联角色在所有角色中的选择情况
     * @param parameter - 用户角色关联关系查询参数
     * @return json
     */
    @RequestMapping(value = "/queryuserrolechecked", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserRoleCheckedVOListResponse queryUserRoleChecked(UserRolePageQueryParameter parameter) {
        try {
            Integer u_id = parameter.getUid();
            parameter.setPageQuery(false);
            UserRoleCheckedVOListResponse userRoleCheckedVOListResponse = sysRoleService.queryUserRoleChecked(parameter);
            if(u_id != null){
                //修改用户时操作
                List<String> roleIdList = new ArrayList<String>();
                if(NullUtil.notNull(userRoleCheckedVOListResponse.getList())){
                    List<UserRoleCheckedVO> roleList = userRoleCheckedVOListResponse.getList();
                    for (int i = 0; i < roleList.size(); i++) {
                        UserRoleCheckedVO roleCheckedVO = roleList.get(i);
                        if(roleCheckedVO.isChecked()){
                            //checked的节点说明是已分配给该用户的
                            roleIdList.add(String.valueOf(roleCheckedVO.getId()));
                        }
                    }
                }

                //session缓存处理
                SysUser userSession = (SysUser)getSessionAttribute(getSessionKey(u_id));
                userSession.setRIdArray(roleIdList.toArray(new String[0]));//set角色节点ID
                setSessionAttribute(getSessionKey(u_id), userSession);
            }

            return userRoleCheckedVOListResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            UserRoleCheckedVOListResponse userResponse = new UserRoleCheckedVOListResponse();
            userResponse.setCode(Code.FAIL);
            userResponse.setMessage("操作失败");
            return userResponse;
        }
    }

    /**
     * 保存用户
     * @param SysUser - 用户信息Model
     * @return String
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserResponse save(SysUser SysUser) {
        Integer u_id = SysUser.getId();
        String password = SysUser.getPassword();
        if(StringUtil.notBlank(password)){
            //MD5加密再存储
            password = Md5Util.MD5Encode(password, null).toUpperCase();
            SysUser.setPassword(password);
        }

        UserResponse response = null;
        String json = null;
        try {
            if(u_id == null){
                //增加
                //从返回的json获取新增用户的ID
                response = add(SysUser);

                //添加成功，记录操作日志
                if(isSuccessResponse(response)){
                    SysUser addUser = response.getData();
                    SysUser.setId(addUser.getId());

                    //记录日志
//                    addLog(json, MENU_NAME, UserLogTypeConstants.USER_ADD, String.valueOf(SysUser.getU_id()), SysUser.getU_logname(), toJson(SysUser));
                }
                return response;
            } else {
                //修改

                /** 权限校验，非管理员（isadmin=0）的用户，自己不能修改自己的权限 **/
                if(!isAdminUser() && u_id == getCurrentUserId().intValue()){
                    return new UserResponse(renderError(Code.FAIL, "非管理员账户不能修改自己信息"));
                }

                BaseResponse baseResponse = update(SysUser);

                //修改成功，记录日志
                if(isSuccessResponse(baseResponse)){
                    //从session中读取
                    SysUser userSession = (SysUser)getSessionAttribute(getSessionKey(u_id));
                    //记录日志
//                    addLog(null, MENU_NAME, UserLogTypeConstants.USER_UPDATE, u_id + "", SysUser.getU_logname(), toJson(userSession));
                    //从session中移除
                    removeSessionAttribute(getSessionKey(u_id));
                }

                response = new UserResponse(baseResponse);
                return response;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            UserResponse userResponse = new UserResponse();
            userResponse.setCode(Code.FAIL);
            userResponse.setMessage("操作失败");
            return userResponse;
        }
    }

    /**
     * 分页查询用户信息
     * @param parameter -用户信分页查询参数
     * @return String
     */
    @RequestMapping(value = "/datalist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(UserPageQueryParameter parameter) {
        if(!isAdminUser()){
            //不是管理员账户，查询用户的结果不能包含自己，也不能包含管理员，否则容易引起修改自己权限问题
            parameter.setIdNotEquals(getCurrentUserId());
            parameter.setIsAdminNotEquals(getCurrentUserId());
        }

        PageQueryResult result = sysUserService.findByPage(parameter);
        ListResponse listResponse = new ListResponse(result.getResultList(), result.getTotal());
        return listResponse;
    }

//    /**
//     * 管理员重置用户密码
//     * @param model - 用户Model
//     * @return json
//     */
//    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public UserResponse resetPassword(SysUser model) {
//        if(StringUtil.isBlank(model.getU_password())){
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_PASSWORD_CAN_NOT_BE_NULL));
//        }
//        if(model.getU_id() == null){
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL));
//        }
//
//        //新密码MD5加密
//        String newPasswordMD5 = Md5Util.MD5Encode(model.getU_password(), null).toUpperCase();
//        model.setU_password(newPasswordMD5);
//        model.setRef_p_id(getCurrentProjectId());
//
//        UserResponse response = null;
//        String json = null;
//        try {
//            String message = sysUserService.updatePasswordByAdmin(model);
//
//            if(Code.SUCCESS.equals(message)){
//                response = new UserResponse();
//                response.setMessage(SAVE_SUCCESS);
//                response.setData(model);//使用model将修改前密码返回
//
//                addLog(json, MENU_NAME, UserLogTypeConstants.USER_RESET_PASSWORD, model.getU_id() + "", newPasswordMD5, toJson(model));
//                return response;
//            }else{
//                log.error(MessageConstants.PASSWORD_UPDATE_FAILED);
//                return new UserResponse(renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED));
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED));
//        }
//    }
//
//    /**
//     * 登录用户自己修改自己密码
//     * @param parameter - 修改密码参数
//     * @return json
//     */
//    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public BaseResponse updatePassword(ChangePasswordParameter parameter) {
//        //旧密码
//        String oldPassword = parameter.getOldPassword();
//        //新密码
//        String newPassword = parameter.getNewPassword();
//        //新密码确认
//        String newPasswordConfirm = parameter.getNewPasswordConfirm();
//        //原始密码不能为空
//        if(StringUtil.isBlank(oldPassword)){
//            return renderError(MessageConstants.PASSWORD_OLD_CAN_NOT_BE_NULL);
//        }
//        //新密码不能为空
//        if(StringUtil.isBlank(newPassword)){
//            return renderError(MessageConstants.PASSWORD_NEW_CAN_NOT_BE_NULL);
//        }
//        //确认密码不能为空
//        if(StringUtil.isBlank(newPasswordConfirm)){
//            return renderError(MessageConstants.PASSWORD_NEW_CONFIRM_CAN_NOT_BE_NULL);
//        }
//        //新密码与确认密码不相同
//        if(!newPassword.equals(newPasswordConfirm)){
//            return renderError(MessageConstants.PASSWORD_NOT_EQUALS);
//        }
//        //新密码与旧密码不能相同
//        if(newPassword.equals(oldPassword)){
//            return renderError(MessageConstants.PASSWORD_CAN_NOT_EQUALS);
//        }
//        //旧密码MD5加密
//        String oldPasswordMD5 = Md5Util.MD5Encode(oldPassword, null).toUpperCase();
//        //新密码MD5加密
//        String newPasswordMD5 = Md5Util.MD5Encode(newPassword, null).toUpperCase();
//
//        parameter.setPid(getCurrentProjectId());
//        parameter.setUid(getCurrentUserId());
//
//        //修改成新密码
//        String message = sysUserService.updatePassword(parameter);
//        if(Code.SUCCESS.equals(message)){
//            return renderSuccess();
//        }else{
//            log.error(MessageConstants.PASSWORD_UPDATE_FAILED);
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED);
//        }
//    }
//
    /**
     * 根据对象ID获取缓存到Session的key
     * @param id - 实体model的ID
     * @return 缓存到Session的key
     */
    private String getSessionKey(Integer id){
        return SessionAttributeConstants.USER_ + id + "_" + getSessionid();
    }

    /**
     * 添加用户
     * @param model
     * @return
     */
    private UserResponse add(SysUser model) {
        if(StringUtil.isBlank(model.getLogname())){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_LOG_NAME_CAN_NOT_BE_NULL));
        }
        if(StringUtil.isBlank(model.getPassword())){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_PASSWORD_CAN_NOT_BE_NULL));
        }
        if(model.getStatus() == null){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.STATUS_CAN_NOT_BE_NULL));
        }

        UserPageQueryParameter parameter = new UserPageQueryParameter();
        parameter.setLogname(model.getLogname());

        //同一项目id对应的名称不应该有重复,否则容易混淆
        int count =	sysUserService.findCountOfLogname(parameter);
        if(count > 0){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_NAME_EXIST));
        }

        String message = null;
        try {
            Date date = new Date();
            model.setCreatetime(date);
            model.setUpdatetime(date);
            message = sysUserService.insertUserAndUserRole(model);
        } catch (Exception e) {
            return new UserResponse(renderError(Code.FAIL));
        }

        if(Code.SUCCESS.equals(message)){
            UserResponse response = new UserResponse();
            response.setMessage(SAVE_SUCCESS);
            SysUser newModel = new SysUser();
            newModel.setId(model.getId());
            response.setData(newModel);
            return response;
        }else{
            log.error("增加信息失败!" );
            return new UserResponse(renderError(Code.FAIL));
        }
    }

    /**
     * 修改
     * @param model - 信息Model
     * @return BaseResponse
     */
    private BaseResponse update(SysUser model) {
        if(model.getId() == null){
            return renderError(Code.FAIL, MessageConstants.PRIMARY_KEY_CAN_NOT_BE_NULL);
        }
        if(StringUtil.isBlank(model.getLogname())){
            return renderError(Code.FAIL, MessageConstants.USER_LOG_NAME_CAN_NOT_BE_NULL);
        }
        if(model.getStatus() == null){
            return renderError(Code.FAIL, MessageConstants.STATUS_CAN_NOT_BE_NULL);
        }

        //同一项目id对应的登录名不应该有重复
        UserPageQueryParameter parameter = new UserPageQueryParameter();
        parameter.setId(model.getId());//排除此uid的
        parameter.setLogname(model.getLogname());
        int count =	sysUserService.findCountOfLogname(parameter);
        if(count > 0){
            return renderError(Code.FAIL, MessageConstants.USER_NAME_EXIST);
        }

        String message = null;
        try {
            message = sysUserService.updateUserAndUserRole(model);
        } catch (Exception e) {
            return renderError(Code.FAIL);
        }
        if(Code.SUCCESS.equals(message)){
            return renderSuccess();
        }else{
            log.error("修改信息失败!" );
            return renderError(Code.FAIL);
        }
    }

    /**
     * 根据用户id删除批量删除
     * @param ids - 批量删除参数
     * @return BaseResponse
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public BaseResponse delete(Integer[] ids) {
        DeleteParameter parameter = new DeleteParameter();
        parameter.setIdArray(ids);
        //校验
        if(NullUtil.isNull(parameter.getIdArray())){
            return renderError(Code.FAIL, MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL);
        }

        //查询用户中是否存在管理员账户
        List<SysUser> list = sysUserService.findAdminUser(parameter);

        if(NullUtil.notNull(list)){
            StringBuffer s = new StringBuffer();
            s.append("用户");
            for (int i = 0; i < list.size(); i++) {
                SysUser model = list.get(i);
                String name = model.getLogname();
                if(i == 0){
                    s.append("[");
                    s.append(name);
                    s.append("]");
                }else{
                    s.append("、[");
                    s.append(name);
                    s.append("]");
                }
            }
            s.append("是管理员用户，不能删除！");
            return renderError(Code.FAIL, s.toString());
        }

        String message = null;
        try {
            message = sysUserService.deleteBatch(parameter);
        } catch (Exception e) {
            return renderError(Code.FAIL);
        }
        if(Code.SUCCESS.equals(message)){
            return renderSuccess();
        }else{
            log.error("删除角色信息失败!" );
            return renderError(Code.FAIL);
        }
    }

}
