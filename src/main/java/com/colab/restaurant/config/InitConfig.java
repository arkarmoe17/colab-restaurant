package com.colab.restaurant.config;

import com.colab.restaurant.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitConfig implements ApplicationRunner {
    private final UserRepo userRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // create an instance of your @Document annotated class
//        User userDoc = User.builder()
//                .name("arkar")
//                .email("admin@gmail.com")
//                .build();
//
//        userDoc = userRepo.insert(userDoc);
    }
}
