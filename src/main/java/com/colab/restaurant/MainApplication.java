package com.colab.restaurant;


import com.colab.restaurant.model.entity.User;
import com.colab.restaurant.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//

//    CommandLineRunner run(RoleService roleService) {
//        return args -> {
//            System.out.println("Command line runner is running.");
////            roleService.createRole(RoleName.ROLE_ADMIN);
//        };
//    }
}
