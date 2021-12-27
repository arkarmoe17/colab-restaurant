package com.arkarmoe.springbootjwt.repo;

import com.arkarmoe.springbootjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
//    User findByUsername(String username);
    Optional<User> findByUsername(String username);
}
