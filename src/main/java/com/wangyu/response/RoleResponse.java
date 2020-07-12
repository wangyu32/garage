package com.wangyu.response;

import com.wangyu.common.BaseResponse;
import com.wangyu.model.SysRole;

/**
 * 角色的Response
 * @author 	wangyu
 *
 */
public class RoleResponse extends BaseResponse {

	/**
	 * 角色Model
	 */
	private SysRole data;

	public RoleResponse() {
		super();
	}

	public RoleResponse(SysRole data) {
		super();
		this.data = data;
	}

	public RoleResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}
	
	public SysRole getData() {
		return data;
	}

	public void setData(SysRole data) {
		this.data = data;
	}
}
