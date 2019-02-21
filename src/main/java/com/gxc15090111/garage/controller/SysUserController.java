package com.gxc15090111.garage.controller;

import java.util.ArrayList;
import java.util.List;

import com.gxc15090111.garage.util.NullUtil;
import com.gxc15090111.garage.util.StringUtil;
import com.gxc15090111.system.common.BaseResponse;
import com.gxc15090111.system.common.Code;
import com.gxc15090111.system.common.DeleteParameter;
import com.gxc15090111.system.common.ListResponse;
import com.gxc15090111.system.constant.MessageConstants;
import com.gxc15090111.system.constant.SessionAttributeConstants;
import com.gxc15090111.system.constant.UserLogTypeConstants;
import com.gxc15090111.system.exception.RollbackableBizException;
import com.gxc15090111.system.model.SysUserModel;
import com.gxc15090111.system.page.PageQueryResult;
import com.gxc15090111.system.parameter.ChangePasswordParameter;
import com.gxc15090111.system.parameter.SysUserPageQueryParameter;
import com.gxc15090111.system.parameter.SysUserRolePageQueryParameter;
import com.gxc15090111.system.response.ModuleMenuResponse;
import com.gxc15090111.system.response.UserResponse;
import com.gxc15090111.system.response.UserRoleCheckedVOListResponse;
import com.gxc15090111.system.service.ISysRoleService;
import com.gxc15090111.system.service.ISysUserService;
import com.gxc15090111.system.util.Md5Util;
import com.gxc15090111.system.vo.SysUserRoleCheckedVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户的控制层
 * @author 	gxc15090111  2018年10月21日
 *
 */
@Slf4j
@Controller
@RequestMapping(value = "/sysuser")
public class SysUserController extends BaseController{

    //PRM微服务地址
    private static final String PRM_SERVICE_URL = "";//ropertyUtils.getValue(CommonConstants.STAR_BBM_PRM);
    //增加
    private static final String ADD = PRM_SERVICE_URL + "/user/add";
    //给用户分配角色
    private static final String ADD_ROLE = PRM_SERVICE_URL + "/user/adduserrole";
    //修改
    private static final String UPDATE = PRM_SERVICE_URL + "/user/update";
    //修改状态
    private static final String UPDATE_STATUS = PRM_SERVICE_URL + "/user/updatestatus";
    //修改密码
    private static final String UPDATE_PASSWORD = PRM_SERVICE_URL + "/user/updatepassword";
    //管理员重置用户密码
    private static final String RESET_PASSWORD = PRM_SERVICE_URL + "/user/resetpassword";
    //批量删除
    private static final String DELETE = PRM_SERVICE_URL + "/user/delete";
    //分页查询
    private static final String PAGE_QUERY = PRM_SERVICE_URL + "/user/list";
    //根据id查询
    private static final String FIND_BY_ID = PRM_SERVICE_URL + "/user/findbyid";
    //根据用户id查询角色
    private static final String QUERY_ROLES_BY_USERID = PRM_SERVICE_URL + "/role/queryrolesbyuserid";
    //分页查询角色
    private static final String PAGE_QUERY_FOR_ROLE = PRM_SERVICE_URL + "/role/list";
    //查询用户关联角色在所有角色中的选择情况
    private static final String QUERY_USER_ROLE_CHECKED= PRM_SERVICE_URL + "/role/queryuserrolechecked";

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
            SysUserModel model = sysUserService.findByPrimaryKey(id);
            model.setU_password(null);//修改时，密码默认设置为空
            request.setAttribute("model", model);
            setSessionAttribute(getSessionKey(model.getU_id()), model);//缓存到session
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
    public UserRoleCheckedVOListResponse queryUserRoleChecked(SysUserRolePageQueryParameter parameter) {
        parameter.setRef_p_id(getCurrentProjectId());
        try {
            Integer u_id = parameter.getU_id();
            UserRoleCheckedVOListResponse userRoleCheckedVOListResponse = sysRoleService.queryUserRoleChecked(parameter);
            if(u_id != null){
            //修改用户时操作
                List<String> roleIdList = new ArrayList<String>();
                if(NullUtil.notNull(userRoleCheckedVOListResponse.getList())){
                    List<SysUserRoleCheckedVO> roleList = userRoleCheckedVOListResponse.getList();
                    for (int i = 0; i < roleList.size(); i++) {
                        SysUserRoleCheckedVO roleCheckedVO = roleList.get(i);
                        if(roleCheckedVO.isChecked()){
                            //checked的节点说明是已分配给该用户的
                            roleIdList.add(String.valueOf(roleCheckedVO.getR_id()));
                        }
                    }
                }

                //session缓存处理
                SysUserModel userSession = (SysUserModel)getSessionAttribute(getSessionKey(u_id));
                userSession.setR_id_array(roleIdList.toArray(new String[0]));//set角色节点ID
                setSessionAttribute(getSessionKey(u_id), userSession);
            }

            return userRoleCheckedVOListResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            UserRoleCheckedVOListResponse userResponse = new UserRoleCheckedVOListResponse();
            userResponse.setCode(Code.FAIL);
            userResponse.setMessage("操作失败");
            return userResponse;
//            return renderError(Code.FAIL, "操作失败");
        }
    }

    /**
     * 保存用户
     * @param userModel - 用户信息Model
     * @return String
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserResponse save(SysUserModel userModel) {
        userModel.setRef_p_id(getCurrentProjectId());

        Integer u_id = userModel.getU_id();
        String password = userModel.getU_password();
        if(StringUtil.notBlank(password)){
            //MD5加密再存储
            password = Md5Util.MD5Encode(password, null).toUpperCase();
            userModel.setU_password(password);
        }

        UserResponse response = null;
        String json = null;
        try {
            if(u_id == null){
                //增加
                //从返回的json获取新增用户的ID
                response = add(userModel);

                //添加成功，记录操作日志
                if(isSuccessResponse(response)){
                    SysUserModel addUser =	response.getData();
                    userModel.setU_id(addUser.getU_id());

                    //记录日志
                    addLog(json, MENU_NAME, UserLogTypeConstants.USER_ADD, String.valueOf(userModel.getU_id()), userModel.getU_logname(), toJson(userModel));
                }
                return response;
            } else {
                //修改

                /** 权限校验，非管理员（isadmin=0）的用户，自己不能修改自己的权限 **/
                if(!isAdminUser() && u_id == getCurrentUserId().intValue()){
                    return new UserResponse(renderError(Code.FAIL, "非管理员账户不能修改自己信息"));
                }

                BaseResponse baseResponse = update(userModel);

                //修改成功，记录日志
                if(isSuccessResponse(baseResponse)){
                    //从session中读取
                    SysUserModel userSession = (SysUserModel)getSessionAttribute(getSessionKey(u_id));
                    //记录日志
                    addLog(null, MENU_NAME, UserLogTypeConstants.USER_UPDATE, u_id + "", userModel.getU_logname(), toJson(userSession));
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

//    /**
//     * 根据用户id批量删除
//     * @param ids - 用户id数组
//     * @return String
//     */
//    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String delete (String[] ids) {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("idArray", ids);
//        paramsMap.put("ref_p_id", getCurrentProjectIdStr());
//        String json = null;
//        try {
//            json = Http.post(DELETE, paramsMap, null);
//            //记录日志
//            addLog(json, MENU_NAME, UserLogTypeConstants.USER_DELETE, null, null, toJson(ids));
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return httpError();
//        }
//        return json;
//    }

    /**
     * 分页查询用户信息
     * @param parameter -用户信分页查询参数
     * @return String
     */
    @RequestMapping(value = "/datalist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(SysUserPageQueryParameter parameter) {
        parameter.setRef_p_id(getCurrentProjectId());
        if(!isAdminUser()){
            //不是管理员账户，查询用户的结果不能包含自己，也不能包含管理员，否则容易引起修改自己权限问题
            parameter.setU_id_not_equals(getCurrentUserId());
            parameter.setU_isadmin_not_equals(getCurrentUserId());
        }

        PageQueryResult result = sysUserService.findByPage(parameter);
        ListResponse listResponse = new ListResponse(result.getResultList(), result.getTotal());
        return listResponse;

//        Map<String, String > paramsMap = new HashMap<String, String>();
////        paramsMap.put("offset", String.valueOf(parameter.getOffset()));
////        paramsMap.put("limit", String.valueOf(parameter.getLimit()));
////        paramsMap.put("sort", parameter.getSort());
////        paramsMap.put("order", parameter.getOrder());
////        paramsMap.put("ref_p_id", getCurrentProjectIdStr(request));//项目id
////
////        if (parameter.getU_id() != null) {
////            paramsMap.put("u_id", String.valueOf(parameter.getU_id()));
////        }
////        if (StringUtil.notBlank(parameter.getU_logname())) {
////            paramsMap.put("u_logname", parameter.getU_logname());
////        }
////        if (StringUtil.notBlank(parameter.getU_realname())) {
////            paramsMap.put("u_realname", parameter.getU_realname());
////        }
////        if (StringUtil.notBlank(parameter.getU_email())) {
////            paramsMap.put("u_email", parameter.getU_email());
////        }
////        if (StringUtil.notBlank(parameter.getU_mobilephone())) {
////            paramsMap.put("u_mobilephone", parameter.getU_mobilephone());
////        }
////        if (parameter.getU_status() != null) {
////            paramsMap.put("u_status", String.valueOf(parameter.getU_status()));
////        }
////
////        if(!isAdminUser()){
////            //不是管理员账户，查询用户的结果不能包含自己，也不能包含管理员，否则容易引起修改自己权限问题
////            paramsMap.put("u_id_not_equals", getCurrentUserIdStr());
////            paramsMap.put("u_isadmin_not_equals", getCurrentUserIdStr());
////        }
////
////        String json = null;
////        try {
////            json = Http.post(PAGE_QUERY, paramsMap);
////        } catch (Exception e) {
////            log.error(e.getMessage(), e);
////            return httpError();
////        }
////        return json;
    }

    /**
     * 管理员重置用户密码
     * @param model - 用户Model
     * @return json
     */
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserResponse resetPassword(SysUserModel model) {
        if(StringUtil.isBlank(model.getU_password())){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_PASSWORD_CAN_NOT_BE_NULL));
        }
        if(model.getU_id() == null){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL));
        }

        //新密码MD5加密
        String newPasswordMD5 = Md5Util.MD5Encode(model.getU_password(), null).toUpperCase();
        model.setU_password(newPasswordMD5);
        model.setRef_p_id(getCurrentProjectId());

        UserResponse response = null;
        String json = null;
        try {
            String message = sysUserService.updatePasswordByAdmin(model);

            if(Code.SUCCESS.equals(message)){
                response = new UserResponse();
                response.setMessage(SAVE_SUCCESS);
                response.setData(model);//使用model将修改前密码返回

                addLog(json, MENU_NAME, UserLogTypeConstants.USER_RESET_PASSWORD, model.getU_id() + "", newPasswordMD5, toJson(model));
                return response;
            }else{
                log.error(MessageConstants.PASSWORD_UPDATE_FAILED);
                return new UserResponse(renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new UserResponse(renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED));
        }
    }

    /**
     * 登录用户自己修改自己密码
     * @param parameter - 修改密码参数
     * @return json
     */
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public BaseResponse updatePassword(ChangePasswordParameter parameter) {
        //旧密码
        String oldPassword = parameter.getOldPassword();
        //新密码
        String newPassword = parameter.getNewPassword();
        //新密码确认
        String newPasswordConfirm = parameter.getNewPasswordConfirm();
        //原始密码不能为空
        if(StringUtil.isBlank(oldPassword)){
            return renderError(MessageConstants.PASSWORD_OLD_CAN_NOT_BE_NULL);
        }
        //新密码不能为空
        if(StringUtil.isBlank(newPassword)){
            return renderError(MessageConstants.PASSWORD_NEW_CAN_NOT_BE_NULL);
        }
        //确认密码不能为空
        if(StringUtil.isBlank(newPasswordConfirm)){
            return renderError(MessageConstants.PASSWORD_NEW_CONFIRM_CAN_NOT_BE_NULL);
        }
        //新密码与确认密码不相同
        if(!newPassword.equals(newPasswordConfirm)){
            return renderError(MessageConstants.PASSWORD_NOT_EQUALS);
        }
        //新密码与旧密码不能相同
        if(newPassword.equals(oldPassword)){
            return renderError(MessageConstants.PASSWORD_CAN_NOT_EQUALS);
        }
        //旧密码MD5加密
        String oldPasswordMD5 = Md5Util.MD5Encode(oldPassword, null).toUpperCase();
        //新密码MD5加密
        String newPasswordMD5 = Md5Util.MD5Encode(newPassword, null).toUpperCase();

        parameter.setPid(getCurrentProjectId());
        parameter.setUid(getCurrentUserId());

        //修改成新密码
        String message = sysUserService.updatePassword(parameter);
        if(Code.SUCCESS.equals(message)){
            return renderSuccess();
        }else{
            log.error(MessageConstants.PASSWORD_UPDATE_FAILED);
            return renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED);
        }
    }

    /**
     * 根据对象ID获取缓存到Session的key
     * @param id - 实体model的ID
     * @return 缓存到Session的key
     */
    private String getSessionKey(Integer id){
        return SessionAttributeConstants.SYS_USER_ + id + "_" + getSessionid();
    }

    /**
     * 添加用户
     * @param model
     * @return
     */
    private UserResponse add(SysUserModel model) {
        if(model.getRef_p_id() == null){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.PROJECT_ID_CAN_NOT_BE_NULL));
        }
        if(StringUtil.isBlank(model.getU_logname())){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_LOG_NAME_CAN_NOT_BE_NULL));
        }
        if(StringUtil.isBlank(model.getU_password())){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_PASSWORD_CAN_NOT_BE_NULL));
        }
        if(model.getU_status() == null){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.STATUS_CAN_NOT_BE_NULL));
        }

        SysUserPageQueryParameter parameter = new SysUserPageQueryParameter();
        parameter.setRef_p_id(model.getRef_p_id());
        parameter.setU_logname(model.getU_logname());

        //同一项目id对应的名称不应该有重复,否则容易混淆
        int count =	sysUserService.findCountOfUlogname(parameter);
        if(count > 0){
            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_NAME_EXIST));
        }

        String message = null;
        try {
            message = sysUserService.insertUserAndUserRole(model);
        } catch (RollbackableBizException e) {
            return new UserResponse(renderError(Code.FAIL));
        } catch (Exception e) {
            return new UserResponse(renderError(Code.FAIL));
        }

        if(Code.SUCCESS.equals(message)){
            UserResponse response = new UserResponse();
            response.setMessage(SAVE_SUCCESS);
            SysUserModel newModel = new SysUserModel();
            newModel.setU_id(model.getU_id());
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
//    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    private BaseResponse update(SysUserModel model) {
        if(model.getU_id() == null){
            return renderError(Code.FAIL, MessageConstants.PRIMARY_KEY_CAN_NOT_BE_NULL);
        }
        if(model.getRef_p_id() == null){
            return renderError(Code.FAIL, MessageConstants.PROJECT_ID_CAN_NOT_BE_NULL);
        }
        if(StringUtil.isBlank(model.getU_logname())){
            return renderError(Code.FAIL, MessageConstants.USER_LOG_NAME_CAN_NOT_BE_NULL);
        }
        if(model.getU_status() == null){
            return renderError(Code.FAIL, MessageConstants.STATUS_CAN_NOT_BE_NULL);
        }

        //同一项目id对应的登录名不应该有重复
        SysUserPageQueryParameter parameter = new SysUserPageQueryParameter();
        parameter.setU_id(model.getU_id());//排除此uid的
        parameter.setRef_p_id(model.getRef_p_id());
        parameter.setU_logname(model.getU_logname());
        int count =	sysUserService.findCountOfUlogname(parameter);
        if(count > 0){
            return renderError(Code.FAIL, MessageConstants.USER_NAME_EXIST);
        }

        String message = null;
        try {
            message = sysUserService.updateUserAndUserRole(model);
        } catch (RollbackableBizException e) {
            return renderError(Code.FAIL);
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
     * @param ids
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

        parameter.setRef_p_id(getCurrentProjectId());

        //查询用户中是否存在管理员账户
        List<SysUserModel> list = sysUserService.findAdminUser(parameter);

        if(NullUtil.notNull(list)){
            StringBuffer s = new StringBuffer();
            s.append("用户");
            for (int i = 0; i < list.size(); i++) {
                SysUserModel model = list.get(i);
                String name = model.getU_logname();
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
        } catch (RollbackableBizException e) {
            return renderError(Code.FAIL);
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

    /**
     * 删除（逻辑删除，状态修改为1）/启用（态修改为0）
     * @param userModel - 信息Model
     * @return BaseResponse
     */
//    @RequestMapping(value = "/updatestatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    private BaseResponse updateStatus(SysUserModel userModel) {
        String message = sysUserService.updateStatus(userModel);
        if(Code.SUCCESS.equals(message)){
            return renderSuccess();
        }else{
            log.error("修改状态失败!" );
            return renderError(Code.FAIL);
        }
    }

    /**
     * 分页查询
     * @param parameter - 信息查询参数
     * @return ListResponse
     */
//    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    private ListResponse list(SysUserPageQueryParameter parameter) {
        PageQueryResult result = sysUserService.findByPage(parameter);
        ListResponse listResponse = new ListResponse(result.getResultList(), result.getTotal());
        return listResponse;
    }

    /**
     * 根据id查询用户
     * @param u_id - 用户的id
     * @return UserResult
     */
//    @RequestMapping(value = "/findbyid", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    private UserResponse findById(Integer u_id){
        SysUserModel model = sysUserService.findByPrimaryKey(u_id);
        return new UserResponse(model);
    }

    /**
     * 根据id查询模块菜单列表
     * @param userid - id
     * @return ListResponse
     */
//    @RequestMapping(value = "/querymodulemenubyuserid", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    private ListResponse queryModuleMenuByUserId(Integer userid){
        ListResponse listResponse = new ListResponse();
        List<ModuleMenuResponse> list = sysUserService.findModuleMenuByUserId(userid);
        listResponse.setList(list);
        return listResponse;
    }

    /**
     * 修改密码
     * @param model - 修改密码参数
     * @return BaseResponse
     */
//    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    private UserResponse resetPassword0(SysUserModel model) {
//        if(model.getRef_p_id() == null){
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.PROJECT_ID_CAN_NOT_BE_NULL));
//        }
//        if(StringUtil.isBlank(model.getU_password())){
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.USER_PASSWORD_CAN_NOT_BE_NULL));
//        }
//        if(model.getU_id() == null){
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL));
//        }
//
//        String message = sysUserService.updatePasswordByAdmin(model);
//        if(Code.SUCCESS.equals(message)){
//            UserResponse response = new UserResponse();
//            response.setMessage(SAVE_SUCCESS);
//            response.setData(model);//使用model将修改前密码返回
//            return response;
//        }else{
//            log.error(MessageConstants.PASSWORD_UPDATE_FAILED);
//            return new UserResponse(renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED));
//        }
//    }

    /**
     * 修改密码
     * @param parameter - 修改密码参数
     * @return BaseResponse
     */
//    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    private BaseResponse updatePassword(ChangePasswordParameter parameter) {
//        if(parameter.getUid() == null){
//            return renderError(Code.FAIL, MessageConstants.PRM_USER_ID_CAN_NOT_BE_NULL);
//        }
//        if(parameter.getPid() == null){
//            return renderError(Code.FAIL, MessageConstants.PROJECT_ID_CAN_NOT_BE_NULL);
//        }
//
//        //旧密码
//        String oldPassword = parameter.getOldPassword();
//        //新密码
//        String newPassword = parameter.getNewPassword();
//        //新密码确认
//        String newPasswordConfirm = parameter.getNewPasswordConfirm();
//        //原始密码不能为空
//        if(StringUtil.isBlank(oldPassword)){
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_OLD_CAN_NOT_BE_NULL);
//        }
//        //新密码不能为空
//        if(StringUtil.isBlank(newPassword)){
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_NEW_CAN_NOT_BE_NULL);
//        }
//        //确认密码不能为空
//        if(StringUtil.isBlank(newPasswordConfirm)){
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_NEW_CONFIRM_CAN_NOT_BE_NULL);
//        }
//        //新密码与确认密码不相同
//        if(!newPassword.equals(newPasswordConfirm)){
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_NOT_EQUALS);
//        }
//        //新密码与旧密码不能相同
//        if(newPassword.equals(oldPassword)){
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_CAN_NOT_EQUALS);
//        }
//        //修改成新密码
//        String message = sysUserService.updatePassword(parameter);
//        if(Code.SUCCESS.equals(message)){
//            return renderSuccess();
//        }else{
//            log.error(MessageConstants.PASSWORD_UPDATE_FAILED);
//            return renderError(Code.FAIL, MessageConstants.PASSWORD_UPDATE_FAILED);
//        }
//    }

}
