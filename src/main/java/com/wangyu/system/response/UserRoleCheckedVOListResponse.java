package com.wangyu.system.response;

import java.util.List;

import com.wangyu.system.common.BaseResponse;
import com.wangyu.system.vo.SysUserRoleCheckedVO;

/**
 * 查询用户关联角色List类型返回信息
 * @author 	wangyu	wangyu@joygo.com 2017年1月7日
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
