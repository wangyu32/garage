package com.wangyu.garage.enums;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/5 1:30
 */
public enum SexEnum {

    MALE("男", 0),
    FEMALE("女", 1);

    private String name;
    private Integer code;

    SexEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static SexEnum getByCode(Integer code) {
        for (SexEnum e : SexEnum.values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }

    public static boolean idValid(Integer code) {
        return getByCode(code) != null;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

}
