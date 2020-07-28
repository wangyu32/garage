package com.wangyu.controller;

import com.wangyu.common.ListResponse;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.CostTypeQueryParameter;
import com.wangyu.service.ICostTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 消费类型表 前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2020-07-28
 */
@Slf4j
@Controller
@RequestMapping("/costtype")
public class CostTypeController extends BaseController {

    //对应菜单名
    private static final String MENU_NAME = "消费管理";

    @Autowired
    private ICostTypeService costTypeService;


    /**
     * 菜单进入
     * @return 跳转页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String list() {
        return "system/costtype/list";
    }

    /**
     * 分页查询
     * @param parameter - 分页查询参数
     * @return String
     */
    @RequestMapping(value = "/datalist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ListResponse dataList(CostTypeQueryParameter parameter) {
        log.info("消费类型查询，parameter={}", toJson(parameter));
        PageQueryResult result = costTypeService.findByPage(parameter);
        ListResponse listResponse = new ListResponse(result.getResultList(), result.getTotal());
        return listResponse;
    }
}
