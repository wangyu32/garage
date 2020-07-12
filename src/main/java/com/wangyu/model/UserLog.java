package com.wangyu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "lg_id", type = IdType.AUTO)
    private Integer lgId;

    /**
     * 功能菜单
     */
    private String lgMenu;

    /**
     * 操作类型(需细分)
     */
    private String lgType;

    /**
     * base库的user表id
     */
    private Integer refUId;

    /**
     * 对象ID（多个用下划线分隔）
     */
    private String lgObjId;

    /**
     * 对象描述
     */
    private String lgObjDesc;

    /**
     * 操作ip
     */
    private String lgIp;

    /**
     * 备注
     */
    private String lgDesc;

    /**
     * 创建时间
     */
    private LocalDateTime lgCreatetime;


}
