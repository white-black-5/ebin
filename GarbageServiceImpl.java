package com.javaclimb.test.service.impl;

import com.javaclimb.test.dao.GarbageMapper;
import com.javaclimb.test.domain.Garbage;
import com.javaclimb.test.service.GarbageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarbageServiceImpl implements GarbageService {

    @Autowired
    private GarbageMapper garbageMapper;

    @Override
    public boolean insert(Garbage garbage) {
        return garbageMapper.insert(garbage)>0;
    }

    @Override
    public boolean update(Garbage garbage) {
        return garbageMapper.update(garbage)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return garbageMapper.delete(id)>0;
    }

    @Override
    public Garbage selectByPrimaryKey(Integer id) {
        return garbageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Garbage> allGarbage() {
        return garbageMapper.allGarbage();
    }

    @Override
    public List<Garbage> garbageOfName(String name) {
        return garbageMapper.garbageOfName(name);
    }
}
