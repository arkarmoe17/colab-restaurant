package com.colab.restaurant.controller;

import com.colab.restaurant.model.request.UserReq;
import com.colab.restaurant.service.UserService;
import com.colab.restaurant.model.entity.User;
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
     * Logout
     * **/
    @PostMapping("/logout/{username}")
    public ResponseEntity<?> logout(@PathVariable("username")String username){
        return userService.logoutUser(username);
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

