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
 * 示例
 * @author 管理员
 *
 * @2018-04-22 17:02
 */
public class DemoQuery extends BaseQuery {
    
    private static final long serialVersionUID = 1L;
    
    /**  ID * */
    private java.lang.Long id;

    /**  创建时间 * */
    private java.util.Date gmtCreate;

    /**  修改时间 * */
    private java.util.Date gmtModify;

    /**  删除状态（0：已删除；1：未删除） * */
    private java.lang.Boolean delState = true;

    /**  名称 * */
    private java.lang.String name;

    /**  数量 * */
    private java.lang.Integer count;

    

    public DemoQuery(){}

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
    public void setName(java.lang.String value) {
        this.name = value;
    }
    
    public java.lang.String getName() {
        return this.name;
    }
    public void setCount(java.lang.Integer value) {
        this.count = value;
    }
    
    public java.lang.Integer getCount() {
        return this.count;
    }
    



}