package com.arkarmoe.springbootjwt.service.impl;

import com.arkarmoe.springbootjwt.model.entity.Menu;
import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.model.request.UserReq;
import com.arkarmoe.springbootjwt.repo.MenuRepo;
import com.arkarmoe.springbootjwt.repo.RoleRepo;
import com.arkarmoe.springbootjwt.repo.UserRepo;
import com.arkarmoe.springbootjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final MenuRepo menuRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * GET TOKEN
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user information from the database
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (!userOptional.isPresent()) {
            log.error("Username:{} is not found in the database.",username);
            throw new UsernameNotFoundException("User is not found in the database.");
        }else{
            log.info("Username:{} is found in the database.",username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userOptional.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(userOptional.get().getUsername(), userOptional.get().getPassword(), authorities);
    }

    @Override
    public ResponseEntity<List<User>> fetchAllUsers() {
        log.info("[START] Fetching all the user lists.");
        List<User> users = userRepo.findAllByOrderByUsername();
        log.info("Total element:{}",users.size());
        log.info("[END] Fetching all the user lists.\n");
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<?> registerUser(UserReq userReq) {
        log.info("[START] Registering the user.");
        log.info(userReq.toString());
        //check username is already existed or not
        Optional<User> userOptional = userRepo.findByUsername(userReq.getUsername());
        if (userOptional.isPresent())
            return new ResponseEntity<>("Username is already existed.", HttpStatus.BAD_REQUEST);

        User user = new User();
        user.setName(userReq.getName());
        user.setUsername(userReq.getUsername());
        user.setRoles(null);
        user.setPassword(passwordEncoder.encode(userReq.getPassword()));
        userRepo.save(user);
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/register").toUriString());
        //return ResponseEntity.created(uri).body(user);
        log.info("[END] Registering the user.\n");
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> logoutUser(String username) {
        log.info("[START] Logout the user by username:{}",username);
        Optional<User> userOptional = userRepo.findByUsername(username);
        if(!userOptional.isPresent())
            return new ResponseEntity<>("Username is not found.",HttpStatus.BAD_REQUEST);
        User user = userOptional.get();
        user.setActive(false);
        userRepo.save(user);
        log.info("[END] Logout the user by username:{}\n",username);
        return ResponseEntity.ok("Success.");
    }

    @Override
    public ResponseEntity<?> assignRolesToUser(Long userId, List<Long> roleIds) {
        log.info("[START] Assigning the user roles to userId:{}",userId);
        log.info("Role ids:{}",roleIds);
        Optional<User> userOptional = userRepo.findById(userId);
        if(!userOptional.isPresent())
            return new ResponseEntity<>("User id is not found.",HttpStatus.BAD_REQUEST);
        User user = userOptional.get();
        List<Role> roles = new ArrayList<>();
        for(Long id : roleIds){
            Optional<Role> roleOptional = roleRepo.findById(id);
            if(!roleOptional.isPresent())
                return new ResponseEntity<>("Role id "+id+ " is not found.",HttpStatus.BAD_REQUEST);
            roles.add(roleOptional.get());
        }
        log.info("Role lists:{}",roles.size());
        user.setRoles(roles);
        userRepo.save(user);
        log.info("[END] Assigning the user roles to userId:{}\n",userId);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> assignMenusToUser(Long userId, List<Long> menuIds) {
        log.info("[START] Assigning the menu lists to userId:{}",userId);
        Optional<User> userOptional = userRepo.findById(userId);
        if(!userOptional.isPresent())
            return new ResponseEntity<>("User id is not found.",HttpStatus.BAD_REQUEST);
        User user = userOptional.get();
        List<Menu> menuList = new ArrayList<>();
        for(Long id : menuIds){
            Optional<Menu> menuOptional = menuRepo.findById(id);
            if(!menuOptional.isPresent())
                return new ResponseEntity<>("Menu id: "+id+" is not found.",HttpStatus.BAD_REQUEST);
            menuList.add(menuOptional.get());
        }
        log.info("Menu lists:{}",menuList.size());
        user.setMenus((Set<Menu>) menuList);
        userRepo.save(user);
        log.info("[END] Assigning the menu lists to userId:{}\n",userId);
        return ResponseEntity.ok(user);
    }

    @Override
    public User getUser(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (!userOptional.isPresent()) throw new UsernameNotFoundException("User is not found in the database.");
        return userOptional.get();
    }

}
