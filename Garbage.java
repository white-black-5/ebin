package com.javaclimb.test.domain;

import java.io.Serializable;
import java.util.Date;

public class Garbage implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*类别*/
    private String type;
    /*查询次数*/
    private Integer times;
    /*简介*/
    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
