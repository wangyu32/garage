package com.wangyu.garage.vo;

import com.wangyu.garage.entity.Garage;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/27 20:33
 */
public class GarageVO extends Garage {

    /**
     * 计费单位，多少秒为一个计费单位
     */
    private Long unit;

    /**
     * 计费单位名称
     */
    private String uname;

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
