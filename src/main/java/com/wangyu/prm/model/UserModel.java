package com.wangyu.prm.model;

import java.util.Date;

/**
 * 用户信息Model
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 下午1:55:37
 *
 */
public class UserModel {
	
	/**
	 * 角色ID数组(user表外字段)，为保存用户同时保存关联角色
	 */
	private String[] r_id_array;
	
	/**
	 * 用户所属项目域名
	 */
	private String p_domain;
	
	/* live_base库 user表字段 	begin*/
	/**
	 * 用户id
	 */
    private Integer u_id;
	
	/**
	 * 项目id
	 */
    private Integer ref_p_id;
	
	/**
	 * 登录名
	 */
    private String u_logname;
    
    /**
	 * 真实姓名
	 */
    private String u_realname;

    /**
	 * 密码
	 */
    private String u_password;

    /**
	 * 电子邮箱
	 */
    private String u_email;

    /**
	 * 移动电话
	 */
    private String u_mobilephone;

    /**
	 * 最后登录时间
	 */
    private Date u_lastlogintime;

    /**
	 * 用户状态（0-正常；1-停用）
	 */
    private Integer u_status;

    /**
     * 是否管理员（0-不是；1-是）
     */
    private Integer u_isadmin;

    /**
	 * 创建时间
	 */
    private Date u_createtime;

    /**
	 * 修改时间
	 */
    private Date u_updatetime;
    /* live_base库 user表字段 	begin*/
    
    /**
     * 数据状态 =0正常状态 =1删除状态
     */
    private Integer u_del;
    
    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getRef_p_id() {
        return ref_p_id;
    }

    public void setRef_p_id(Integer ref_p_id) {
        this.ref_p_id = ref_p_id;
    }

    public String getU_logname() {
        return u_logname;
    }

    public void setU_logname(String u_logname) {
        this.u_logname = u_logname == null ? null : u_logname.trim();
    }

    public String getU_realname() {
        return u_realname;
    }

    public void setU_realname(String u_realname) {
        this.u_realname = u_realname == null ? null : u_realname.trim();
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password == null ? null : u_password.trim();
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email == null ? null : u_email.trim();
    }

    public String getU_mobilephone() {
        return u_mobilephone;
    }

    public void setU_mobilephone(String u_mobilephone) {
        this.u_mobilephone = u_mobilephone == null ? null : u_mobilephone.trim();
    }

    public Date getU_lastlogintime() {
        return u_lastlogintime;
    }

    public void setU_lastlogintime(Date u_lastlogintime) {
        this.u_lastlogintime = u_lastlogintime;
    }

    public Integer getU_status() {
        return u_status;
    }

    public void setU_status(Integer u_status) {
        this.u_status = u_status;
    }

    public Date getU_createtime() {
        return u_createtime;
    }

    public void setU_createtime(Date u_createtime) {
        this.u_createtime = u_createtime;
    }

    public Date getU_updatetime() {
        return u_updatetime;
    }

    public void setU_updatetime(Date u_updatetime) {
        this.u_updatetime = u_updatetime;
    }

	public String[] getR_id_array() {
		return r_id_array;
	}

	public void setR_id_array(String[] r_id_array) {
		this.r_id_array = r_id_array;
	}

	public Integer getU_isadmin() {
		return u_isadmin;
	}

	public void setU_isadmin(Integer u_isadmin) {
		this.u_isadmin = u_isadmin;
	}

	public Integer getU_del() {
		return u_del;
	}

	public void setU_del(Integer u_del) {
		this.u_del = u_del;
	}

	public String getP_domain() {
		return p_domain;
	}

	public void setP_domain(String p_domain) {
		this.p_domain = p_domain;
	}
	
}