package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by jesse on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RoleDaoTest {

    private static final Logger logger= LoggerFactory.getLogger(RoleDaoTest.class);
    @Autowired
    private RoleDao roleDao;
    @Test
    public void getById() throws Exception {
        Role role = roleDao.getById(100);
        if(role!=null){
            logger.info("role={roleName:"+role.getRoleName()+",userId:"+role.getUser());
        }
    }

    @Test
    public void getRolesByUserId() throws Exception {

    }

}