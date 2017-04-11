package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.PersistentLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class PersistentTokenDaoTest {
    private static final Logger logger= LoggerFactory.getLogger(RoleDaoTest.class);
    @Autowired
    PersistentTokenDao persistentTokenDao;
    @Test
    public void insert() throws Exception {
        int count=persistentTokenDao.insert("1251251251521sda","12512dasdasd","jesse",new Date());
        if(count>0){
            logger.info("insert success");
        }
    }

    @Test
    public void update() throws Exception {
        int count=persistentTokenDao.update("1251251251521sda","dasdasdasff",new Date());
        if(count>0){
            logger.info("update success");
        }
    }

    @Test
    public void getTokenForSeries() throws Exception {
        PersistentLogin persistentLogin=persistentTokenDao.getTokenForSeries("1251251251521sda");
        if(persistentLogin!=null){
            logger.info("token:"+persistentLogin.getSeries());
        }
    }

    @Test
    public void delete() throws Exception {
        int count = persistentTokenDao.delete("jesse");
        if(count>0){
            logger.info("delete success!");
        }
    }

}