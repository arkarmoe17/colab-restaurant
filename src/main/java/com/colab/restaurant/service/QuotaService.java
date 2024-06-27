package com.colab.restaurant.service;

import com.colab.restaurant.model.dto.QuotaDTO;
import com.colab.restaurant.model.entity.Quota;

public interface QuotaService {
    QuotaDTO getQuotaByShopCode(String shopCode);
    void createQuota(Quota quota);
    QuotaDTO updateQuota(Quota quota);
}
