package com.gxc15090111.garage.parameter;

import lombok.Data;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/25 17:37
 */
@Data
public class UserStopRecordingQueryParameter extends StopRecordingQueryParameter {

//    private Long userid;

    private String name;

    private Integer sex;

//    private String phone;

    private Integer type;

    /**
     * 车位编码
     */
    private String code;

    /**
     * 车位层级
     */
    private Integer level;
}
