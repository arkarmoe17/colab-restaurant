package com.arkarmoe.springbootjwt.controller;

import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.model.request.UserReq;
import com.arkarmoe.springbootjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Arkar on 27-Dec-2021
 **/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * FETCH ALL USERS
     **/
    @GetMapping("/lists")
    public ResponseEntity<List<User>> fetchAllUsers() {
        return userService.fetchAllUsers();
    }

    /**
     * Create User
     **/
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserReq req) {
        return userService.registerUser(req);
    }

    /**
     * Assign Role to User
     **/
    @PostMapping("/{userId}/role")
    public ResponseEntity<?> assignRolesToUser(@PathVariable("userId") Long userId,
                                               @RequestBody List<Long> roleIds) {
        return userService.assignRolesToUser(userId, roleIds);
    }

    /**
     * Assign Menu lists to user
     * **/
    @PostMapping("/{userId}/menu")
    public ResponseEntity<?> assignMenuToUser(@PathVariable("userId")Long userId,
                                              @RequestBody List<Long> menuIds){
        return userService.assignMenusToUser(userId,menuIds);
    }
}

