package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends MongoRepository<Contract, String> {
}
