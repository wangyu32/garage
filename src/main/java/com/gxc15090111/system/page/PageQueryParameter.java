package com.gxc15090111.system.page;

/**
 * 分页查询参数公共父类
 * @author 	gxc15090111  2018年10月30日
 *
 */
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
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(boolean pageQuery) {
		this.pageQuery = pageQuery;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
