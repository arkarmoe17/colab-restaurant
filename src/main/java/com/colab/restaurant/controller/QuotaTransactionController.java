package com.colab.restaurant.controller;

import com.colab.restaurant.model.dto.QuotaTransactionDTO;
import com.colab.restaurant.model.entity.QuotaOrder;
import com.colab.restaurant.service.QuotaTransService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/quotaTransactions")
@RequiredArgsConstructor
@Tag(name = "Quota Transactions", description = "Quota Transaction API")
public class QuotaTransactionController {
    private final QuotaTransService quotaTransService;

    @Operation(summary = "Present the quotas to the customer")
    @PostMapping()
    public ResponseEntity<QuotaTransactionDTO> presentQuota(@RequestHeader("shop-code") String shopCode,
                                                 @RequestBody List<QuotaOrder> orders) {
        return ResponseEntity.ok(quotaTransService.presentQuota(shopCode, orders));
    }

}
