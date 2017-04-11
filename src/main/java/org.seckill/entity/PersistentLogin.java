package org.seckill.entity;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
public class PersistentLogin {
    private String series;
    private String userName;
    private String token;



    private Date date;

    public PersistentLogin(){

    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
