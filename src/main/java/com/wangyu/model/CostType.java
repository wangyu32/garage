package com.wangyu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消费类型表
 * </p>
 *
 * @author wangyu
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CostType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消费类型
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消费类型
     */
    private String name;

    /**
     * 父类型ID
     */
    private Integer pid;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;


}
