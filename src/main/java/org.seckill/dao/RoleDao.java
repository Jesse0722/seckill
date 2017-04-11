package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Role;

import java.util.Set;

/**
 * Created by jesse on 2017/4/11.
 */
public interface RoleDao {

    int insertRole(@Param("roleName") String roleName,@Param("userId") int userId);

    Role getById(@Param("roleId") int roleId);
}
