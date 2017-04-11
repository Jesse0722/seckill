package org.seckill.security;

import org.seckill.dao.PersistentTokenDao;
import org.seckill.entity.PersistentLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private PersistentTokenDao persistentTokenDao;

    @Autowired
    public PersistentTokenRepositoryImpl(PersistentTokenDao persistentTokenDao){
        this.persistentTokenDao=persistentTokenDao;
    }

    private static final Logger logger = LoggerFactory.getLogger(PersistentTokenRepositoryImpl.class);
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        logger.info("Creating Token for user : {}", token.getUsername());
        int insertCount=persistentTokenDao.insert(token.getSeries(),token.getTokenValue(),token.getUsername(),token.getDate());
        if(insertCount>0){
            logger.info("Creating Token success!");
        }else {
            logger.info("Creating Token failed!");
        }
    }

    @Override
    public void updateToken(String series, String token, Date date) {
        logger.info("Updating Token for seriesId : {}", series);
            int updateCount = persistentTokenDao.update(series, token, date);
            if(updateCount>0){
                logger.info("Update token success...");
            }else {
                logger.info("Update token failed...");
            }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        logger.info("Fetch Token if any for seriesId : {}", series);
        try{
            PersistentLogin persistentLogin = persistentTokenDao.getTokenForSeries(series);
            if(persistentLogin!=null){
                return new PersistentRememberMeToken(persistentLogin.getUserName(),persistentLogin.getSeries(),
                        persistentLogin.getToken(),persistentLogin.getDate());
            }
            return null;
        }catch (Exception e){
            logger.info("Token not found...");
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : {}", username);
        int count=persistentTokenDao.delete(username);
        if(count>0){
            logger.info("delete success...");
        }else{
            logger.info("delete failed...");
        }

    }
}
