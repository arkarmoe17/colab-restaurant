package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.DiningTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiningTableRepo extends MongoRepository<DiningTable, String> {
    Optional<DiningTable> findByTableCode(String tableCode);
    Optional<DiningTable> findByShopCodeAndName(String shopCode, String name);
    List<DiningTable> findAllByShopCodeOrderByName(String shopCode);
}
