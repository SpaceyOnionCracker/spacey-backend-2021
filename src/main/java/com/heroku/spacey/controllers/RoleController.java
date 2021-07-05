package com.heroku.spacey.controllers;

import com.heroku.spacey.entity.Role;
import com.heroku.spacey.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/employees")
    public List<Role> getEmployeeRoles() {
        return roleService.getEmployeeRoles();
    }
}
