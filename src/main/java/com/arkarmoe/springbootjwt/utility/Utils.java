package com.arkarmoe.springbootjwt.utility;

import com.arkarmoe.springbootjwt.model.entity.Role;
import com.arkarmoe.springbootjwt.model.entity.User;
import com.arkarmoe.springbootjwt.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Arkar on 27-Dec-2021
 * **/

@Slf4j
public class Utils {
    /**
     * Check Admin Role or not
     * **/
    public static boolean checkUserHasAdminRole(User user){
        if(user==null) return false;
        for(Role r : user.getRoles()){
            if(r.getName().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }
}
