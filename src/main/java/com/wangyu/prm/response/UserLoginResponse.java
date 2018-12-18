package com.wangyu.prm.response;

import com.wangyu.prm.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录用户的Response
 * @author 	wangyu	wangyu@joygo.com 2016年12月4日 上午12:56:02
 *
 */
public class UserLoginResponse extends BaseResponse {

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
    private String u_logname = "";
    
    /**
	 * 真实姓名
	 */
    private String u_realname = "";

    /**
	 * 密码
	 */
    private String u_password = "";

    /**
	 * 用户状态（0-正常；1-停用）
	 */
    private Integer u_status;
	
    /**
     * 项目域名
     */
    private String p_domain = "";
    
    /**
     * 钻石单位（如：钻石）
     */
    private String u_coinunit = "";
    
    /**
     * 荔枝单位（如：荔枝、青春币）
     */
    private String u_giftunit = "";
    
    /**
     * 是否管理员（0-不是；1-是）
     */
    private Integer u_isadmin;
    
	/**
	 * 用户菜单，包括模块，菜单
	 */
	private List<ModuleMenuResponse> menus = new ArrayList<ModuleMenuResponse>();
	
	public UserLoginResponse() {
		super();
	}
	
	public UserLoginResponse(String code, String message) {
		super(code, message);
	}
	

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
		this.u_logname = u_logname;
	}

	public String getU_realname() {
		return u_realname;
	}

	public void setU_realname(String u_realname) {
		this.u_realname = u_realname;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public Integer getU_status() {
		return u_status;
	}

	public void setU_status(Integer u_status) {
		this.u_status = u_status;
	}

	public List<ModuleMenuResponse> getMenus() {
		return menus;
	}

	public void setMenus(List<ModuleMenuResponse> menus) {
		this.menus = menus;
	}

	public UserLoginResponse setCode(String code){
		super.setCode(code);
		return this;
	}

	public UserLoginResponse setMessage(String message){
		super.setMessage(message);
		return this;
	}

	public String getP_domain() {
		return p_domain;
	}

	public void setP_domain(String p_domain) {
		this.p_domain = p_domain;
	}

	public String getU_coinunit() {
		return u_coinunit;
	}

	public void setU_coinunit(String u_coinunit) {
		this.u_coinunit = u_coinunit;
	}

	public String getU_giftunit() {
		return u_giftunit;
	}

	public void setU_giftunit(String u_giftunit) {
		this.u_giftunit = u_giftunit;
	}

	public Integer getU_isadmin() {
		return u_isadmin;
	}

	public void setU_isadmin(Integer u_isadmin) {
		this.u_isadmin = u_isadmin;
	}
	
	
}
