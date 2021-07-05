package com.heroku.spacey.services.impl;

import com.heroku.spacey.dao.RoleDao;
import com.heroku.spacey.entity.Role;
import com.heroku.spacey.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Override
    public List<Role> getEmployeeRoles() {
        return roleDao.getEmployeeRoles();
    }
}
