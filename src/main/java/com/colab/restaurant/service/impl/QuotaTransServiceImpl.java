package com.colab.restaurant.service.impl;

import com.colab.restaurant.mapper.QuotaTransMapper;
import com.colab.restaurant.model.dto.QuotaTransactionDTO;
import com.colab.restaurant.model.entity.QuotaOrder;
import com.colab.restaurant.model.entity.QuotaTransaction;
import com.colab.restaurant.model.enums.QuotaTransactionStatus;
import com.colab.restaurant.repo.QuotaTransactionRepo;
import com.colab.restaurant.service.QuotaTransService;
import com.colab.restaurant.service.helper.QuotaServiceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuotaTransServiceImpl implements QuotaTransService {
    private final QuotaTransactionRepo quotaTransactionRepo;
    private final QuotaServiceHelper quotaServiceHelper;

    @Override
    public QuotaTransactionDTO presentQuota(String shopCode, List<QuotaOrder> orders) {
        log.info("Present the quota to the shopCode:{}", shopCode);
        log.info("Orders:{}", orders);

        // save quotaTransaction
        QuotaTransaction quotaTransaction = QuotaTransaction.builder()
                .shopCode(shopCode)
                .orders(orders)
                .totalAmount(orders.stream().map(QuotaOrder::getAmount).reduce(0.0, Double::sum))
                .status(QuotaTransactionStatus.GIFT)
                .description("You got a present from admin.")
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        quotaTransactionRepo.save(quotaTransaction);

        // update quota values
        quotaServiceHelper.updateQuotaValues(shopCode, orders);

        return QuotaTransMapper.INSTANCE.toDto(quotaTransaction);
    }
}
