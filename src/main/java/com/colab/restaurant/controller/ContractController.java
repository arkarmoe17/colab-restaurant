package com.colab.restaurant.controller;

import com.colab.restaurant.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
