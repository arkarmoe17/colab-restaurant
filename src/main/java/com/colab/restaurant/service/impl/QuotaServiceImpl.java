package com.colab.restaurant.service.impl;

import com.colab.restaurant.exceptions.BadRequestException;
import com.colab.restaurant.mapper.QuotaMapper;
import com.colab.restaurant.model.dto.QuotaDTO;
import com.colab.restaurant.model.entity.Quota;
import com.colab.restaurant.repo.QuotaRepo;
import com.colab.restaurant.service.QuotaService;
import com.colab.restaurant.utility.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuotaServiceImpl implements QuotaService {
    private final QuotaRepo quotaRepo;

    @Override
    public QuotaDTO getQuotaByShopCode(String shopCode) {
        log.info("Fetching the quota by shopCode:{}", shopCode);
        Optional<Quota> quotaOptional = quotaRepo.findByShopCode(shopCode);
        return quotaOptional.map(QuotaMapper.INSTANCE::toDto).orElse(null);
    }

    @Override
    public void createQuota(Quota quota) {
        log.info("Creating the quota by shopCode:{}", quota.getShopCode());
        Optional<Quota> quotaOptional = quotaRepo.findByShopCode(quota.getShopCode());
        if (quotaOptional.isPresent())
            throw new BadRequestException(Constant.Message.DATA_IS_EXISTED);
        quotaRepo.save(quota);
        log.info("Quota: {}", quota);
    }

    @Override
    public QuotaDTO updateQuota(Quota quota) {
        log.info("Updating the quota by shopCode:{}", quota.getShopCode());
        Quota quota1 = quotaRepo.findByShopCode(quota.getShopCode())
                .orElseThrow(() -> new BadRequestException(Constant.Message.SHOP_CODE_NOT_FOUND));
        quota.setId(quota1.getId());
        quotaRepo.save(quota);
        log.info("Quota:{}", quota);
        return QuotaMapper.INSTANCE.toDto(quota);
    }
}
