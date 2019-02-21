package com.gxc15090111.system.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 微服务返回信息List类型数据
 * @author 	gxc15090111  2018年10月13日
 *
 */
public class ListResponse extends BaseResponse {
	
	/**
	 * 微服务返回业务数据-List类型
	 */
    private List<?> list;

    /**
     * 总记录数
     */
    private int total;
    
    
    public ListResponse() {
        super();
    }
    
    public ListResponse (List<?> list) {
        this.list = list;
    }

    public ListResponse(List<?> list, int total) {
		super();
		this.list = list;
		this.total = total;
	}

	public ListResponse(String code) {
        super(code);
    }

    public ListResponse(String code, String message) {
        super(code, message);
    }

    public ListResponse setList(List<?> list) {
        this.list = list;
        return this;
    }

    public List<?> getList() {
        return list;
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int totalCount) {
		this.total = totalCount;
	}
	
	public ListResponse emptyListResponse(){
		this.list = new ArrayList<Object>();
		this.total = 0;
		return this;
	}

    public static ListResponse faild(){
        ListResponse listResponse = new ListResponse();
        listResponse.setCode(Code.FAIL);
        return listResponse;
    }

    public static ListResponse faild(String message){
        ListResponse listResponse = new ListResponse();
        listResponse.setCode(Code.FAIL);
        listResponse.setMessage(message);
        return listResponse;
    }

}
