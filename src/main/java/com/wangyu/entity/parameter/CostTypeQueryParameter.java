package com.wangyu.entity.parameter;

import com.wangyu.entity.page.PageQueryParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * 消费类型查询参数
 * @author 	wangyu
 *
 */
@Getter
@Setter
public class CostTypeQueryParameter extends PageQueryParameter {
	
	/**
	 * 父亲id
	 */
	private Integer pid;

	/**
	 * 消费类型名称
	 */
	private String name;

	/**
	 * 消费类型备注
	 */
	private String notes;

}
