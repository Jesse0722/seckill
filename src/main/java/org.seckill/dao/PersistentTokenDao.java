package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.PersistentLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
public interface PersistentTokenDao {

    int insert(@Param("series") String series, @Param("token") String token, @Param("userName") String userName, @Param("date") Date date);

    int update(@Param("series") String series, @Param("token") String token, @Param("date") Date date);

    PersistentLogin getTokenForSeries(@Param("series") String series);

    int delete(@Param("userName") String userName);

}
