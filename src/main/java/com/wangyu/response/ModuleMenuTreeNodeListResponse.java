package com.wangyu.response;

import com.wangyu.common.BaseResponse;
import com.wangyu.entity.ModuleMenuTreeNode;

import java.util.List;

/**
 * 菜单树节点ListResponse
 * @author 	wangyu
 *
 */
public class ModuleMenuTreeNodeListResponse extends BaseResponse {

	List<ModuleMenuTreeNode> list;

	int total;
	
	public ModuleMenuTreeNodeListResponse() {
		super();
	}

	public ModuleMenuTreeNodeListResponse(List<ModuleMenuTreeNode> list) {
		super();
		this.list = list;
	}

	public ModuleMenuTreeNodeListResponse(List<ModuleMenuTreeNode> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<ModuleMenuTreeNode> getList() {
		return list;
	}

	public void setList(List<ModuleMenuTreeNode> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
