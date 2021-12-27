package com.arkarmoe.springbootjwt.service;

import com.arkarmoe.springbootjwt.model.User;
import com.arkarmoe.springbootjwt.request.UserReq;
import org.springframework.http.ResponseEntity;

import java.util.List;
/**
 * Created by Arkar on 27-Dec-2021
 * **/
public interface UserService {
    List<User> fetchAllUsers();
    ResponseEntity<?> registerUser(UserReq userReq);
    ResponseEntity<?> findByUsername(String username);
    User getUser(String username);
//    User saveUser(User user);
//    Role saveRole(Role role);
//    void addRoleToUser(String username,String roleName);
}
