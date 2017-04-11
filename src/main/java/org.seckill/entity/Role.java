package org.seckill.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jesse on 2017/4/10.
 */
public class Role {
    private int roleId;
    private String roleName;
    private User user;

    public Role(){

    }
    public Role(User user) {
        this.user = user;
    }

    public Role(User user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @Override
//    public String toString(){
//
//    }
}
