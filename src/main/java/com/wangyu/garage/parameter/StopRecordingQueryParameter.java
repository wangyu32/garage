package com.wangyu.garage.parameter;

import com.wangyu.system.page.PageQueryParameter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 停车记录查询参数
 * @Author wangyu
 * @Date 2018/12/6 19:22
 */
@Data
public class StopRecordingQueryParameter extends PageQueryParameter {

    /**
     * 车库ID
     */
    private Long garageid;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 用户ID数组
     */
    private Long[] useridArray;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车主电话
     */
    private String phone;

    /**
     * 进入车库时间 - 开始时间
     */
    private String intimeStart;

    /**
     * 进入车库时间 - 结束时间
     */
    private String intimeEnd;

    /**
     * 离开车库时间 - 开始时间
     */
    private String outtimeStart;

    /**
     * 离开车库时间 - 结束时间
     */
    private String outtimeEnd;

    /**
     * 停车费
     */
    private BigDecimal amount;

    /**
     * 状态0-入库;1-出库
     */
    private Integer status;


}
