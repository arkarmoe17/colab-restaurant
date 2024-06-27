package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.QuotaTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuotaTransactionRepo extends MongoRepository<QuotaTransaction, String> {
    Optional<QuotaTransaction> findByShopCode(String shopCode);
}
