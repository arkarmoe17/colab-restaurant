package com.colab.restaurant.repo;

import com.colab.restaurant.model.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepo extends MongoRepository<Contract, String> {
    List<Contract> findAllByShopCodeOrderByContractDate(String shopCode);
}
