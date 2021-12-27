package com.arkarmoe.springbootjwt.controller;

import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Arkar on 27-Dec-2021
 **/
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    // fetch all roles
    @GetMapping("/lists")
    public ResponseEntity<List<Role>> fetchAllRoles(){
        return roleService.fetchAllRoles();
    }

    //create role
    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
}
