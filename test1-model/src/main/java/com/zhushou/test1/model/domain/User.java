/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.model.domain;

import com.yuntao.platform.common.annotation.ModelComment;
import com.yuntao.platform.common.annotation.ModelFieldComment;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author admin
 * @2016-12-05 18
 */
@ModelComment("用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ModelFieldComment(value = "ID",maxLength = 20)
    private java.lang.Long id;

    @ModelFieldComment(value = "创建时间")
    private java.util.Date gmtCreate;

    @ModelFieldComment(value = "修改时间")
    private java.util.Date gmtModify;

    @ModelFieldComment(value = "删除状态（0：已删除；1：未删除）")
    private java.lang.Boolean delState;

    @ModelFieldComment(value = "账号",maxLength = 20)
    private String accountNo;

    @ModelFieldComment(value = "密码",maxLength = 32)
    private String pwd;

    @ModelFieldComment(value = "昵称",maxLength = 50)
    private String nickname;

    @ModelFieldComment(value = "类型",maxLength = 4)
    private Integer type;

    @ModelFieldComment(value = "状态",maxLength = 4)
    private Integer status;

    @ModelFieldComment(value = "手机",maxLength = 20)
    private String mobile;

    @ModelFieldComment(value = "邮件",maxLength = 50)
    private String email;

    @ModelFieldComment(value = "性别",maxLength = 4)
    private Integer sex;

    @ModelFieldComment(value = "生日",maxLength = 30)
    private String birthday;

    @ModelFieldComment(value = "宣言",maxLength = 500)
    private String message;

    @ModelFieldComment(value = "头像",maxLength = 500)
    private String avatar;


    @ModelFieldComment(value = "描述",maxLength = 2000)
    private String desc;

    @ModelFieldComment(value = "支付密码",maxLength = 32)
    private String payPwd;

    @ModelFieldComment(value = "用户地址",maxLength = 500)
    private String address;


    @ModelFieldComment(value = "用户姓名",maxLength = 30)
    private String userName;

    @ModelFieldComment(value = "角色",maxLength = 1000)
    private String role;


    @ModelFieldComment(value = "职业",maxLength = 50)
    private String career;

    @ModelFieldComment(value = "学历",maxLength = 4)
    private Integer education;


    @ModelFieldComment(value = "客户端id",maxLength = 100)
    private String clientId;

    @ModelFieldComment(value = "设备id",maxLength = 100)
    private String deviceId;

    @ModelFieldComment(value = "设备token",maxLength = 100)
    private String deviceToken;


    @ModelFieldComment(value = "邀请码",maxLength = 20)
    private String inviteCode;

    @ModelFieldComment(value = "别人的邀请",maxLength = 20)
    private String shareInviteCode;




    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Boolean getDelState() {
        return delState;
    }

    public void setDelState(Boolean delState) {
        this.delState = delState;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getShareInviteCode() {
        return shareInviteCode;
    }

    public void setShareInviteCode(String shareInviteCode) {
        this.shareInviteCode = shareInviteCode;
    }
}