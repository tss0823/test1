package com.zhushou.test1.web.member.controller;

import com.yuntao.platform.common.CustomizedPropertyConfigurer;
import com.yuntao.platform.common.annotation.NeedLogin;
import com.yuntao.platform.common.annotation.ParamFieldComment;
import com.yuntao.platform.common.annotation.ReqMethodComment;
import com.yuntao.platform.common.cache.CacheService;
import com.yuntao.platform.common.exception.BizException;
import com.yuntao.platform.common.utils.BeanUtils;
import com.yuntao.platform.common.utils.DateUtil;
import com.yuntao.platform.common.utils.ResponseObjectUtils;
import com.yuntao.platform.common.web.ResponseObject;
import com.zhushou.test1.common.constant.CacheConstant;
import com.zhushou.test1.model.domain.User;
import com.zhushou.test1.model.enums.UserStatus;
import com.zhushou.test1.model.enums.UserType;
import com.zhushou.test1.model.query.UserQuery;
import com.zhushou.test1.model.vo.UserVo;
import com.zhushou.test1.service.inter.UserService;
import com.zhushou.test1.service.support.SmsServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;


    @Autowired
    private SmsServer smsServer;



    @ReqMethodComment(value="用户注册",author = "shengshan.tang",module = "user",ver = "1.0.1")
    @RequestMapping("register")
    public ResponseObject doRegister(@ParamFieldComment("手机账号") String mobile,
                                     @ParamFieldComment("密码") String pwd, @ParamFieldComment("验证码") String smsCode,
                                     @ParamFieldComment(value = "邀请码",required = false) String inviteCode) {
        ResponseObject responseObject = new ResponseObject();
        // validate
        int userType = UserType.member.getCode();
        String key = StringUtils.join(new Object[]{CacheConstant.smsCodeRegister, userType, mobile}, "_");
        Object cacheSmsCode = cacheService.get(key);
        if (cacheSmsCode == null) {
            throw new BizException("验证码已失效，请重新获取");
        }
        if (!cacheSmsCode.toString().equals(smsCode)) {
            throw new BizException("验证码输入错误");
        }
        if (mobile.length() != 11 || !mobile.startsWith("1")) {
            throw new BizException("手机账号不合法");
        }
        User paramUser = new User();
        paramUser.setAccountNo(mobile);
        paramUser.setMobile(mobile);
        paramUser.setPwd(pwd);
        paramUser.setType(UserType.member.getCode());
        paramUser.setStatus(UserStatus.pass.getCode());
        paramUser.setNickname(mobile);
        if(null != inviteCode){//邀请码验证
            UserQuery query = new UserQuery();
            query.setInviteCode(inviteCode);
            if(userService.selectList(query).size()>0){
                paramUser.setShareInviteCode(inviteCode);// 邀请码
            }else{
                throw new BizException("邀请码不存在，请重新输入");
            }
        }
        User user = userService.register(paramUser);
        UserVo userVo = BeanUtils.beanCopy(user, UserVo.class);
        responseObject.setData(userVo);
        return responseObject;
    }

    @ReqMethodComment(value="用户登录",author = "shengshan.tang",module = "user",ver = "1.0.1")
    @RequestMapping("login")
    public ResponseObject doLogin(@ParamFieldComment("手机账号") String mobile,
                                  @ParamFieldComment("密码") String pwd) {
        // validate
        // login
        ResponseObject responseObject = new ResponseObject();
        User user = userService.login(mobile, UserType.member.getCode(), pwd);
        responseObject.setData(user);
        return responseObject;
    }



    @ReqMethodComment(value="获取用户信息",author = "shengshan.tang",module = "user",ver = "1.0.1")
    @RequestMapping("getLoginUser")
    @NeedLogin
    public ResponseObject getLoginUser() {
        ResponseObject responseObject = ResponseObjectUtils.buildResObject();
        User user = userService.getCurrentUser();
        responseObject.setData(user);
        return responseObject;
    }

    @ReqMethodComment(value = "注册获取手机账号验证码",author = "shengshan.tang",module = "user",ver = "1.0.1")
    @RequestMapping("getRegisterSMSCode")
    public ResponseObject getRegisterSMSCode(@ParamFieldComment("手机账号") String mobile) {
        ResponseObject responseObject = new ResponseObject();
        int userType = UserType.member.getCode();
        //是否存在
        User dbUser = userService.findByAccountAndType(mobile, userType);
        if (dbUser != null) {
            throw new BizException("该手机账号已存在");
        }
        String key = StringUtils.join(new Object[]{CacheConstant.smsCodeRegister, userType, mobile}, "_");
        Object cacheSmsCode = cacheService.get(key);
        if(cacheSmsCode == null){  //没有发送验证码，才走手机发送
            String smsCode = smsServer.getRegisterSMSCode(mobile);
            cacheService.set(key, smsCode, DateUtil.MINITUE_5_SECONDS);
            if (!CustomizedPropertyConfigurer.isProd()) {
                responseObject.setData(smsCode);
            }
        }
        return responseObject;
    }


}
