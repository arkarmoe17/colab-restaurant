//package com.colab.restaurant.config;
//
//import com.colab.restaurant.filter.CustomAuthenticationFilter;
//import com.colab.restaurant.filter.CustomAuthorizationFilter;
//import com.colab.restaurant.utility.Utils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.http.HttpMethod.GET;
//import static org.springframework.http.HttpMethod.POST;
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final Utils utils;
//    private final CustomAuthorizationFilter customAuthorizationFilter;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), userRepo);
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
//        customAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        customAuthenticationFilter.setUtils(utils);
//        customAuthenticationFilter.setFilterProcessesUrl("/api/user/login");
//
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        //token endpoints
//        http.authorizeRequests().antMatchers("/api/user/login/**","/api/user/logout/**", "/api/token/refresh/**").permitAll();
//        //userRole endpoints
//        http.authorizeRequests().antMatchers("/api/role/**").hasAnyAuthority("ROLE_ADMIN");
//        //user endpoints
//        http.authorizeRequests().antMatchers(GET, "/api/user/lists/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/user/register/**").hasAnyAuthority("ROLE_ADMIN");
//        //Menu endpoints
//        http.authorizeRequests().antMatchers("/api/menu/**").hasAnyAuthority("ROLE_ADMIN");
//
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(customAuthenticationFilter); //add authentication filter
////        http.addFilterBefore(new CustomAuthorizationFilter(utils), UsernamePasswordAuthenticationFilter.class); //add authorization filter
//        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); //add authorization filter
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//}
