package com.heroku.spacey.dao;

import com.heroku.spacey.entity.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getEmployeeRoles();
    Long getRoleId(String roleName);
    String getRoleName(long id);
    long insertRole(String roleName);
}
