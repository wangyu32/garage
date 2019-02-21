package com.gxc15090111.system.model;

import java.util.Date;

/**
 * 菜单Model
 * @author 	gxc15090111	 2018年10月17日 下午6:45:23
 *
 */
public class SysModuleMenuModel {

    /**
	 * 菜单id
	 */
    private Integer mm_id;

    /**
	 * 模块id
	 */
    private Integer ref_m_id;

    /**
	 * 菜单名称
	 */
    private String mm_name;

    /**
	 * 菜单地址
	 */
    private String mm_url;

    /**
	 * 菜单排序号
	 */
    private Integer mm_order;

    /**
	 * 菜单描述
	 */
    private String mm_desc;

    /**
	 * 菜单状态
	 */
    private Integer mm_status;

    /**
	 * 创建时间
	 */
    private Date mm_createtime;

    public Integer getMm_id() {
        return mm_id;
    }

    public void setMm_id(Integer mm_id) {
        this.mm_id = mm_id;
    }

    public Integer getRef_m_id() {
        return ref_m_id;
    }

    public void setRef_m_id(Integer ref_m_id) {
        this.ref_m_id = ref_m_id;
    }

    public String getMm_name() {
        return mm_name;
    }

    public void setMm_name(String mm_name) {
        this.mm_name = mm_name == null ? null : mm_name.trim();
    }

    public String getMm_url() {
        return mm_url;
    }

    public void setMm_url(String mm_url) {
        this.mm_url = mm_url == null ? null : mm_url.trim();
    }

    public Integer getMm_order() {
        return mm_order;
    }

    public void setMm_order(Integer mm_order) {
        this.mm_order = mm_order;
    }

    public String getMm_desc() {
        return mm_desc;
    }

    public void setMm_desc(String mm_desc) {
        this.mm_desc = mm_desc == null ? null : mm_desc.trim();
    }

    public Integer getMm_status() {
        return mm_status;
    }

    public void setMm_status(Integer mm_status) {
        this.mm_status = mm_status;
    }

    public Date getMm_createtime() {
        return mm_createtime;
    }

    public void setMm_createtime(Date mm_createtime) {
        this.mm_createtime = mm_createtime;
    }
}