package com.gxc15090111.garage.vo;

import com.gxc15090111.garage.entity.Garage;

/**
 * @Description
 * @Author gxc15090111
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
