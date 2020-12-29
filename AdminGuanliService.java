package com.javaclimb.test.service;

import com.javaclimb.test.domain.AdminGuanli;

import java.util.List;

/*
* 管理员service接口
* */
public interface AdminGuanliService {

    /*
    * 登录
    * */
    public boolean verifyPassword(String username,String password);
    /*
     * 增加
     * */
    public boolean insert(AdminGuanli adminGuanli);

    /*
     * 修改
     * */
    public boolean update(AdminGuanli adminGuanli);

    /*
     * 删除
     * */
    public boolean delete(Integer id);

    /*
     * 根据主键查询整个对象
     * */
    public AdminGuanli selectByPrimaryKey(Integer id);

    /*
     * 查询所有管理员
     * */
    public List<AdminGuanli> allAdminGuanli();

    /*
     * 根据管理员名字模糊查询列表
     * */
    public List<AdminGuanli> AdminGuanliOfName(String name);


}
