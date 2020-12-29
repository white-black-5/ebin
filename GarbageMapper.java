package com.javaclimb.test.dao;

import com.javaclimb.test.domain.Garbage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarbageMapper {
    /*
     * 增加
     * */
    public int insert(Garbage garbage);

    /*
     * 修改
     * */
    public int update(Garbage garbage);

    /*
     * 删除
     * */
    public int delete(Integer id);

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
