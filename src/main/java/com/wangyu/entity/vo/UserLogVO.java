package com.wangyu.entity.vo;

import java.util.Date;

/**
 * 操作员日志VO
 * @author 	wangyu
 *
 */
public class UserLogVO {
	/**
	 * 主键id
	 */
    private Integer lg_id;

	/**
	 * 用户id
	 */
    private Integer ref_u_id;

    /**
     * 用户名
     */
    private String u_logname;

	/**
	 * 操作类型(login,logout,insert,update,delete,select)
	 */
    private String lg_type;

	/**
	 * 操作的表格
	 */
    private String lg_table;

	/**
	 * IP地址
	 */
    private String lg_ip;

	/**
	 * 操作时间
	 */
    private Date lg_createtime;

	/**
	 * 操作内容
	 */
    private String lg_content;

	public Integer getLg_id() {
		return lg_id;
	}

	public void setLg_id(Integer lg_id) {
		this.lg_id = lg_id;
	}

	public Integer getRef_u_id() {
		return ref_u_id;
	}

	public void setRef_u_id(Integer ref_u_id) {
		this.ref_u_id = ref_u_id;
	}

	public String getU_logname() {
		return u_logname;
	}

	public void setU_logname(String u_logname) {
		this.u_logname = u_logname;
	}

	public String getLg_type() {
		return lg_type;
	}

	public void setLg_type(String lg_type) {
		this.lg_type = lg_type;
	}

	public String getLg_table() {
		return lg_table;
	}

	public void setLg_table(String lg_table) {
		this.lg_table = lg_table;
	}

	public String getLg_ip() {
		return lg_ip;
	}

	public void setLg_ip(String lg_ip) {
		this.lg_ip = lg_ip;
	}

	public Date getLg_createtime() {
		return lg_createtime;
	}

	public void setLg_createtime(Date lg_createtime) {
		this.lg_createtime = lg_createtime;
	}

	public String getLg_content() {
		return lg_content;
	}

	public void setLg_content(String lg_content) {
		this.lg_content = lg_content;
	}
    
}
