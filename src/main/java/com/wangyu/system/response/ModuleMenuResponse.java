package com.wangyu.system.response;

import com.wangyu.system.model.SysModuleMenuModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块及对应菜单Response
 * @author 	wangyu wangyu@joygo.com 2016年12月4日
 *
 */
public class ModuleMenuResponse {

	/**
	 * 模块id
	 */
	private Integer m_id;

	/**
	 * 模块类型
	 */
	private Integer m_type;
	
	/**
	 * 模块名称
	 */
    private String m_name;

    /**
     * 模块图片
     */
    private String m_image;

    /**
     * 模块排序
     */
    private Integer m_order;
    
    /**
     * 模块下菜单集合
     */
    private List<SysModuleMenuModel> items = new ArrayList<SysModuleMenuModel>();
    
    public Integer getM_id() {
		return m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}

	public Integer getM_type() {
		return m_type;
	}

	public void setM_type(Integer m_type) {
		this.m_type = m_type;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_image() {
		return m_image;
	}

	public void setM_image(String m_image) {
		this.m_image = m_image;
	}

	public Integer getM_order() {
		return m_order;
	}

	public void setM_order(Integer m_order) {
		this.m_order = m_order;
	}

	public List<SysModuleMenuModel> getItems() {
		return items;
	}

	public void setItems(List<SysModuleMenuModel> items) {
		this.items = items;
	}
	
	
}
