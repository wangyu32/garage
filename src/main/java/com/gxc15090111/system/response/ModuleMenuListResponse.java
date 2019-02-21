package com.gxc15090111.system.response;

import java.util.List;

import com.gxc15090111.system.common.BaseResponse;

/**
 * 查询菜单List类型返回信息
 * @author 	wangyu	wangyu@joygo.com 2016年11月17日 下午5:31:05
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
