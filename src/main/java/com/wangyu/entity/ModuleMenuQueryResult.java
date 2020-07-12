package com.wangyu.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 模块及对应菜单查询结果封装对象
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class ModuleMenuQueryResult implements Serializable {

	/**
	 * 模块id
	 */
	private Integer id;

	/**
	 * 模块类型
	 */
	private Integer type;
	
	/**
	 * 模块名称
	 */
    private String name;

    /**
     * 模块图片
     */
    private String image;

    /**
     * 模块排序
     */
    private Integer order;
    
    /**
     * 菜单id
     */
    private Integer mmid;

    /**
     * 菜单名称
     */
    private String mmname;

    /**
     * 菜单URL
     */
    private String mmurl;

    /**
     * 菜单排序号
     */
    private Integer mmorder;

}
