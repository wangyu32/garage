package com.gxc15090111.system.response;

import com.gxc15090111.system.common.BaseResponse;
import com.gxc15090111.system.model.SysRoleModel;

/**
 * 角色的Response
 * @author 	wangyu	wangyu@joygo.com 2016年12月4日 下午12:47:30
 *
 */
public class RoleResponse extends BaseResponse {

	/**
	 * 角色Model
	 */
	private SysRoleModel data;

	public RoleResponse() {
		super();
	}

	public RoleResponse(SysRoleModel data) {
		super();
		this.data = data;
	}

	public RoleResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}
	
	public SysRoleModel getData() {
		return data;
	}

	public void setData(SysRoleModel data) {
		this.data = data;
	}
}
