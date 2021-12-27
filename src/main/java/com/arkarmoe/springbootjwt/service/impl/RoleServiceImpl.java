package com.arkarmoe.springbootjwt.service.impl;

import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.repo.RoleRepo;
import com.arkarmoe.springbootjwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Override
    public ResponseEntity<?> createRole(Role role) {
        log.info("[START] Creating the user role.");
        log.info("Role name:{}", role.getName());
        Optional<Role> roleOptional = roleRepo.findByName(role.getName());
        if(roleOptional.isPresent()) return new ResponseEntity<>("Role name is already existed.", HttpStatus.BAD_REQUEST);
        roleRepo.save(role);
        log.info("[END] Creating the user role.\n");
        return ResponseEntity.ok(role);
    }
}
