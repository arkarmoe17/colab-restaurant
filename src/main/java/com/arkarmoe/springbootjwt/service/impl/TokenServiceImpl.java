package com.arkarmoe.springbootjwt.service.impl;

import com.arkarmoe.springbootjwt.model.User;
import com.arkarmoe.springbootjwt.repo.UserRepo;
import com.arkarmoe.springbootjwt.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UserRepo userRepo;
    /**
     * TOKEN
     **/
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //load user information from the database
//        Optional<User> userOptional = userRepo.findByUsername(username);
//        if (!userOptional.isPresent()) {
//            log.error("[FAIL] User is not found in the database.");
//            throw new UsernameNotFoundException("User is not found in the database.");
//        } else {
//            log.info("[SUCCESS] User is found in the database.");
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        userOptional.get().getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
//        return new org.springframework.security.core.userdetails.User(userOptional.get().getUsername(), userOptional.get().getPassword(), authorities);
//    }
}
