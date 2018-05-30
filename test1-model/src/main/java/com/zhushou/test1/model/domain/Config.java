/*
 * 
 * 
 * 
 * 
 */

package com.zhushou.test1.model.domain;

import java.io.Serializable;

/**
 * 配置项
 * @author admin
 *
 * @2016-11-28 11
 */
public class Config implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**  ID * */
    private Long id;

    /**  名称 * */
    private String name;

    /**  值 * */
    private String value;

    /**  是否删除（1：未删除；0：已删除） * */
    private Integer delState;


    public Config(){
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return this.id;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public void setDelState(Integer value) {
        this.delState = value;
    }

    public Integer getDelState() {
        return this.delState;
    }
    



}