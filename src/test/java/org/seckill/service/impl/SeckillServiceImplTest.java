package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jesse on 2017/4/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getById() throws Exception {
        long id=1000;
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void getAll() throws Exception {
        List<Seckill> list = seckillService.getAll();
        logger.info("list={}",list);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id=1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("Exposer={}",exposer);
    }
    @Test
    public void seckillLogic() throws Exception{
        long id =1001;
        long userPhone=18048574024L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, exposer.getMd5());
                logger.info("SeckillExecution={}", seckillExecution);
            } catch (RepeatKillException e1) {
                logger.error("RepeatKillException", e1.fillInStackTrace());
            } catch (SeckillCloseException e2) {
                logger.error("RepeatKillException", e2.fillInStackTrace());
            } catch (SeckillException e3) {
                logger.error("SeckillException", e3.fillInStackTrace());
            }
        }
        else{
            logger.info("not open seckill!");
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        //先要执行 exportSeckillUrl，reduceNumber、insertSuccessKilled，
        long id =1001;
        long userPhone=18048574023L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            SeckillExecution seckillExecution = seckillService.executeSeckill(id,userPhone,exposer.getMd5());
            logger.info("SeckillExecution={}",seckillExecution);
        }
    }

}