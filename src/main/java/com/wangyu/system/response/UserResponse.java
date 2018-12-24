package com.wangyu.system.response;

import com.wangyu.system.common.BaseResponse;
import com.wangyu.system.model.SysUserModel;

/**
 * 用户model对应的Response
 * @author 	wangyu@joygo.com 2016年11月3日
 *
 */
public class UserResponse extends BaseResponse {

	/**
	 * 用户model
	 */
	private SysUserModel data;

	public UserResponse() {
		super();
	}

	public UserResponse(SysUserModel data) {
		super();
		this.data = data;
	}
	
	public UserResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}
	
	public SysUserModel getData() {
		return data;
	}

	public void setData(SysUserModel data) {
		this.data = data;
	}
}
