package com.wangyu.system.response;

import java.util.List;

import com.wangyu.system.common.BaseResponse;
import com.wangyu.system.model.SysRoleModel;

/**
 * 查询角色List类型返回信息
 * @author 	wangyu	wangyu@joygo.com 2016年11月17日 下午5:31:05
 *
 */
public class RoleModelListResponse extends BaseResponse {

	List<SysRoleModel> list;

	int total;
	
	public RoleModelListResponse() {
		super();
	}

	public RoleModelListResponse(List<SysRoleModel> list) {
		super();
		this.list = list;
	}

	public RoleModelListResponse(List<SysRoleModel> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<SysRoleModel> getList() {
		return list;
	}

	public void setList(List<SysRoleModel> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
