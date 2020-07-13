package com.wangyu.entity.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询参数公共父类
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class PageQueryParameter {
	
	/**
	 * 是否分页查询，默认开启分页查询
	 */
	private boolean pageQuery = true;
	
	/**
	 * 每页记录数
	 */
	private int limit = 15;
	
	/**
	 * 开始记录数
	 */
	private int offset = 0;

	/**
	 * 页数
	 */
	private int pageNumber = 0;

	/**
	 * 排序字段
	 */
	private String sort = "";

	/**
	 * 排序方式
	 */
	private String order = "";

	public PageQueryParameter() {
	}
	
	public PageQueryParameter(boolean pageQuery, int limit, int offset, String sort, String order) {
		super();
		this.pageQuery = pageQuery;
		this.limit = limit;
		this.offset = offset;
		this.sort = sort;
		this.order = order;
	}

	public PageQueryParameter(boolean pageQuery, int limit, int offset, int pageNumber, String sort, String order) {
		super();
		this.pageQuery = pageQuery;
		this.limit = limit;
		this.offset = offset;
		this.pageNumber = pageNumber;
		this.sort = sort;
		this.order = order;
	}

	public PageQueryParameter(PageQueryParameter parameter) {
		super();
		this.pageQuery = parameter.isPageQuery();
		this.limit = parameter.getLimit();
		this.offset = parameter.getOffset();
		this.pageNumber = parameter.getPageNumber();
		this.sort = parameter.getSort();
		this.order = parameter.getOrder();
	}

	public boolean isAsc(){
	    if(this.order != null && this.order.toUpperCase().equals("ASC")){
	        return true;
        }
	    return false;
    }

	public boolean isDesc(){
        if(this.order != null && this.order.toUpperCase().equals("DESC")){
            return true;
        }
        return false;
    }

}
