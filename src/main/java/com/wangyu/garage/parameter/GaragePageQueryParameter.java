package com.wangyu.garage.parameter;

import com.wangyu.system.page.PageQueryParameter;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/24 14:40
 */
public class GaragePageQueryParameter extends PageQueryParameter {

    /**
     * 车库名
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
