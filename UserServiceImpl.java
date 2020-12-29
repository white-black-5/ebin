package com.javaclimb.test.service.impl;

import com.javaclimb.test.dao.UserMapper;
import com.javaclimb.test.domain.User;
import com.javaclimb.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 用户service实现类
* */
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper UserMapper;

    @Override
    public boolean insert(User user) {
        return UserMapper.insert(user)>0;
    }

    @Override
    public boolean update(User user) {
        return UserMapper.update(user)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return UserMapper.delete(id)>0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return UserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> allUser() {
        return UserMapper.allUser();
    }

    @Override
    public List<User> userOfName(String name) {
        return UserMapper.userOfName(name);
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        return UserMapper.verifyPassword(username,password)>0;

    }
}
