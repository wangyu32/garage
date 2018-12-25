package com.wangyu.garage.controller;

import com.wangyu.common.Result;
import com.wangyu.system.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/22 18:28
 */
@Slf4j
@Controller
@RequestMapping(value = "/qccode")
public class QrCodeController extends BaseController {

    @Value("${server.port}")
    private String port;

    @Value("${server.context-path}")
    private String contextPath;

    @RequestMapping(value = "/comein", method = RequestMethod.GET)
    public Result getComeinQrCode() {
//        String url = "http://192.168.168.105:" + port + contextPath + "/garage/comein";
        String requstUrl = request.getRequestURL().toString();
        String url = requstUrl.replace("qccode","garage");
        try {
            QRCodeUtil.generateQRCode(url, 300, 300, "jpg", response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return failed("生成入库二维码失败");
    }

    @RequestMapping(value = "/comeout", method = RequestMethod.GET)
    public Result getComeoutQrCode() {
//        String url = "http://192.168.168.105:" + port + contextPath + "/garage/comeout";
        String requstUrl = request.getRequestURL().toString();
        String url = requstUrl.replace("qccode","garage");
        try {
            QRCodeUtil.generateQRCode(url, 300, 300, "jpg", response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return failed("生成入库二维码失败");
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
