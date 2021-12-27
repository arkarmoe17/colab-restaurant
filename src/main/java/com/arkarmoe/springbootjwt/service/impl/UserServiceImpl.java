package com.arkarmoe.springbootjwt.service.impl;

import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.model.request.UserReq;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * GET TOKEN
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user information from the database
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (!userOptional.isPresent()) {
            log.error("[FAIL] User is not found in the database.");
            throw new UsernameNotFoundException("User is not found in the database.");
        } else {
            log.info("[SUCCESS] User is found in the database.");
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
                return new ResponseEntity<>("Role id "+id+ " is not found",HttpStatus.BAD_REQUEST);
            roles.add(roleOptional.get());
        }
        log.info("Role lists:{}",roles.size());
        user.setRoles(roles);
        userRepo.save(user);
        log.info("[END] Assigning the user roles to userId:{}\n",userId);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> findByUsername(String username) {
        return null;
    }

    @Override
    public User getUser(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (!userOptional.isPresent()) throw new UsernameNotFoundException("User is not found in the database.");
        return userOptional.get();
    }


//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        User user = userRepo.findByUsername(username);
//        Role role = roleRepo.findByName(roleName);
//        user.getRoles().add(role);
//        userRepo.save(user);
//    }
}
