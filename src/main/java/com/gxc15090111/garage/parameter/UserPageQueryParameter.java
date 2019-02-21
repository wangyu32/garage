package com.gxc15090111.garage.parameter;

import com.gxc15090111.system.page.PageQueryParameter;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/24 14:40
 */
public class UserPageQueryParameter extends PageQueryParameter {

    /**
     * 用户ID数组
     */
    private Long[] useridArray;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别0-男;1-女
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 类型0-固定;1-临时；2-管理员
     */
    private Integer type;

    /**
     * 创建时间-开始时间
     */
    private String startTime = "";

    /**
     * 结束时间-结束时间
     */
    private String endTime = "";

    public Long[] getUseridArray() {
        return useridArray;
    }

    public void setUseridArray(Long[] useridArray) {
        this.useridArray = useridArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
