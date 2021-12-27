package com.arkarmoe.springbootjwt.controller;

import com.arkarmoe.springbootjwt.model.User;
import com.arkarmoe.springbootjwt.request.UserReq;
import com.arkarmoe.springbootjwt.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Created by Arkar on 27-Dec-2021
 * **/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * FETCH ALL USERS
     * **/
    @GetMapping("/lists")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.fetchAllUsers());
    }

    /**
     * Create User
     * **/
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserReq req){
        return userService.registerUser(req);
    }

    /*@PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }*/

//
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
