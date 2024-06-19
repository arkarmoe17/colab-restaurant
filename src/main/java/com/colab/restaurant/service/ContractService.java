package com.colab.restaurant.service;

import com.colab.restaurant.model.dto.ContractDTO;
import com.colab.restaurant.model.entity.Contract;

import java.util.List;

public interface ContractService {
    List<String> getAllContractStatus();

    List<ContractDTO> getAllContractsByShopCode(String shopCode);

    ContractDTO saveContract(Contract contract);
}
