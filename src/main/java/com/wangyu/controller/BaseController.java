package com.wangyu.controller;

import com.google.gson.Gson;
import com.wangyu.common.*;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.constant.CommonConstants;
import com.wangyu.constant.SessionAttributeConstants;
import com.wangyu.response.UserLoginResponse;
import com.wangyu.service.IUserLogService;
import com.wangyu.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wangyu
 */
public class BaseController {

    public static final String SUCCESS_FLAG = "success";
    public static final String SUCCESS_CODE = "0";
    public static final String ERROR_CODE = "9999";
    public static final String SUCCESS_MESSAGE = "请求成功！";
    public static final String ERROR_MESSAGE = "请求失败！";
    public static final String REQUEST_PARAMETER_ERROR = "请求参数错误！";
    public static final String RETURN_CODE_KEY = "returncode";
    public static final String RETURN_MESSAGE_KEY = "returnMsg";
    public static final String RETURN_DATA_KEY = "data";

    protected static final String SAVE_SUCCESS = "保存成功";
    protected static final String UPDATE_SUCCESS = "修改成功";
    protected static final String DELETE_SUCCESS = "删除成功";

    /**
     * Http的request请求
     */
    protected HttpServletRequest request;

    /**
     * Http的response响应
     */
    protected HttpServletResponse response;

    protected Gson gson = new Gson();

    @Autowired
    private IUserLogService userLogService;

    /**
     * 默认处理 Request 和 Response 对象
     * @param request
     * @param response
     */
    @ModelAttribute
    protected void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public static Result success() {
        return Result.success();
    }

    public static Result success(String message) {
        return Result.success(message);
    }

    public static Result success(Object data) {
        return Result.success(data);
    }

    public static Result success(String message, Object data) {
        return Result.success(message, data);
    }

    public static Result failed() {
        return Result.failed();
    }

    public static Result failed(String message) {
        return Result.failed(message);
    }

    public static Result failed(Object data) {
        return Result.failed(data);
    }

    public static Result failed(ValidateResult validateResult) {
        return Result.failed(validateResult.getErrorMessage());
    }

    /**
     * 提示前端错误信息
     * @param message - 错误信息
     * @return json格式信息
     */
    protected String errorMessage(String message){
        Gson gson = new Gson();
        BaseResponse response = renderError(Code.FAIL, message);
        return gson.toJson(response);
    }

    /**
     * http请求的错误统一返回
     * @return json格式信息
     */
    protected String httpError(){
        BaseResponse response = renderError(Code.FAIL);
        return gson.toJson(response);
    }

//    public static Map<String, Object> getRequstParameterErrorMessage() {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
//        rsMap.put(SUCCESS_FLAG, Boolean.FALSE);
//        rsMap.put(RETURN_CODE_KEY, ERROR_CODE);
//        rsMap.put(RETURN_MESSAGE_KEY, REQUEST_PARAMETER_ERROR);
//        return rsMap;
//    }
//
//    public static Map<String, Object> getErrorMessage(String returnMessage) {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
//        rsMap.put(SUCCESS_FLAG, Boolean.FALSE);
//        rsMap.put(RETURN_CODE_KEY, ERROR_CODE);
//        rsMap.put(RETURN_MESSAGE_KEY, returnMessage == null ? ERROR_MESSAGE : returnMessage);
//        return rsMap;
//    }
//
//    public static Map<String, Object> getErrorMessage(String returnCode, String returnMessage) {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
//        rsMap.put(SUCCESS_FLAG, Boolean.FALSE);
//        rsMap.put(RETURN_CODE_KEY, returnCode);
//        rsMap.put(RETURN_MESSAGE_KEY, returnMessage == null ? ERROR_MESSAGE : returnMessage);
//        return rsMap;
//    }
//
//    public static Map<String, Object> getSuccessMessage() {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
//        rsMap.put(SUCCESS_FLAG, Boolean.TRUE);
//        rsMap.put(RETURN_CODE_KEY, SUCCESS_CODE);
//        rsMap.put(RETURN_MESSAGE_KEY, SUCCESS_MESSAGE);
//        return rsMap;
//    }
//
//    public static Map<String, Object> getSuccessMessage(String returnMessage) {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
//        rsMap.put(SUCCESS_FLAG, Boolean.TRUE);
//        rsMap.put(RETURN_CODE_KEY, SUCCESS_CODE);
//        rsMap.put(RETURN_MESSAGE_KEY, returnMessage == null ? SUCCESS_MESSAGE : returnMessage);
//        return rsMap;
//    }
//
//    public static Map<String, Object> getSuccessMessage(Object data) {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
//        rsMap.put(SUCCESS_FLAG, Boolean.TRUE);
//        rsMap.put(RETURN_CODE_KEY, SUCCESS_CODE);
//        rsMap.put(RETURN_MESSAGE_KEY, SUCCESS_MESSAGE);
//        rsMap.put(RETURN_DATA_KEY, data);
//        return rsMap;
//    }

    /**
     * 获取登录用户id
     * @return 用户id
     */
    protected UserLoginResponse getCurrentUser(){
        UserLoginResponse userInfo = (UserLoginResponse)request.getSession().getAttribute(SessionAttributeConstants.CURRENT_USER);
        return userInfo;
    }

    /**
     * 获取登录用户id
     * @return 用户id
     */
    protected Integer getCurrentUserId(){
        return getCurrentUser().getUId();
    }

    /**
     * 获取登录用户id
     * @return 用户id
     */
    protected String getCurrentUserIdStr(){
        return String.valueOf( getCurrentUserId() );
    }

    /**
     * 获取当前Session的ID
     * @return
     */
    protected String getSessionid(){
        return request.getSession().getId();
    }

    /**
     * 将value,以name为key放到session中
     * @param name - key
     * @param value - value值
     */
    protected void setSessionAttribute(String name, Object value){
        request.getSession().setAttribute(name, value);
    }

    /**
     * 从sesion中获取name对应的value
     * @param name
     * @return
     */
    protected Object getSessionAttribute(String name){
        return request.getSession().getAttribute(name);
    }

    /**
     * 从sesion中移除name对应的value
     * @param name
     * @return
     */
    protected void removeSessionAttribute(String name){
        request.getSession().removeAttribute(name);
    }


    /**
     * 使用list分页
     * @param offset - 开始记录数
     * @param limit - 每页记录数
     * @param list - 数据集
     * @return ListResponse
     */
    protected ListResponse pageList(int offset, int limit, List list){
        ListResponse response = new ListResponse();
        if(offset < 0 || limit < 1 || NullUtil.isNull(list)){
            return response;
        }

        response.setTotal(list.size());
        List dataList = new ArrayList();
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if(i>=offset && index <= limit){
                dataList.add(list.get(i));
                index ++;
            }
        }
        response.setList(dataList);
        return response;
    }

    /**
     * 判断返response是否是处理成功的响应
     * @param response - BaseResponse或其子类
     * @return response的code等于200返回true,不等于200，返回false
     */
    protected boolean isSuccessResponse(BaseResponse response){
        if(Code.SUCCESS.equals(response.getCode())){
            return true;
        }
        return false;
    }

    /**
     * 判断json是否操作成功的json串
     * @param json - 调用http请求返回的json串
     * @return
     */
    protected boolean isSuccessJson(String json){
        Gson gson = new Gson();
        BaseResponse response = gson.fromJson(json, BaseResponse.class);
        if(Code.SUCCESS.equals(response.getCode())){
            return true;
        }
        return false;
    }

    /**
     * 当前登录用户是否是管理员级别
     * @return
     */
    protected boolean isAdminUser(){
        UserLoginResponse user = getCurrentUser();
        if(user.getUIsadmin() != null && CommonConstants.PRM_USER_IS_ADMIN == user.getUIsadmin()){
            return true;
        }
        return false;
    }

    /**
     * 判断状态是否是启用的
     * @param status - 状态值
     * @return 启用true, 禁用false
     */
    protected boolean isEnableStatus(int status){
        if(status == CommonConstants.COMMON_STATUS_NORMAL){
            return true;
        }
        return false;
    }

    /**
     * 判断状态是否是禁用的
     * @param status - 状态值
     * @return 启用true, 禁用false
     */
    protected boolean isDisableStatus(int status){
        if(status == CommonConstants.COMMON_STATUS_NOT_USE){
            return true;
        }
        return false;
    }

    /**
     * 将http请求结果转为json
     * @param response - http请求返回结果
     * @return json格式信息
     */
    protected String toJson(BaseResponse response){
        return gson.toJson(response);
    }

    /**
     * 响应操作成功
     * @return BaseResponse - 响应信息封装对象
     */
    public BaseResponse renderSuccess() {
        return new BaseResponse().setMessage("操作成功!");
    }

    /**
     * 响应操作成功
     * @param message - 响应信息
     * @return BaseResponse
     */
    public BaseResponse renderSuccess(String message) {
        return new BaseResponse().setMessage(message);
    }

    /**
     * 响应操作成功
     * @param data - 响应数据
     * @return BaseResponse
     */
    public BaseResponse renderSuccess(Object data){
        return new DataResponse().setData(data).setCode(Code.SUCCESS).setMessage("操作成功!");
    }

    /**
     * 服务器响应错误
     * @param code - 错误码
     * @return BaseResponse
     */
    public BaseResponse renderError(String code){
        return new BaseResponse().setCode(code);
    }

    /**
     * 服务器响应错误
     * @param code - 错误码
     * @param message - 响应信息
     * @return BaseResponse
     */
    public BaseResponse renderError(String code, String message){
        return new BaseResponse().setCode(code).setMessage(message);
    }

    /**
     * 将对象转换成json
     * @param o - 对象
     * @return json串
     */
    protected String toJson(Object o){
        return gson.toJson(o);
    }

//    /**
//     * 记录操作日志
//     * @param lg_menu - 菜单名称
//     * @param lg_type - 操作类型
//     * @param lg_obj_id - 对象ID
//     * @param lg_obj_desc - 对象描述
//     * @param lg_desc - 备注
//     */
//    protected void addLog(String lg_menu, String lg_type, String lg_obj_id, String lg_obj_desc, String lg_desc){
//        UserLogModel log = new UserLogModel();
//        log.setLg_menu(lg_menu);
//        log.setLg_type(lg_type);
//        log.setLg_obj_id(lg_obj_id);
//        log.setLg_obj_desc(lg_obj_desc);
//        log.setLg_desc(lg_desc);
//        log.setLg_ip(request.getRemoteHost());
//        log.setRef_u_id(getCurrentUserId());
//        userLogService.save(log);
//    }
//
//    /**
//     * 记录操作日志
//     * @param json 调用http请求的json，如果json为空，直接记日志；json不为空，验证json是否是操作成功的响应，如果是，记日志；如果不是，不需记日志
//     * @param lg_menu - 菜单名称
//     * @param lg_type - 操作类型
//     * @param lg_obj_id - 对象ID
//     * @param lg_obj_desc - 对象描述
//     * @param lg_desc - 备注
//     */
//    protected void addLog(String json, String lg_menu, String lg_type, String lg_obj_id, String lg_obj_desc, String lg_desc){
//        if(StringUtil.isBlank(json) || isSuccessJson(json)){
//            //json不为空或为操作成功的响应才记录日志
//            UserLogModel log = new UserLogModel();
//            log.setLg_menu(lg_menu);
//            log.setLg_type(lg_type);
//            log.setLg_obj_id(lg_obj_id);
//            log.setLg_obj_desc(lg_obj_desc);
//            log.setLg_desc(lg_desc);
//            log.setLg_ip(request.getRemoteHost());
//            log.setRef_u_id(getCurrentUserId());
//            userLogService.save(log);
//        }
//    }
}
