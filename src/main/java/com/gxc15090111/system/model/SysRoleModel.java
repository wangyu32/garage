package com.gxc15090111.system.model;

import java.util.Date;

/**
 * 角色信息Model
 * @author 	wangyu wangyu@joygo.com 2016年10月16日
 *
 */
public class SysRoleModel {
	
	/**
	 * 菜单ID数组(role表外字段)，为保存角色同时保存关联菜单
	 */
	private String[] mm_id_array;
	
	/* live_base库role表属性 begin	*/
	/**
	 * 角色id
	 */
    private Integer r_id;
	
	/**
	 * 项目id
	 */
    private Integer ref_p_id;
	
	/**
	 * 角色名称
	 */
    private String r_name;
	
	/**
	 * 名称描述
	 */
    private String r_desc;
	
	/**
	 * 角色状态（0-正常；1-停用）
	 */
    private Integer r_status;
	
	/**
	 * 创建时间
	 */
    private Date r_createtime;
	
	/**
	 * 修改时间
	 */
    private Date r_updatetime;
    /* live_base库role表属性 end	*/

    /**
     * 数据状态 =0正常状态 =1删除状态
     */
    private Integer r_del;
    
    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public Integer getRef_p_id() {
        return ref_p_id;
    }

    public void setRef_p_id(Integer ref_p_id) {
        this.ref_p_id = ref_p_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name == null ? null : r_name.trim();
    }

    public String getR_desc() {
        return r_desc;
    }

    public void setR_desc(String r_desc) {
        this.r_desc = r_desc == null ? null : r_desc.trim();
    }

    public Integer getR_status() {
        return r_status;
    }

    public void setR_status(Integer r_status) {
        this.r_status = r_status;
    }

    public Date getR_createtime() {
        return r_createtime;
    }

    public void setR_createtime(Date r_createtime) {
        this.r_createtime = r_createtime;
    }

    public Date getR_updatetime() {
        return r_updatetime;
    }

    public void setR_updatetime(Date r_updatetime) {
        this.r_updatetime = r_updatetime;
    }

	public String[] getMm_id_array() {
		return mm_id_array;
	}

	public void setMm_id_array(String[] mm_id_array) {
		this.mm_id_array = mm_id_array;
	}

	public Integer getR_del() {
		return r_del;
	}

	public void setR_del(Integer r_del) {
		this.r_del = r_del;
	}
    
}