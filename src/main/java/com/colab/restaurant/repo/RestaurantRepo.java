package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends MongoRepository<Restaurant, String> {
    Optional<Restaurant> findByShopCode(String shopCode);

}
