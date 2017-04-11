package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Role;
import org.seckill.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by jesse on 2017/4/10.
 */
public interface UserDao {

    int insertUser(@Param("userName") String userName, @Param("password")String password);

    User getById(@Param("userId") int userId);

    List<User> getList(@Param("offset")int offset,@Param("limit")int limit);
}
