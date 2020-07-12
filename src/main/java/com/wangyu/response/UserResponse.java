package com.wangyu.response;

import com.wangyu.common.BaseResponse;
import com.wangyu.model.SysUser;

/**
 * 用户model对应的Response
 * @author 	wangyu
 *
 */
public class UserResponse extends BaseResponse {

	/**
	 * 用户model
	 */
	private SysUser data;

	public UserResponse() {
		super();
	}

	public UserResponse(SysUser data) {
		super();
		this.data = data;
	}
	
	public UserResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}
	
	public SysUser getData() {
		return data;
	}

	public void setData(SysUser data) {
		this.data = data;
	}
}
