package com.wangyu.entity.parameter;

import com.wangyu.entity.page.PageQueryParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户操作日志查询参数
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class UserLogPageQueryParameter extends PageQueryParameter {
	
	/**
	 * 用户名
	 */
	private String u_logname = "";
	
	/**
	 * 后台用户id
	 */
    private Integer ref_u_id;

    /**
     * 后台用户id数组
     */
    private String[] ref_u_id_array;
    
    /**
     * 功能菜单
     */
    private String lg_menu = "";
    
	/**
	 * 操作类型
	 */
    private String lg_type = "";

    /**
     * 对象ID（多个用下划线分隔）
     */
    private String lg_obj_id = "";

    /**
     * 对象描述
     */
    private String lg_obj_desc = "";
    
    /**
     * 操作ip
     */
    private String lg_ip = "";

    /**
     * 操作时间-开始时间
     */
    private String lg_createtime_begin = "";
    
    /**
     * 操作时间-结束时间
     */
    private String lg_createtime_end = "";

    /**
     * 备注
     */
    private String lg_desc = "";

	public String getU_logname() {
		return u_logname;
	}

	public void setU_logname(String u_logname) {
		this.u_logname = u_logname;
	}

	public Integer getRef_u_id() {
		return ref_u_id;
	}

	public void setRef_u_id(Integer ref_u_id) {
		this.ref_u_id = ref_u_id;
	}

	public String getLg_type() {
		return lg_type;
	}

	public void setLg_type(String lg_type) {
		this.lg_type = lg_type;
	}

	public String getLg_obj_id() {
		return lg_obj_id;
	}

	public void setLg_obj_id(String lg_obj_id) {
		this.lg_obj_id = lg_obj_id;
	}

	public String getLg_obj_desc() {
		return lg_obj_desc;
	}

	public void setLg_obj_desc(String lg_obj_desc) {
		this.lg_obj_desc = lg_obj_desc;
	}

	public String getLg_ip() {
		return lg_ip;
	}

	public void setLg_ip(String lg_ip) {
		this.lg_ip = lg_ip;
	}

	public String getLg_createtime_begin() {
		return lg_createtime_begin;
	}

	public void setLg_createtime_begin(String lg_createtime_begin) {
		this.lg_createtime_begin = lg_createtime_begin;
	}

	public String getLg_createtime_end() {
		return lg_createtime_end;
	}

	public void setLg_createtime_end(String lg_createtime_end) {
		this.lg_createtime_end = lg_createtime_end;
	}

	public String getLg_desc() {
		return lg_desc;
	}

	public void setLg_desc(String lg_desc) {
		this.lg_desc = lg_desc;
	}

	public String[] getRef_u_id_array() {
		return ref_u_id_array;
	}

	public void setRef_u_id_array(String[] ref_u_id_array) {
		this.ref_u_id_array = ref_u_id_array;
	}

	public String getLg_menu() {
		return lg_menu;
	}

	public void setLg_menu(String lg_menu) {
		this.lg_menu = lg_menu;
	}
	
}
