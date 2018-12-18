package com.wangyu.prm.parameter;

import com.wangyu.prm.page.PageQueryParameter;

/**
 * 项目信息查询参数
 * @author 	wangyu wangyu@joygo.com 2016年10月15日
 *
 */
public class ProjectPageQueryParameter extends PageQueryParameter {
	
	/**
	 * 项目id
	 */
	private Integer p_id;
	
	/**
	 * 项目名称
	 */
	private String p_name = "";
	
	/**
	 * 项目appid
	 */
	private String p_appid = "";
	
	/**
	 * 项目状态（0-正常；1-暂停）
	 */
	private Integer p_status;

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
		this.p_name = p_name;
	}

	public String getP_appid() {
		return p_appid;
	}

	public void setP_appid(String p_appid) {
		this.p_appid = p_appid;
	}

	public Integer getP_status() {
		return p_status;
	}

	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}

}
