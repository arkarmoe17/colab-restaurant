package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.Quota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuotaRepo extends MongoRepository<Quota, String> {
    Optional<Quota> findByShopCode(String shopCode);
}
