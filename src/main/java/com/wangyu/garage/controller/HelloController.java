package com.wangyu.garage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/11 22:02
 */
@Controller
@RequestMapping(value = "/view")
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }


}
