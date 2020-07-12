package com.wangyu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 状态（0-正常；1-停用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 数据状态 =0正常状态 =1删除状态
     */
    private Boolean del;


}
