package com.colab.restaurant.service.impl;

import com.colab.restaurant.model.enums.ContractStatus;
import com.colab.restaurant.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {
    @Override
    public List<String> getAllContractStatus() {
        log.info("Fetching all the contract status list.");
        return Arrays.stream(ContractStatus.values())
                .map(Enum::name)
                .toList();
    }
}
