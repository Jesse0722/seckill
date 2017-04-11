package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Role;
import org.seckill.entity.User;
import org.seckill.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private RoleDao roleDao;

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
                logger.info("roleName:" + role.getRoleName()+"  "+role.getRoleId());
            }
        }
    }
    @Transactional
    @Test
    public void createUser(){
        String userName="lee";
        String password="123456";
        String role= RoleEnum.USER.getName();
        int count = userDao.insertUser(userName,password);
        if(count>0){
            logger.info("insert user success username={}",userName);
            int insertRoleCount=roleDao.insertRole(userName,userDao.getByName(userName).getUserId());
            if(insertRoleCount>0){
                logger.info("insert role success username={},role={}",userName,role);
            }else{
                logger.info("insert role fail username={},role={}",userName,role);
            }
        }else{
            logger.info("insert user fail username={},role={}",userName,role);
        }
    }

    @Test
    public void getByName(){
        User user = userDao.getByName("lijiajun");
        if(user!=null){
            logger.info("user={}" ,user);
        }
    }

}