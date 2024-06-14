//package com.colab.restaurant.controller;
//
//import com.colab.restaurant.model.entity.Role;
//import com.colab.restaurant.service.RoleService;
//import com.colab.restaurant.model.enums.RoleName;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by Arkar on 27-Dec-2021
// **/
//@RestController
//@RequestMapping("/api/role")
//@RequiredArgsConstructor
//public class RoleController {
//    private final RoleService roleService;
//
//    // fetch all roles
//    @GetMapping("/lists")
//    public ResponseEntity<List<Role>> fetchAllRoles(){
//        return roleService.fetchAllRoles();
//    }
//
//    //create role
//    @PostMapping()
//    public ResponseEntity<?> createRole(@RequestParam("roleName") RoleName role) {
//        return roleService.createRole(role);
//    }
//}
