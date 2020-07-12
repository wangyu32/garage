package com.wangyu.response;

import com.wangyu.common.BaseResponse;

import java.util.List;

/**
 * 查询菜单List类型返回信息
 * @author
 *
 */
public class ModuleMenuListResponse extends BaseResponse {

	List<ModuleMenuResponse> list;

	int total;
	
	public ModuleMenuListResponse() {
		super();
	}

	public ModuleMenuListResponse(List<ModuleMenuResponse> list) {
		super();
		this.list = list;
	}

	public ModuleMenuListResponse(List<ModuleMenuResponse> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<ModuleMenuResponse> getList() {
		return list;
	}

	public void setList(List<ModuleMenuResponse> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
