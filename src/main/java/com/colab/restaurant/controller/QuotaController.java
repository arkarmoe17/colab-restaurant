package com.colab.restaurant.controller;

import com.colab.restaurant.mapper.QuotaMapper;
import com.colab.restaurant.model.dto.QuotaDTO;
import com.colab.restaurant.service.QuotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1.0/quota")
@RequiredArgsConstructor
@Tag(name = "Quota", description = "Restaurant Quota API")
public class QuotaController {
    private final QuotaService quotaService;

    @Operation(summary = "Fetching the quota by shopCode")
    @GetMapping
    public ResponseEntity<QuotaDTO> getQuotaByShopCode(@RequestHeader("shop-code") String shopCode) {
        return ResponseEntity.ok(quotaService.getQuotaByShopCode(shopCode));
    }

    @Operation(summary = "Creating the quota")
    @PostMapping()
    public ResponseEntity<Void> createQuota(@RequestBody QuotaDTO quotaDTO) {
        quotaService.createQuota(QuotaMapper.INSTANCE.toEntity(quotaDTO));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Updating the quota")
    @PutMapping()
    public ResponseEntity<QuotaDTO> updateQuota(@RequestBody QuotaDTO quotaDTO) {
        return ResponseEntity.ok(quotaService.updateQuota(QuotaMapper.INSTANCE.toEntity(quotaDTO)));
    }


}
