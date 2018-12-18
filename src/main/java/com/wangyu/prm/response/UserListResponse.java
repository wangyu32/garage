package com.wangyu.prm.response;

import com.wangyu.prm.common.BaseResponse;
import com.wangyu.prm.model.UserModel;

import java.util.List;

/**
 * 查询用户的List类型返回信息
 * @author wangyu@joygo.com 2017年3月13日
 *
 */
public class UserListResponse extends BaseResponse {

	List<UserModel> list;

	int total;
	
	public UserListResponse() {
		super();
	}

	public UserListResponse(List<UserModel> list) {
		super();
		this.list = list;
	}

	public UserListResponse(List<UserModel> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<UserModel> getList() {
		return list;
	}

	public void setList(List<UserModel> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
