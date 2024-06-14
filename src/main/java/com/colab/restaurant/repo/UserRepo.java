package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAllByOrderByUsername();

    Optional<User> findByUsername(String username);
}
