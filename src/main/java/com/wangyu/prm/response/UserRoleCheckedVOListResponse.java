package com.wangyu.prm.response;

import java.util.List;

import com.wangyu.prm.common.BaseResponse;
import com.wangyu.prm.vo.UserRoleCheckedVO;

/**
 * 查询用户关联角色List类型返回信息
 * @author 	wangyu	wangyu@joygo.com 2017年1月7日
 *
 */
public class UserRoleCheckedVOListResponse extends BaseResponse {

	List<UserRoleCheckedVO> list;

	int total;
	
	public UserRoleCheckedVOListResponse() {
		super();
	}

	public UserRoleCheckedVOListResponse(List<UserRoleCheckedVO> list) {
		super();
		this.list = list;
	}

	public UserRoleCheckedVOListResponse(List<UserRoleCheckedVO> list, int total) {
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

	public List<UserRoleCheckedVO> getList() {
		return list;
	}

	public void setList(List<UserRoleCheckedVO> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
