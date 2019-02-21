package com.gxc15090111.system.response;

import java.util.List;

import com.gxc15090111.system.common.BaseResponse;
import com.gxc15090111.system.common.ModuleMenuTreeNode;

/**
 * 菜单树节点ListResponse
 * @author 	gxc15090111	 2018年11月17日 下午5:31:05
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
