/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.service.impl;

import com.yuntao.platform.common.auth.AuthUser;
import com.yuntao.platform.common.auth.AuthUserService;
import com.yuntao.platform.common.auth.UserAuthResService;
import com.yuntao.platform.common.cache.CacheService;
import com.yuntao.platform.common.constant.SystemConstant;
import com.yuntao.platform.common.exception.BizException;
import com.yuntao.platform.common.filter.ResponseHolder;
import com.yuntao.platform.common.utils.AppConfigUtils;
import com.yuntao.platform.common.utils.BeanUtils;
import com.yuntao.platform.common.utils.DateUtil;
import com.yuntao.platform.common.utils.ExceptionUtils;
import com.yuntao.platform.common.web.Pagination;
import com.zhushou.test1.common.constant.CacheConstant;
import com.zhushou.test1.dal.mapper.UserMapper;
import com.zhushou.test1.model.domain.User;
import com.zhushou.test1.model.query.UserQuery;
import com.zhushou.test1.model.vo.UserVo;
import com.zhushou.test1.service.inter.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Service
public class UserServiceImpl extends AbstService implements UserService,AuthUserService,UserAuthResService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheService cacheService;

    @Override
    public List<User> selectList(UserQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToMap(query);
        return userMapper.selectList(queryMap);
    }

    @Override
    public User selectOne(UserQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToMap(query);
        List<User> users = userMapper.selectList(queryMap);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Pagination<UserVo> selectPage(UserQuery query) {
        Map<String, Object> queryMap = BeanUtils.beanToMap(query);
        long totalCount = userMapper.selectListCount(queryMap);
        Pagination<UserVo> pagination = new Pagination<>(totalCount,
                query.getPageSize(), query.getPageNum());
        if (totalCount == 0) {
            return pagination;
        }
        queryMap.put("pagination", pagination);
        List<User> dataList = userMapper.selectList(queryMap);
        List<UserVo> newDataList = new ArrayList<>(dataList.size());
        pagination.setDataList(newDataList);
        for (User user : dataList) {
            UserVo userVo = BeanUtils.beanCopy(user, UserVo.class);
            newDataList.add(userVo);
        }
        return pagination;
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }


    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User findByAccountAndType(String accountNo, int userType) {
        UserQuery query = new UserQuery();
        query.setAccountNo(accountNo);
        query.setType(userType);
        User user = this.selectOne(query);
        return user;
    }

    @Override
    public User login(String accountNo, Integer type, String pwd) {
        User user = this.findByAccountAndType(accountNo,type);
        if (user == null) {
            throw new BizException("账号不存在");
        }
        if (!user.getPwd().equals(pwd)) {
            throw new BizException("密码不正确");
        }
        setCurrentUser(user);
        return user;
    }

    @Override
    public User register(User user) {
        this.insert(user);

        setCurrentUser(user);

        return user;
    }

    @Override
    public User getCurrentUser() {
        //浏览器中cookie 缓存
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        if (AppConfigUtils.getAppName().equals("h5")) {  //h5端拿不到cookie特殊处理
            String userId = request.getParameter("userId");
            if (StringUtils.isNoneEmpty(userId)) {
                try{
                    return getCurrentUser(Long.valueOf(userId));
                }catch (Exception e){
                    stackLog.info(ExceptionUtils.getPrintStackTrace(e));
                }
            }
        }
        Cookie cookie = WebUtils.getCookie(request, SystemConstant.USER_TOKEN);
        if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
            HttpServletResponse response = (HttpServletResponse) ResponseHolder.get();
            String uuid = UUID.randomUUID().toString();
            Cookie sidCookie = new Cookie(SystemConstant.USER_TOKEN, uuid);
            sidCookie.setMaxAge(DateUtil.MONTH_SECONDS);
            sidCookie.setPath("/");
//            sidCookie.setDomain("doublefit.cn");
            response.addCookie(sidCookie);
            return null;
        }
        //获取userId
        String sid = cookie.getValue();
        return getCurrentUser(sid);
    }

    @Override
    public User getCurrentUser(String sid) {
        Object value = cacheService.getGlobal(CacheConstant.loginSid + "_" + sid);
        if (value == null) {
            return null;
        }
        Long userId = (Long) value;
        return getCurrentUser(userId);
    }

    @Override
    public User getCurrentUser(Long userId) {
        //从cache中获取
        User user = (User) cacheService.getGlobal(CacheConstant.loginUser + "_" + userId);
        if (user != null) {
            return user;
        }
        user = this.findById(userId);
        if (user != null) {
            cacheService.setGlobal(CacheConstant.loginUser + "_" + userId, user, DateUtil.DAY_SECONDS * 3);
        }
        return user;
    }

    @Override
    public void setCurrentUser(User user) {
        //浏览器中cookie 缓存
        if (RequestContextHolder.getRequestAttributes() == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie cookie = WebUtils.getCookie(request, SystemConstant.USER_TOKEN);
        String sid;
        if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
            HttpServletResponse response = (HttpServletResponse) ResponseHolder.get();
            sid = UUID.randomUUID().toString();
            Cookie sidCookie = new Cookie(SystemConstant.USER_TOKEN, sid);
            sidCookie.setMaxAge(DateUtil.MONTH_SECONDS);
            sidCookie.setPath("/");
//            sidCookie.setDomain("usefullc.cn");
            response.addCookie(sidCookie);
        } else {
            sid = cookie.getValue();
        }
        request.setAttribute(SystemConstant.USER_TOKEN, sid);

        //set cache userId
        cacheService.setGlobal(CacheConstant.loginSid + "_" + sid, user.getId());

        //set cache user
        cacheService.setGlobal(CacheConstant.loginUser + "_" + user.getId(), user);


    }


    @Override
    public AuthUser getAuthUser() {
        User user = getCurrentUser();
        if (user != null) {
            AuthUser authUser = new AuthUser();
            authUser.setUserId(user.getId());
            authUser.setMobile(user.getMobile());
            authUser.setUserName(user.getUserName());
            return authUser;
        }
        return null;
    }

    @Override
    public AuthUser getAuthUser(String s) {
        User user = getCurrentUser(s);
        if (user != null) {
            AuthUser authUser = new AuthUser();
            authUser.setUserId(user.getId());
            authUser.setMobile(user.getMobile());
            authUser.setUserName(user.getUserName());
            return authUser;
        }
        return null;
    }

    @Override
    public Set<String> selectAuthUrlsByUserId(Long aLong) {
        return null;
    }

    @Override
    public boolean needCheck(String s) {
        return false;
    }
}