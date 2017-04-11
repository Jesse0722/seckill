package org.seckill.service;

import org.seckill.entity.User;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
public interface UserService {
    int createUser(String userName,String password);

    User getById(int userId);

    User getByName(String name);
}
