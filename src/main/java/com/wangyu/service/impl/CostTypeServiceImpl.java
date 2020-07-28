package com.wangyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyu.entity.page.PageQueryResult;
import com.wangyu.entity.parameter.CostTypeQueryParameter;
import com.wangyu.model.CostType;
import com.wangyu.mapper.CostTypeMapper;
import com.wangyu.service.ICostTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyu.service.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消费类型表 服务实现类
 * </p>
 *
 * @author wangyu
 * @since 2020-07-28
 */
@Slf4j
@Service
public class CostTypeServiceImpl extends ServiceImpl<CostTypeMapper, CostType> implements ICostTypeService, JsonConverter {

    @Autowired
    private CostTypeMapper costTypeMapper;

    @Override
    public PageQueryResult findByPage(CostTypeQueryParameter parameter) {
        QueryWrapper<CostType> wrapper = new QueryWrapper<>();
        if (parameter.getPid() != null) {
            wrapper.lambda().eq(CostType::getPid, parameter.getPid());
        }
        if (StringUtils.isNotBlank(parameter.getName())) {
            wrapper.lambda().like(CostType::getName, parameter.getName());
        }
        if (parameter.getNotes() != null) {
            wrapper.lambda().eq(CostType::getNotes, parameter.getNotes());
        }
        if (StringUtils.isNotBlank(parameter.getSort())) {
            if(parameter.isAsc()){
                wrapper.orderByAsc(parameter.getSort());
            } else {
                wrapper.orderByDesc(parameter.getSort());
            }
        }

        if (parameter.isPageQuery()) {
            log.info("parameter={}", toJson(parameter));
            Page<CostType> page = new Page<>(parameter.getOffset() / parameter.getLimit() + 1, parameter.getLimit());
            log.info("page={}", toJson(page));
            IPage<CostType> iPage = this.costTypeMapper.selectPage(page, wrapper);
            PageQueryResult pageQueryResult = new PageQueryResult(iPage.getRecords(), Long.valueOf(iPage.getTotal()).intValue());
            return pageQueryResult;
        } else {
            List<CostType> list = this.costTypeMapper.selectList(wrapper);
            return new PageQueryResult(list, list.size());
        }
    }
}
