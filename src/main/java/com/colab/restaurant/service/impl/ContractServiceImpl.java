package com.colab.restaurant.service.impl;

import com.colab.restaurant.exceptions.BadRequestException;
import com.colab.restaurant.mapper.ContractMapper;
import com.colab.restaurant.model.dto.ContractDTO;
import com.colab.restaurant.model.entity.Contract;
import com.colab.restaurant.model.enums.ContractStatus;
import com.colab.restaurant.repo.ContractRepo;
import com.colab.restaurant.repo.RestaurantRepo;
import com.colab.restaurant.service.ContractService;
import com.colab.restaurant.utility.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {
    private final ContractRepo contractRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public List<String> getAllContractStatus() {
        log.info("Fetching all the contract status list.");
        return Arrays.stream(ContractStatus.values())
                .map(Enum::name)
                .toList();
    }

    @Override
    public List<ContractDTO> getAllContractsByShopCode(String shopCode) {
        log.info("Fetching the contracts by shopCode:{}", shopCode);
        restaurantRepo.findByShopCode(shopCode)
                .orElseThrow(() -> new BadRequestException(Constant.Message.SHOP_CODE_NOT_FOUND));
        return ContractMapper.INSTANCE
                .toDtoList(contractRepo.findAllByShopCodeOrderByContractDate(shopCode));
    }


    @Override
    public ContractDTO saveContract(Contract contract) {
        log.info("Saving the contract with shopCode:{}", contract.getShopCode());
        restaurantRepo.findByShopCode(contract.getShopCode())
                .orElseThrow(() -> new BadRequestException(Constant.Message.SHOP_CODE_NOT_FOUND));
        contractRepo.save(contract);
        return ContractMapper.INSTANCE.toDto(contract);
    }

}
