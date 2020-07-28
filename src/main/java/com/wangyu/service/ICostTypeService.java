package com.wangyu.service;

import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.CostTypeQueryParameter;
import com.wangyu.model.CostType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 消费类型表 服务类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-28
 */
public interface ICostTypeService extends IService<CostType> {

    PageQueryResult findByPage(CostTypeQueryParameter parameter);
}
