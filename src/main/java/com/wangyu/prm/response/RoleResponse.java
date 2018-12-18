package com.wangyu.prm.response;

import com.wangyu.prm.common.BaseResponse;
import com.wangyu.prm.model.RoleModel;

/**
 * 角色的Response
 * @author 	wangyu	wangyu@joygo.com 2016年12月4日 下午12:47:30
 *
 */
public class RoleResponse extends BaseResponse {

	/**
	 * 角色Model
	 */
	private RoleModel data;

	public RoleResponse() {
		super();
	}

	public RoleResponse(RoleModel data) {
		super();
		this.data = data;
	}

	public RoleResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}
	
	public RoleModel getData() {
		return data;
	}

	public void setData(RoleModel data) {
		this.data = data;
	}
}
