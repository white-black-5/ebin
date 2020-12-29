package com.javaclimb.test.service;

import com.javaclimb.test.domain.Garbage;

import java.util.List;

public interface GarbageService {
    /*
     * 增加
     * */
    public boolean insert(Garbage garbage);

    /*
     * 修改
     * */
    public boolean update(Garbage garbage);

    /*
     * 删除
     * */
    public boolean delete(Integer id);

    /*
     * 根据主键查询整个对象
     * */
    public Garbage selectByPrimaryKey(Integer id);

    /*
     * 查询所有歌手
     * */
    public List<Garbage> allGarbage();

    /*
     * 根据歌手名字模糊查询列表
     * */
    public List<Garbage> garbageOfName(String name);

}
