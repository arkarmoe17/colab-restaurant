package com.arkarmoe.springbootjwt.service;

import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.model.request.UserReq;
import org.springframework.http.ResponseEntity;

import java.util.List;
/**
 * Created by Arkar on 27-Dec-2021
 * **/
public interface UserService {
    ResponseEntity<List<User>> fetchAllUsers();
    ResponseEntity<?> registerUser(UserReq userReq);
    ResponseEntity<?> logoutUser(String username);
    ResponseEntity<?> assignRolesToUser(Long userId,List<Long> roleIds);
    ResponseEntity<?> assignMenusToUser(Long userId,List<Long> menuIds);
    User getUser(String username);
}
