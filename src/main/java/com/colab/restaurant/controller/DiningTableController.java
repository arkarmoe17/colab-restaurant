package com.colab.restaurant.controller;

import com.colab.restaurant.mapper.DiningTableMapper;
import com.colab.restaurant.model.dto.DiningTableDTO;
import com.colab.restaurant.model.entity.DiningTable;
import com.colab.restaurant.service.DiningTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/diningTables")
@RequiredArgsConstructor
@Tag(name = "DiningTables", description = "Dining Tables API")
public class DiningTableController {
    private final DiningTableService diningTableService;

    @Operation(summary = "Fetching the Dining Tables")
    @GetMapping
    public ResponseEntity<List<DiningTableDTO>> fetchingDiningTables(@RequestHeader("shop-code") String shopCode,
                                                                     @RequestParam(value = "isActive", required = false) Boolean isActive) {
        return ResponseEntity.ok(diningTableService.fetchingDiningTables(shopCode, isActive));
    }

    @Operation(summary = "Create Dining Table")
    @PostMapping
    public ResponseEntity<DiningTableDTO> createDiningTable(@RequestBody DiningTableDTO requestDto) {
        DiningTable diningTable = DiningTableMapper.INSTANCE.toEntity(requestDto);
        return ResponseEntity.ok(diningTableService.createDiningTable(diningTable));
    }

    @Operation(summary = "Update Dining Table")
    @PutMapping
    public ResponseEntity<DiningTableDTO> updateDiningTable(@RequestBody DiningTableDTO requestDto) {
        DiningTable diningTable = DiningTableMapper.INSTANCE.toEntity(requestDto);
        return ResponseEntity.ok(diningTableService.updateDiningTable(diningTable));
    }

}
