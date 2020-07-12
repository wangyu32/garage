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
 * 模块表
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysModule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模块分类(1-项目;2-后台)
     */
    private Integer type;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块图片
     */
    private String image;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 描述
     */
    private String mDesc;

    /**
     * 状态（0-正常；1-停用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;


}
