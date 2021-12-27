package com.arkarmoe.springbootjwt.controller;

import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Arkar on 27-Dec-2021
 **/
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    //create role
    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
}
