package com.wangyu.system.entity;

/**
 * 模块及对应菜单查询结果封装对象
 * @author 	wangyu wangyu@joygo.com 2016年10月12日
 *
 */
public class ModuleMenuQueryResult {

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
     * 菜单id
     */
    private Integer mm_id;

    /**
     * 菜单名称
     */
    private String mm_name;

    /**
     * 菜单URL
     */
    private String mm_url;

    /**
     * 菜单排序号
     */
    private Integer mm_order;

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

	public Integer getMm_id() {
		return mm_id;
	}

	public void setMm_id(Integer mm_id) {
		this.mm_id = mm_id;
	}

	public String getMm_name() {
		return mm_name;
	}

	public void setMm_name(String mm_name) {
		this.mm_name = mm_name;
	}

	public String getMm_url() {
		return mm_url;
	}

	public void setMm_url(String mm_url) {
		this.mm_url = mm_url;
	}

	public Integer getMm_order() {
		return mm_order;
	}

	public void setMm_order(Integer mm_order) {
		this.mm_order = mm_order;
	}
    
	
}
