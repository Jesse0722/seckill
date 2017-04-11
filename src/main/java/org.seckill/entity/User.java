package org.seckill.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jesse on 2017/4/10.
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private int roleId;
    private Set<Role> roles = new HashSet<Role>(0);

    public User(){
    }

    public User(String userName, String password, String email, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(String userName, String password, String email, Integer roleId) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString(){
        return "User{"+
                "user_name:"+userName+
                "password:"+password+
                "email:"+email+
                "role:"+roleId;
    }
}
