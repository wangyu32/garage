package com.wangyu.system.response;

import com.wangyu.system.common.BaseResponse;
import com.wangyu.system.model.SysUserModel;

import java.util.List;

/**
 * 查询用户的List类型返回信息
 * @author wangyu@joygo.com 2017年3月13日
 *
 */
public class UserListResponse extends BaseResponse {

	List<SysUserModel> list;

	int total;
	
	public UserListResponse() {
		super();
	}

	public UserListResponse(List<SysUserModel> list) {
		super();
		this.list = list;
	}

	public UserListResponse(List<SysUserModel> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<SysUserModel> getList() {
		return list;
	}

	public void setList(List<SysUserModel> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}