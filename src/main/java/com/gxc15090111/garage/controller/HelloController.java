package com.gxc15090111.garage.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/11 22:02
 */
@Controller
@RequestMapping(value = "/view")
public class HelloController {

    @ApiOperation(value = "hello world", notes = "")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }


}
