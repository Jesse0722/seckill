package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Role;
import org.seckill.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by jesse on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void insertUser() throws Exception {
        int count = userDao.insertUser("lijiajun","123456");
        if(count<=0){
            logger.info("insert failed, username");
        }else{
            logger.info("insert success");
        }
    }

    @Test
    public void getById() throws Exception {
        User user = userDao.getById(10000);
        Set<Role> roleSet = new HashSet<Role>();
        roleSet=user.getRoles();
        if(user!=null){
            logger.info("User is not empty");
            logger.info("UserName:"+user.getUserName());
            logger.info("Password:"+user.getPassword());
            logger.info("Email:"+user.getEmail());
            logger.info("Role:"+roleSet.size());
            for(Role role:roleSet) {
                logger.info("roleName:" + role.getRoleName()+"  "+role.getUser());
            }
        }
    }

}