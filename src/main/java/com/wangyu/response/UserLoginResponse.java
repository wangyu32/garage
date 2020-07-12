package com.wangyu.response;

import com.wangyu.common.BaseResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录用户的Response
 * @author 	wangyu
 *
 */
@Data
public class UserLoginResponse extends BaseResponse {

	/**
	 * 用户id
	 */
    private Integer uId;

	/**
	 * 登录名
	 */
    private String uLogname = "";
    
    /**
	 * 真实姓名
	 */
    private String uRealname = "";

    /**
	 * 密码
	 */
    private String uPassword = "";

    /**
	 * 用户状态（0-正常；1-停用）
	 */
    private Integer uStatus;
	
    /**
     * 是否管理员（0-不是；1-是）
     */
    private Integer uIsadmin;
    
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


	public UserLoginResponse setCode(String code){
		super.setCode(code);
		return this;
	}

	public UserLoginResponse setMessage(String message){
		super.setMessage(message);
		return this;
	}

}
