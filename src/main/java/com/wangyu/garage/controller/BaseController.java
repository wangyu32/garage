package com.wangyu.garage.controller;

import com.google.gson.Gson;
import com.wangyu.common.Result;
import com.wangyu.common.validate.ValidateResult;
import com.wangyu.prm.common.BaseResponse;
import com.wangyu.prm.constant.SessionAttributeConstants;
import com.wangyu.prm.response.UserLoginResponse;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/2 17:10
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

    /**
     * Http的request请求
     */
    protected HttpServletRequest request;

    /**
     * Http的response响应
     */
    protected HttpServletResponse response;

    protected Gson gson = new Gson();

    /**
     * 默认处理 Request 和 Response 对象
     * @param request
     * @param response
     */
    @ModelAttribute
    protected void setReqAndRes(HttpServletRequest request,HttpServletResponse response){
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
     * 将http请求结果转为json
     * @param response - http请求返回结果
     * @return json格式信息
     */
    protected String responseToJson(BaseResponse response){
        Gson gson = new Gson();
        return gson.toJson(response);
    }

    /**
     * 将http请求结果转为json
     * @param response - http请求返回结果
     * @param gson - Gson
     * @return json格式信息
     */
    protected String responseToJson(BaseResponse response, Gson gson){
        if(gson == null){
            return responseToJson(response);
        }
        return gson.toJson(response);
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
}
