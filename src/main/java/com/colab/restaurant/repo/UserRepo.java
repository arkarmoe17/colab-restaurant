package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findAllByOrderByUsername();
    Optional<User> findByUsername(String username);
}
