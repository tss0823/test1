/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.service.inter;

import com.zhushou.test1.model.domain.User;
import com.zhushou.test1.model.query.UserQuery;
import com.zhushou.test1.model.vo.UserVo;
import com.yuntao.platform.common.web.Pagination;

import java.util.List;


/**
 * 用户 服务接口
 * @author 管理员
 *
 * @2018-05-30 15:46
 */
public interface UserService {
	
    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    List<User> selectList(UserQuery query);

    /**
     * 查询对象
     *
     * @param query
     * @return
     */
    User selectOne(UserQuery query);


    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Pagination<UserVo> selectPage(UserQuery query);

    /**
     * 根据id获得对象
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 新增
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 根据id修改
     *
     * @param user
     * @return
     */
    int updateById(User user);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    User findByAccountAndType(String accountNo, int userType);

    User login(String accountNo, Integer type,String pwd);

    User register(User user);

    User getCurrentUser();

    User getCurrentUser(String sid);

    User getCurrentUser(Long userId);

    void setCurrentUser(User user);


    

}

