package com.wangyu.response;


import com.wangyu.common.BaseResponse;
import com.wangyu.model.SysUser;

import java.util.List;

/**
 * 查询用户的List类型返回信息
 * @author wangyu
 *
 */
public class UserListResponse extends BaseResponse {

	List<SysUser> list;

	int total;
	
	public UserListResponse() {
		super();
	}

	public UserListResponse(List<SysUser> list) {
		super();
		this.list = list;
	}

	public UserListResponse(List<SysUser> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<SysUser> getList() {
		return list;
	}

	public void setList(List<SysUser> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
