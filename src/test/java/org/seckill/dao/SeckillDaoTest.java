package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jesse on 2017/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    private static final Logger log = LoggerFactory.getLogger(SeckillDaoTest.class);

    /*
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException:
### Error querying database.  Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection; nested exception is java.sql.SQLException: An attempt by a client to checkout a Connection has timed out.
### The error may exist in file [D:\MavenProject\seckill\target\classes\mapper\SeckillDao.xml]
### The error may involve org.seckill.dao.SeckillDao.queryById
### The error occurred while executing a query
### Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection; nested exception is java.sql.SQLException: An attempt by a client to checkout a Connection has timed out.

	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:76)
     */
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception{
        long seckillId=1000;
        Seckill seckill=seckillDao.queryById(seckillId);
        log.info(seckill.toString());
    }

    @Test
    public void queryAll() throws Exception{
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill s:seckills){
            log.info(s.toString());
        }
    }

    @Test
    public void reduceNumber() throws Exception{
        long seckillId=1000;
        Date date= new Date();
        int updateCount=seckillDao.reduceNumber(seckillId,date);
        log.info(updateCount+"");
    }

}