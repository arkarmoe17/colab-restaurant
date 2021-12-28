package com.arkarmoe.springbootjwt.service;

import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.model.enums.RoleName;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Arkar on 27-Dec-2021
 * **/
public interface RoleService {
    ResponseEntity<List<Role>> fetchAllRoles();
    ResponseEntity<?> createRole(RoleName role);
}
