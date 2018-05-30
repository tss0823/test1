/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.service.impl;

import com.zhushou.test1.model.domain.Demo;
import com.zhushou.test1.model.query.DemoQuery;
import com.zhushou.test1.model.vo.DemoVo;
import com.zhushou.test1.dal.mapper.DemoMapper;
import com.zhushou.test1.service.inter.DemoService;
import com.yuntao.platform.common.utils.BeanUtils;
import com.yuntao.platform.common.web.Pagination;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class DemoServiceImpl extends AbstService implements DemoService {


    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> selectList(DemoQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToMap(query);
        return demoMapper.selectList(queryMap);
    }

    @Override
    public Demo selectOne(DemoQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToMap(query);
        List<Demo> demos = demoMapper.selectList(queryMap);
        if (CollectionUtils.isNotEmpty(demos)) {
            return demos.get(0);
        }
        return null;
    }

    @Override
    public Pagination<DemoVo> selectPage(DemoQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToMap(query);
        long totalCount = demoMapper.selectListCount(queryMap);
        Pagination<DemoVo> pagination = new Pagination<>(totalCount,
                query.getPageSize(), query.getPageNum());
        if (totalCount == 0) {
            return pagination;
        }
        queryMap.put("pagination", pagination);
        List<Demo> dataList = demoMapper.selectList(queryMap);
        List<DemoVo> newDataList = new ArrayList<>(dataList.size());
        pagination.setDataList(newDataList);
        for (Demo demo : dataList) {
            DemoVo demoVo = BeanUtils.beanCopy(demo, DemoVo.class);
            newDataList.add(demoVo);
        }
        return pagination;
    }

    @Override
    public Demo findById(Long id) {
        return demoMapper.findById(id);
    }


    @Override
    public int insert(Demo demo) {
        return demoMapper.insert(demo);
    }

    @Override
    public int updateById(Demo demo) {
        return demoMapper.updateById(demo);
    }

    @Override
    public int deleteById(Long id) {
        return demoMapper.deleteById(id);
    }


}