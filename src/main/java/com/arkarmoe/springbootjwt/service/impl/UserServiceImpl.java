package com.arkarmoe.springbootjwt.service.impl;

import com.arkarmoe.springbootjwt.model.User;
import com.arkarmoe.springbootjwt.repo.RoleRepo;
import com.arkarmoe.springbootjwt.repo.UserRepo;
import com.arkarmoe.springbootjwt.request.UserReq;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
     * TOKEN
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
    public List<User> fetchAllUsers() {
        return userRepo.findAll();
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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/register").toUriString());
        log.info("[END] Registering the user.\n");
        return ResponseEntity.created(uri).body(user);
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
//    public User saveUser(User user) {
//        log.info("Saving the user: {}",user.getUsername());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepo.save(user);
//    }

//    @Override
//    public Role saveRole(Role role) {
//        log.info("Saving role:{}",role.getName());
//        return roleRepo.save(role);
//    }

//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        User user = userRepo.findByUsername(username);
//        Role role = roleRepo.findByName(roleName);
//        user.getRoles().add(role);
//        userRepo.save(user);
//    }
}
