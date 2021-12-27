package com.arkarmoe.springbootjwt.controller;

import com.arkarmoe.springbootjwt.model.entity.Menu;
import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.service.UserService;
import com.arkarmoe.springbootjwt.utility.Constant;
import com.arkarmoe.springbootjwt.utility.Utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created by Arkar on 27-Dec-2021
 **/
@RestController
@Slf4j
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {
    private final UserService userService;

    /**
     * REFRESH TOKEN
     **/
    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(Constant.Token.SECRET.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();

                //get user from db
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + Constant.Token.ACCESS_TOKEN_EXPIRE))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(Constant.Token.ROLES, user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, Object> tokens = new HashMap<>();
                tokens.put(Constant.Token.ACCESS_TOKEN, access_token);
                tokens.put(Constant.Token.REFRESH_TOKEN, refresh_token);
                boolean isAdminRole = new Utils().checkUserHasAdminRole(user);
                if(!isAdminRole){
                    List<String> menuNames = user.getMenus().stream().map(m -> m.getName()).collect(Collectors.toList());
                    tokens.put(Constant.Token.MENU_PERMISSION, menuNames);
                }

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                //response
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing.");
        }
    }
}
