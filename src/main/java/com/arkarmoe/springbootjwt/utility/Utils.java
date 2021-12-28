package com.arkarmoe.springbootjwt.utility;

import com.arkarmoe.springbootjwt.model.entity.Menu;
import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.model.enums.RoleName;
import com.arkarmoe.springbootjwt.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Arkar on 27-Dec-2021
 **/

@Slf4j
@Component
public class Utils {
    private static UserRepo userRepo;

    @Autowired
    private UserRepo userRepository;

    @PostConstruct
    public void init() {
        System.out.println("Inside Init method");
        this.userRepo = userRepository;
        System.out.println("Outside Init method");
    }

    /**
     * Take User Menu lists , except ROLE_ADMIN
     **/
    public static List<String> takeMenuListsOfUser(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (!userOptional.isPresent()) return null;
        boolean flag = false;
        for (Role r : userOptional.get().getRoles()) {
            if (r.getName().equals(RoleName.ROLE_ADMIN.name())) {
                flag = true;
                break; 
            }
        }
        if (flag) return null;
        else{
            return userOptional.get().getMenus().stream().map(m -> m.getName()).collect(Collectors.toList());
        }
    }

    /**
     * Check Admin Role or not
     **/
    public static boolean checkUserHasAdminRole(User user) {
        if (user == null) return false;
        for (Role r : user.getRoles()) {
            if (r.getName().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }

    /**
     * Active or Inactive the user
     **/
    public static void activeUserActionStatus(String username, boolean actionBool) {

    }

}
