package com.gxc15090111.system.response;

import java.util.List;

import com.gxc15090111.system.common.BaseResponse;
import com.gxc15090111.system.vo.SysUserRoleCheckedVO;

/**
 * 查询用户关联角色List类型返回信息
 * @author 	gxc15090111	 2018年11月7日
 *
 */
public class UserRoleCheckedVOListResponse extends BaseResponse {

	List<SysUserRoleCheckedVO> list;

	int total;
	
	public UserRoleCheckedVOListResponse() {
		super();
	}

	public UserRoleCheckedVOListResponse(List<SysUserRoleCheckedVO> list) {
		super();
		this.list = list;
	}

	public UserRoleCheckedVOListResponse(List<SysUserRoleCheckedVO> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public UserRoleCheckedVOListResponse(BaseResponse base) {
		if(base != null){
			setCode(base.getCode());
			setMessage(base.getMessage());
		}
	}

	public List<SysUserRoleCheckedVO> getList() {
		return list;
	}

	public void setList(List<SysUserRoleCheckedVO> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
