package com.gxc15090111.garage.parameter;

import com.gxc15090111.system.page.PageQueryParameter;

/**
 * @Description
 * @Author gxc15090111
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
