package com.javaclimb.test.service.impl;

import com.javaclimb.test.dao.AdminGuanliMapper;
import com.javaclimb.test.domain.AdminGuanli;
import com.javaclimb.test.service.AdminGuanliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 管理员service实现类
* */
@Service
public class AdminGuanliServiceImpl implements AdminGuanliService {

    @Autowired
    private AdminGuanliMapper AdminGuanliMapper;

    @Override
    public boolean insert(AdminGuanli adminGuanli) {
        return AdminGuanliMapper.insert(adminGuanli)>0;
    }

    @Override
    public boolean update(AdminGuanli adminGuanli) {
        return AdminGuanliMapper.update(adminGuanli)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return AdminGuanliMapper.delete(id)>0;
    }

    @Override
    public AdminGuanli selectByPrimaryKey(Integer id) {
        return AdminGuanliMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminGuanli> allAdminGuanli() {
        return AdminGuanliMapper.allAdminGuanli();
    }

    @Override
    public List<AdminGuanli> AdminGuanliOfName(String name) {
        return AdminGuanliMapper.AdminGuanliOfName(name);
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        return AdminGuanliMapper.verifyPassword(username,password)>0;

    }
}
