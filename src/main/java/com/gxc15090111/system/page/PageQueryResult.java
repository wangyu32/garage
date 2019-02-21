package com.gxc15090111.system.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询返回结果公共类
 * @author 	gxc15090111	 2018年10月30日 下午1:31:41
 *
 */
public class PageQueryResult<T> {
 
	/**
     * 以List形式返回结果
     */
    private List<T> resultList = new ArrayList<T>();
    
    /**
     * 总记录数
     */
    private int total = 0;

    
    public PageQueryResult() {
    	super();
    }
    
    public PageQueryResult(List<T> resultList) {
    	super();
    	this.resultList = resultList;
    }

    public PageQueryResult(List<T> resultList, int total) {
		super();
		this.resultList = resultList;
		this.total = total;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public boolean isEmpty(){
		return resultList == null || resultList.size() == 0;
	}
}
