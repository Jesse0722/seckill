package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by jesse on 2017/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id=1000;
        long userPhone=18048574023L;
        Date date = new Date();
        int insertCount = successKilledDao.insertSuccessKilled(id,userPhone);
        if(insertCount<=0){
            logger.info("insert failed, id={}",id);
        }else{
            logger.info("insert success, id={},userPhone={}",id,userPhone);
        }


    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id=1000;
        long userPhone=18048574023L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000,userPhone);
        if(successKilled != null){
            logger.info("queryByIdWithSeckill,successKilled ={}",successKilled);
            logger.info("queryByIdWithSeckill,successKilled.Seckill ={}",successKilled.getSeckill());
        }
    }

}