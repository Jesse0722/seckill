package org.seckill.service.impl;

import org.seckill.dao.UserDao;
import org.seckill.entity.User;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public int createUser(String userName, String password) {
        return userDao.insertUser(userName,password);
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }
}
