package com.wangyu.prm.response;

import java.util.List;

import com.wangyu.prm.common.BaseResponse;
import com.wangyu.prm.model.RoleModel;

/**
 * 查询角色List类型返回信息
 * @author 	wangyu	wangyu@joygo.com 2016年11月17日 下午5:31:05
 *
 */
public class RoleModelListResponse extends BaseResponse {

	List<RoleModel> list;

	int total;
	
	public RoleModelListResponse() {
		super();
	}

	public RoleModelListResponse(List<RoleModel> list) {
		super();
		this.list = list;
	}

	public RoleModelListResponse(List<RoleModel> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<RoleModel> getList() {
		return list;
	}

	public void setList(List<RoleModel> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
