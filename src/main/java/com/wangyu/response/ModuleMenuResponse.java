package com.wangyu.response;

import com.wangyu.model.SysModuleMenu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块及对应菜单Response
 * @author 	wangyu
 *
 */
@Data
public class ModuleMenuResponse {

	/**
	 * 模块id
	 */
	private Integer mid;

	/**
	 * 模块类型
	 */
	private Integer mtype;
	
	/**
	 * 模块名称
	 */
    private String mname;

    /**
     * 模块图片
     */
    private String mimage;

    /**
     * 模块排序
     */
    private Integer morder;
    
    /**
     * 模块下菜单集合
     */
    private List<SysModuleMenu> items = new ArrayList<>();
    

}
