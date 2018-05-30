/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.service.inter;

import com.zhushou.test1.model.domain.Demo;
import com.zhushou.test1.model.query.DemoQuery;
import com.zhushou.test1.model.vo.DemoVo;
import com.yuntao.platform.common.web.Pagination;

import java.util.List;


/**
 * 示例 服务接口
 * @author 管理员
 *
 * @2018-04-22 17:02
 */
public interface DemoService {
	
    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    List<Demo> selectList(DemoQuery query);

    /**
     * 查询对象
     *
     * @param query
     * @return
     */
    Demo selectOne(DemoQuery query);


    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Pagination<DemoVo> selectPage(DemoQuery query);

    /**
     * 根据id获得对象
     *
     * @param id
     * @return
     */
    Demo findById(Long id);

    /**
     * 新增
     *
     * @param demo
     * @return
     */
    int insert(Demo demo);

    /**
     * 根据id修改
     *
     * @param demo
     * @return
     */
    int updateById(Demo demo);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);


    

}

