package com.javaclimb.test.service;

import com.javaclimb.test.domain.AdminGuanli;
import com.javaclimb.test.domain.User;

import java.util.List;

/*
* 用户service接口
* */
public interface UserService {

    /*
    * 登录
    * */
    public boolean verifyPassword(String username, String password);
    /*
     * 增加
     * */
    public boolean insert(User user);

    /*
     * 修改
     * */
    public boolean update(User user);

    /*
     * 删除
     * */
    public boolean delete(Integer id);

    /*
     * 根据主键查询整个对象
     * */
    public User selectByPrimaryKey(Integer id);

    /*
     * 查询所有歌手
     * */
    public List<User> allUser();

    /*
     * 根据歌手名字模糊查询列表
     * */
    public List<User> userOfName(String name);


}
