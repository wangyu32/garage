package com.wangyu.prm.response;

import com.wangyu.prm.common.BaseResponse;
import com.wangyu.prm.model.UserModel;

/**
 * 用户model对应的Response
 * @author 	wangyu@joygo.com 2016年11月3日
 *
 */
public class UserResponse extends BaseResponse {

	/**
	 * 用户model
	 */
	private UserModel data;

	public UserResponse() {
		super();
	}

	public UserResponse(UserModel data) {
		super();
		this.data = data;
	}
	
	public UserResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}
	
	public UserModel getData() {
		return data;
	}

	public void setData(UserModel data) {
		this.data = data;
	}
}
