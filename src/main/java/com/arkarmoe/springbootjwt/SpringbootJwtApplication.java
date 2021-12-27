package com.arkarmoe.springbootjwt;

import com.arkarmoe.springbootjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            System.out.println("Command line runner is running.");
//            userService.saveRole(new Role(null,"ROLE_OPERATOR"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//            userService.saveUser(new User(null,"Arkar Moe","arkar","1234",new ArrayList<>()));
//            userService.addRoleToUser("arkar","ROLE_USER");
        };
    }
}
