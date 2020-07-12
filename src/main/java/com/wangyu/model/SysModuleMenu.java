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
 * 菜单表
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysModuleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模块id
     */
    private Integer mid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 排序号
     */
    private Integer order;

    /**
     * 描述
     */
    private String desc;

    /**
     * 状态（0-正常；1-停用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;


}
