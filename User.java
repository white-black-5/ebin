package com.javaclimb.test.domain;

import java.io.Serializable;
import java.util.Date;

/*
* 管理员
* */
public class User implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    /*性别*/
    private Byte sex;
    /*头像*/
    private String pic;
    /*生日*/
    private Date birth;
    /*地区*/
    private String location;
    /*联系方式*/
    private String contact;
    /*查询次数*/
    private Integer times;

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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
