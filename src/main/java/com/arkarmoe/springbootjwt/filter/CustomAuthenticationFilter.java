package com.arkarmoe.springbootjwt.filter;

import com.arkarmoe.springbootjwt.model.entity.Menu;
import com.arkarmoe.springbootjwt.repo.UserRepo;
import com.arkarmoe.springbootjwt.utility.Constant;
import com.arkarmoe.springbootjwt.utility.Utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@Getter @Setter
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private Utils utils;
    //inject with constructor
    /*public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }*/

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Authentication is checking.");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        log.info("Username is {} | Password is {}", username, password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        /** active the user status **/
        utils.actionUserStatus(user.getUsername(),true);

        /** access token and refresh tokens **/
        Algorithm algorithm = Algorithm.HMAC256(Constant.Token.SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.Token.ACCESS_TOKEN_EXPIRE))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(Constant.Token.ROLES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.Token.REFRESH_TOKEN_EXPIRE))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        /** response **/
        Map<String, Object> tokens = new HashMap<>();
        tokens.put(Constant.Token.ACCESS_TOKEN, access_token);
        tokens.put(Constant.Token.REFRESH_TOKEN, refresh_token);
        List<String> menuList = utils.takeMenuListsOfUser(user.getUsername());
        if(menuList!=null) tokens.put(Constant.Token.MENU_PERMISSION, menuList);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
