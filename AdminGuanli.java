package com.javaclimb.test.domain;

import java.io.Serializable;
import java.util.Date;

/*
* 管理员
* */
public class AdminGuanli implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    /*头像*/
    private String pic;
    /*地区*/
    private String location;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
