package com.gxc15090111.system.model;

import java.util.Date;

/**
 * 项目信息Model
 * @author 	gxc15090111  2018年10月13日
 *
 */
public class ProjectModel {
	
	/**
	 * 项目id
	 */
    private Integer p_id;

    /**
     * 项目名称
     */
    private String p_name;

    /**
     * 项目域名
     */
    private String p_domain;

    /**
     * 项目 App Id
     */
    private String p_appid;

    /**
     * 项目关注
     */
    private String p_sercert;

    /**
     * 项目全名
     */
    private String p_org;

    /**
     * 项目描述
     */
    private String p_desc;

    /**
     * 项目Logo
     */
    private String p_logo;

    /**
     * 项目状态 0-正常；1-停用
     */
    private Integer p_status;

    /**
     * 项目创建时间
     */
    private Date p_createtime;

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name == null ? null : p_name.trim();
    }

    public String getP_domain() {
        return p_domain;
    }

    public void setP_domain(String p_domain) {
        this.p_domain = p_domain == null ? null : p_domain.trim();
    }

    public String getP_appid() {
        return p_appid;
    }

    public void setP_appid(String p_appid) {
        this.p_appid = p_appid == null ? null : p_appid.trim();
    }

    public String getP_sercert() {
        return p_sercert;
    }

    public void setP_sercert(String p_sercert) {
        this.p_sercert = p_sercert == null ? null : p_sercert.trim();
    }

    public String getP_org() {
        return p_org;
    }

    public void setP_org(String p_org) {
        this.p_org = p_org == null ? null : p_org.trim();
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc == null ? null : p_desc.trim();
    }

    public String getP_logo() {
        return p_logo;
    }

    public void setP_logo(String p_logo) {
        this.p_logo = p_logo == null ? null : p_logo.trim();
    }

    public Integer getP_status() {
        return p_status;
    }

    public void setP_status(Integer p_status) {
        this.p_status = p_status;
    }

    public Date getP_createtime() {
        return p_createtime;
    }

    public void setP_createtime(Date p_createtime) {
        this.p_createtime = p_createtime;
    }
}