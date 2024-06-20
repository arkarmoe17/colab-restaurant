//package com.colab.restaurant.utility;
//
//import com.colab.restaurant.model.entity.Menu;
//import com.colab.restaurant.model.entity.Role;
//import com.colab.restaurant.model.entity.User;
//import com.colab.restaurant.model.enums.RoleName;
//import com.colab.restaurant.repo.UserRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * Created by Arkar on 27-Dec-2021
// **/
//
//@Slf4j
//@Component
//public class Utils {
//    private static UserRepo userRepo;
//    @Autowired
//    private UserRepo userRepository;
//
//    @PostConstruct
//    public void init() {
//        this.userRepo = userRepository;
//    }
//
//
//
//    /**
//     * Check Admin Role or not
//     **/
//    public static boolean checkUserHasAdminRole(User user) {
//        if (user == null) return false;
//        for (Role r : user.getRoles()) {
//            if (r.getName().equals("ROLE_ADMIN")) return true;
//        }
//        return false;
//    }
//
//    /**
//     * Active or Inactive the user
//     **/
//    public static boolean checkUserIsActive(String username) {
//        Optional<User> userOptional = userRepo.findByUsername(username);
//        if (!userOptional.isPresent()) return false;
//        User user = userOptional.get();
//        return user.isActive();
//    }
//
//    /**
//     * Action the user status
//     **/
//    public static void actionUserStatus(String username, boolean actionBool) {
//        Optional<User> userOptional = userRepo.findByUsername(username);
//        if (!userOptional.isPresent()) {
//            log.error("Username:{} is not found.\n", username);
//        } else {
//            User user = userOptional.get();
//            user.setActive(actionBool);
//            userRepo.save(user);
//        }
//    }
//
//}
