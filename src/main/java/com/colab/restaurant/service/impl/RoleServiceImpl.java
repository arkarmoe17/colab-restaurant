package com.colab.restaurant.service.impl;

import com.colab.restaurant.model.entity.Role;
import com.colab.restaurant.repo.RoleRepo;
import com.colab.restaurant.service.RoleService;
import com.colab.restaurant.model.enums.RoleName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Override
    public ResponseEntity<List<Role>> fetchAllRoles() {
        log.info("[START] Fetching all the user roles.");
        List<Role> roles = roleRepo.findAllByOrderByName();
        log.info("Total element:{}",roles.size());
        log.info("[END] Fetching all the user roles.\n");
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<?> createRole(RoleName roleName) {
        log.info("[START] Creating the user role.");
        log.info("Role name:{}", roleName.name());
        Optional<Role> roleOptional = roleRepo.findByName(roleName.name());
        if(roleOptional.isPresent()) return new ResponseEntity<>("Role name is already existed.", HttpStatus.BAD_REQUEST);
        Role role = new Role();
        role.setName(roleName.name());
        roleRepo.save(role);
        log.info("[END] Creating the user role.\n");
        return ResponseEntity.ok(role);
    }
}
