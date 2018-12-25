package com.wangyu.garage.parameter;

import lombok.Data;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/25 17:37
 */
@Data
public class UserStopRecordingQueryParameter extends StopRecordingQueryParameter {

//    private Long userid;

    private String name;

    private Integer sex;

//    private String phone;

    private Integer type
            ;
}
