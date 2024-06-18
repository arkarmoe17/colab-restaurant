package com.colab.restaurant.controller;

import com.colab.restaurant.mapper.OwnerMapper;
import com.colab.restaurant.model.dto.OwnerDTO;
import com.colab.restaurant.model.entity.Owner;
import com.colab.restaurant.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/owners")
@RequiredArgsConstructor
@Tag(name = "Owner", description = "Owner API")
public class OwnerController {
    private final OwnerService ownerService;

    // create
    @Operation(summary = "Owner Creation")
    @PostMapping
    public ResponseEntity<Void> createOwner(@RequestBody OwnerDTO dto) {
        Owner owner = OwnerMapper.INSTANCE.toEntity(dto);
        ownerService.createOwner(owner);
        return ResponseEntity.ok().build();
    }

    // find by shop code
    @Operation(summary = "Fetching the owners by shopCode")
    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAllByShopCode(@RequestHeader("shop-code") String shopCode) {
        return ResponseEntity.ok(ownerService.findAllByShopCode(shopCode));
    }
}
