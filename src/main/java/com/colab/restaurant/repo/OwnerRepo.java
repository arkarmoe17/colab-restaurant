package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepo extends MongoRepository<Owner, String> {
    List<Owner> findAllByShopCodeOrderByName(String shopCode);
}
