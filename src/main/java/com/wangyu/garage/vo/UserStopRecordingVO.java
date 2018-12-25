package com.wangyu.garage.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 用户停车记录
 * @Author wangyu
 * @Date 2018/12/25
 */

@Data
public class UserStopRecordingVO {

    /**
     * 用户ID
     */
    private Long userid;

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
     * 车库ID
     */
    private Long garageid;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 进入车库时间
     */
    private Date intime;

    /**
     * 离开车库时间
     */
    private Date outtime;

    /**
     * 总共停车时间，单位秒
     */
    private Long totaltime;

    /**
     * 停车单价
     */
    private BigDecimal price;

    /**
     * 停车费
     */
    private BigDecimal amount;

    /**
     * 状态0-入库;1-出库
     */
    private Integer status;

}
