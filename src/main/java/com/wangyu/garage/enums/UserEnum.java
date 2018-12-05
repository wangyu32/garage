package com.wangyu.garage.enums;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/5 20:31
 */
public enum UserEnum {

    COMMON("普通用户", 0),
    MEMBERSHIP("会员", 1);

    private String name;
    private Integer type;

    UserEnum(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public static UserEnum getByType(Integer type) {
        for (UserEnum e : UserEnum.values()) {
            if (e.type == type) {
                return e;
            }
        }
        return null;
    }

    public static boolean idValid(Integer type) {
        return getByType(type) != null;
    }

    public Integer getType() {
        return type;
    }
}
