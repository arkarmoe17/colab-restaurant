package com.arkarmoe.springbootjwt.service;

import com.arkarmoe.springbootjwt.model.Role;
import com.arkarmoe.springbootjwt.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User saveUser(User user);
    User getUser(String username);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
}
