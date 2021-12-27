package com.arkarmoe.springbootjwt.service;

import com.arkarmoe.springbootjwt.model.entity.Role;
import org.springframework.http.ResponseEntity;

/**
 * Created by Arkar on 27-Dec-2021
 * **/
public interface RoleService {
    ResponseEntity<?> createRole(Role role);
}
