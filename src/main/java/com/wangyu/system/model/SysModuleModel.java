package com.wangyu.system.model;

import java.util.Date;

/**
 * 菜单模块Model
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 下午6:43:40
 *
 */
public class SysModuleModel {

	/**
	 * 模块id
	 */
    private Integer m_id;

    /**
	 * 模块类型
	 */
    private Integer m_type;
    
    /**
	 * 模块名称
	 */
    private String m_name;

    /**
	 * 模块图片URL
	 */
    private String m_image;

    /**
	 * 模块排序号
	 */
    private Integer m_order;

    /**
	 * 模块描述
	 */
    private String m_desc;

    /**
	 * 模块状态
	 */
    private Integer m_status;

    /**
	 * 创建时间
	 */
    private Date m_createtime;

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public Integer getM_type() {
        return m_type;
    }

    public void setM_type(Integer m_type) {
        this.m_type = m_type;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name == null ? null : m_name.trim();
    }

    public String getM_image() {
        return m_image;
    }

    public void setM_image(String m_image) {
        this.m_image = m_image == null ? null : m_image.trim();
    }

    public Integer getM_order() {
        return m_order;
    }

    public void setM_order(Integer m_order) {
        this.m_order = m_order;
    }

    public String getM_desc() {
        return m_desc;
    }

    public void setM_desc(String m_desc) {
        this.m_desc = m_desc == null ? null : m_desc.trim();
    }

    public Integer getM_status() {
        return m_status;
    }

    public void setM_status(Integer m_status) {
        this.m_status = m_status;
    }

    public Date getM_createtime() {
        return m_createtime;
    }

    public void setM_createtime(Date m_createtime) {
        this.m_createtime = m_createtime;
    }
}