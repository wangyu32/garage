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
 * 操作员表
 * </p>
 *
 * @author wangyu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    private String logname;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 移动电话
     */
    private String mobilephone;

    /**
     * 最后登录时间
     */
    private Date lastlogintime;

    /**
     * 用户状态（0-正常；1-停用）
     */
    private Integer status;

    /**
     * 是否管理员 1-是（不允许删除），0-不是 
     */
    private Integer isadmin;

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
