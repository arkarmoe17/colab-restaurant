package com.colab.restaurant.controller;

import com.colab.restaurant.mapper.ContractMapper;
import com.colab.restaurant.model.dto.ContractDTO;
import com.colab.restaurant.model.entity.Contract;
import com.colab.restaurant.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/contracts")
@RequiredArgsConstructor
@Tag(name = "Contract", description = "Contract API")
public class ContractController {
    private final ContractService contractService;

    @Operation(summary = "Fetching the contractStatus list")
    @GetMapping("/status/list")
    public ResponseEntity<List<String>> getAllContractStatus() {
        return ResponseEntity.ok(contractService.getAllContractStatus());
    }

    @Operation(summary = "Fetching Contract list with shopCode")
    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts(@RequestHeader("shop-code") String shopCode) {
        return ResponseEntity.ok(contractService.getAllContractsByShopCode(shopCode));
    }

    @Operation(summary = "Save Contract")
    @PostMapping
    public ResponseEntity<ContractDTO> saveContract(@RequestBody ContractDTO requestDTO) {
        Contract contract = ContractMapper.INSTANCE.toEntity(requestDTO);
        return ResponseEntity.ok(contractService.saveContract(contract));
    }

    @Operation(summary = "Update Contract")
    @PutMapping
    public ResponseEntity<ContractDTO> updateContract(@RequestBody ContractDTO requestDTO) {
        Contract contract = ContractMapper.INSTANCE.toEntity(requestDTO);
        return ResponseEntity.ok(contractService.saveContract(contract));
    }


}
