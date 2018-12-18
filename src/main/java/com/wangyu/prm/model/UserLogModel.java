package com.wangyu.prm.model;

import java.util.Date;

/**
 * 后台用户操作日志
 * @author 	wangyu@joygo.com 2017年3月8日
 *
 */
public class UserLogModel {
	
    /**
     * base库的user表u_logname
     */
    private String ref_u_logname;
	
    /**
     * 主键
     */
    private Integer lg_id;

    /**
     * 功能菜单
     */
    private String lg_menu;

    /**
     * 操作类型(需细分)
     */
    private String lg_type;

    /**
     * base库的user表id
     */
    private Integer ref_u_id;

    /**
     * 对象ID（多个用下划线分隔）
     */
    private String lg_obj_id;

    /**
     * 对象描述(对象的重要属性或其他比较重要的信息)
     */
    private String lg_obj_desc;

    /**
     * 备注（新增记录新值，修改一定要记录前值）
     */
    private String lg_desc;

    /**
     * 操作ip
     */
    private String lg_ip;

    /**
     * 创建时间
     */
    private Date lg_createtime;

    public Integer getLg_id() {
        return lg_id;
    }

    public void setLg_id(Integer lg_id) {
        this.lg_id = lg_id;
    }

    public String getLg_menu() {
        return lg_menu;
    }

    public void setLg_menu(String lg_menu) {
        this.lg_menu = lg_menu == null ? null : lg_menu.trim();
    }

    public String getLg_type() {
        return lg_type;
    }

    public void setLg_type(String lg_type) {
        this.lg_type = lg_type == null ? null : lg_type.trim();
    }

    public Integer getRef_u_id() {
        return ref_u_id;
    }

    public void setRef_u_id(Integer ref_u_id) {
        this.ref_u_id = ref_u_id;
    }

    public String getLg_obj_id() {
        return lg_obj_id;
    }

    public void setLg_obj_id(String lg_obj_id) {
        this.lg_obj_id = lg_obj_id == null ? null : lg_obj_id.trim();
    }

    public String getLg_ip() {
        return lg_ip;
    }

    public void setLg_ip(String lg_ip) {
        this.lg_ip = lg_ip == null ? null : lg_ip.trim();
    }

    public Date getLg_createtime() {
        return lg_createtime;
    }

    public void setLg_createtime(Date lg_createtime) {
        this.lg_createtime = lg_createtime;
    }

    public String getLg_desc() {
        return lg_desc;
    }

    public void setLg_desc(String lg_desc) {
        this.lg_desc = lg_desc == null ? null : lg_desc.trim();
    }

    public String getLg_obj_desc() {
        return lg_obj_desc;
    }

    public void setLg_obj_desc(String lg_obj_desc) {
        this.lg_obj_desc = lg_obj_desc == null ? null : lg_obj_desc.trim();
    }

	public String getRef_u_logname() {
		return ref_u_logname;
	}

	public void setRef_u_logname(String ref_u_logname) {
		this.ref_u_logname = ref_u_logname;
	}

	@Override
	public String toString() {
		return "UserLogModel [lg_id=" + lg_id + ", lg_menu=" + lg_menu + ", lg_type=" + lg_type + ", ref_u_id=" + ref_u_id + ", lg_obj_id="
				+ lg_obj_id + ", lg_ip=" + lg_ip + ", lg_createtime=" + lg_createtime + ", lg_desc=" + lg_desc + ", lg_obj_desc=" + lg_obj_desc + "]";
	}
    
}