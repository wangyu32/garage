package com.gxc15090111.garage.dto;

import java.math.BigDecimal;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/27 21:41
 */
public class GarageDTO {


    /**
     * 主键ID
     */
    private Long id;

    /**
     * 车库名
     */
    private String name;

    /**
     * 总停车位数
     */
    private Integer total;

    /**
     * 已使用停车位数
     */
    private Integer inuse;

    /**
     * 空闲停车位数
     */
    private Integer unuse;

    /**
     * 每小时单价
     */
    private BigDecimal price;

    /**
     * 计费单位ID
     */
    private Integer priceUnitId;

    /**
     * 服务ip
     */
    private String serverIp;

    /**
     * 服务端口
     */
    private Integer serverPort;


}
