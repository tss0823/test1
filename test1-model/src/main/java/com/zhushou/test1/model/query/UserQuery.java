/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.model.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author 管理员
 *
 * @2018-05-30 15:46
 */
public class UserQuery extends BaseQuery {
    
    private static final long serialVersionUID = 1L;
    
    /**  账号 * */
    private java.lang.String accountNo;

    /**  密码 * */
    private java.lang.String pwd;

    /**  昵称 * */
    private java.lang.String nickname;

    /**  类型 * */
    private java.lang.Integer type;

    /**  状态 * */
    private java.lang.Integer status;

    /**  手机 * */
    private java.lang.String mobile;

    /**  邮件 * */
    private java.lang.String email;

    /**  性别 * */
    private java.lang.Integer sex;

    /**  生日 * */
    private java.lang.String birthday;

    /**  宣言 * */
    private java.lang.String message;

    /**  头像 * */
    private java.lang.String avatar;

    /**  描述 * */
    private java.lang.String desc;

    /**  支付密码 * */
    private java.lang.String payPwd;

    /**  用户地址 * */
    private java.lang.String address;

    /**  用户姓名 * */
    private java.lang.String userName;

    /**  角色 * */
    private java.lang.String role;

    /**  职业 * */
    private java.lang.String career;

    /**  学历 * */
    private java.lang.Integer education;

    /**  客户端id * */
    private java.lang.String clientId;

    /**  设备id * */
    private java.lang.String deviceId;

    /**  设备token * */
    private java.lang.String deviceToken;

    /**  邀请码 * */
    private java.lang.String inviteCode;

    /**  别人的邀请 * */
    private java.lang.String shareInviteCode;

    /**  ID * */
    private java.lang.Long id;

    /**  创建时间 * */
    private java.util.Date gmtCreate;

    /**  修改时间 * */
    private java.util.Date gmtModify;

    /**  删除状态（0：已删除；1：未删除） * */
    private java.lang.Boolean delState = true;

    

    public UserQuery(){}

    public void setAccountNo(java.lang.String value) {
        this.accountNo = value;
    }
    
    public java.lang.String getAccountNo() {
        return this.accountNo;
    }
    public void setPwd(java.lang.String value) {
        this.pwd = value;
    }
    
    public java.lang.String getPwd() {
        return this.pwd;
    }
    public void setNickname(java.lang.String value) {
        this.nickname = value;
    }
    
    public java.lang.String getNickname() {
        return this.nickname;
    }
    public void setType(java.lang.Integer value) {
        this.type = value;
    }
    
    public java.lang.Integer getType() {
        return this.type;
    }
    public void setStatus(java.lang.Integer value) {
        this.status = value;
    }
    
    public java.lang.Integer getStatus() {
        return this.status;
    }
    public void setMobile(java.lang.String value) {
        this.mobile = value;
    }
    
    public java.lang.String getMobile() {
        return this.mobile;
    }
    public void setEmail(java.lang.String value) {
        this.email = value;
    }
    
    public java.lang.String getEmail() {
        return this.email;
    }
    public void setSex(java.lang.Integer value) {
        this.sex = value;
    }
    
    public java.lang.Integer getSex() {
        return this.sex;
    }
    public void setBirthday(java.lang.String value) {
        this.birthday = value;
    }
    
    public java.lang.String getBirthday() {
        return this.birthday;
    }
    public void setMessage(java.lang.String value) {
        this.message = value;
    }
    
    public java.lang.String getMessage() {
        return this.message;
    }
    public void setAvatar(java.lang.String value) {
        this.avatar = value;
    }
    
    public java.lang.String getAvatar() {
        return this.avatar;
    }
    public void setDesc(java.lang.String value) {
        this.desc = value;
    }
    
    public java.lang.String getDesc() {
        return this.desc;
    }
    public void setPayPwd(java.lang.String value) {
        this.payPwd = value;
    }
    
    public java.lang.String getPayPwd() {
        return this.payPwd;
    }
    public void setAddress(java.lang.String value) {
        this.address = value;
    }
    
    public java.lang.String getAddress() {
        return this.address;
    }
    public void setUserName(java.lang.String value) {
        this.userName = value;
    }
    
    public java.lang.String getUserName() {
        return this.userName;
    }
    public void setRole(java.lang.String value) {
        this.role = value;
    }
    
    public java.lang.String getRole() {
        return this.role;
    }
    public void setCareer(java.lang.String value) {
        this.career = value;
    }
    
    public java.lang.String getCareer() {
        return this.career;
    }
    public void setEducation(java.lang.Integer value) {
        this.education = value;
    }
    
    public java.lang.Integer getEducation() {
        return this.education;
    }
    public void setClientId(java.lang.String value) {
        this.clientId = value;
    }
    
    public java.lang.String getClientId() {
        return this.clientId;
    }
    public void setDeviceId(java.lang.String value) {
        this.deviceId = value;
    }
    
    public java.lang.String getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceToken(java.lang.String value) {
        this.deviceToken = value;
    }
    
    public java.lang.String getDeviceToken() {
        return this.deviceToken;
    }
    public void setInviteCode(java.lang.String value) {
        this.inviteCode = value;
    }
    
    public java.lang.String getInviteCode() {
        return this.inviteCode;
    }
    public void setShareInviteCode(java.lang.String value) {
        this.shareInviteCode = value;
    }
    
    public java.lang.String getShareInviteCode() {
        return this.shareInviteCode;
    }
    public void setId(java.lang.Long value) {
        this.id = value;
    }
    
    public java.lang.Long getId() {
        return this.id;
    }
    public void setGmtCreate(java.util.Date value) {
        this.gmtCreate = value;
    }
    
    public java.util.Date getGmtCreate() {
        return this.gmtCreate;
    }
    public void setGmtModify(java.util.Date value) {
        this.gmtModify = value;
    }
    
    public java.util.Date getGmtModify() {
        return this.gmtModify;
    }
    public void setDelState(java.lang.Boolean value) {
        this.delState = value;
    }
    
    public java.lang.Boolean getDelState() {
        return this.delState;
    }
    



}