package com.colab.restaurant.controller;

import com.colab.restaurant.mapper.OwnerMapper;
import com.colab.restaurant.model.dto.OwnerDTO;
import com.colab.restaurant.model.entity.Owner;
import com.colab.restaurant.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    // create
    @PostMapping
    public ResponseEntity<Void> createOwner(@RequestBody OwnerDTO dto) {
        Owner owner = OwnerMapper.INSTANCE.toEntity(dto);
        ownerService.createOwner(owner);
        return ResponseEntity.ok().build();
    }

    // find by shop code
    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAllByShopCode(@RequestHeader("shop-code") String shopCode) {
        return ResponseEntity.ok(ownerService.findAllByShopCode(shopCode));
    }
}
