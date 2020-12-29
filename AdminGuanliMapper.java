package com.javaclimb.test.dao;

import com.javaclimb.test.domain.AdminGuanli;
import org.springframework.stereotype.Repository;

import java.util.List;

/*管理员*/
@Repository
public interface AdminGuanliMapper {
    /*
    * 登录
    * */
    public int verifyPassword(String username,String password);
    /*
    * 增加
    * */
    public int insert(AdminGuanli adminGuanli);

    /*
    * 修改
    * */
    public int update(AdminGuanli adminGuanli);

    /*
    * 删除
    * */
    public int delete(Integer id);

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
