package com.colab.restaurant.service;

import com.colab.restaurant.model.dto.QuotaTransactionDTO;
import com.colab.restaurant.model.entity.QuotaOrder;

import java.util.List;

public interface QuotaTransService {
    QuotaTransactionDTO presentQuota(String shopCode, List<QuotaOrder> orders);
}
