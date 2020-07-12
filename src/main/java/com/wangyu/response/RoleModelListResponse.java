package com.wangyu.response;

import com.wangyu.common.BaseResponse;
import com.wangyu.model.SysRole;

import java.util.List;

/**
 * 查询角色List类型返回信息
 * @author 	wangyu
 *
 */
public class RoleModelListResponse extends BaseResponse {

	List<SysRole> list;

	int total;
	
	public RoleModelListResponse() {
		super();
	}

	public RoleModelListResponse(List<SysRole> list) {
		super();
		this.list = list;
	}

	public RoleModelListResponse(List<SysRole> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<SysRole> getList() {
		return list;
	}

	public void setList(List<SysRole> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
