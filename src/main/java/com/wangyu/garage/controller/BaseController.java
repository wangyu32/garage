package com.wangyu.garage.controller;

import com.wangyu.garage.common.Result;
import com.wangyu.garage.common.ValidateResult;

import java.util.HashMap;
import java.util.Map;

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
}
