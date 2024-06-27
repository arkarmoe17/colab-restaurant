package com.colab.restaurant.model.dto;

import com.colab.restaurant.model.entity.QuotaOrder;
import com.colab.restaurant.model.enums.QuotaTransactionStatus;
import lombok.Data;

import java.util.List;

@Data
public class QuotaTransactionDTO {
    private String shopCode;
    private String orderCode; // from customer
    private List<QuotaOrder> orders;
    private double totalAmount; // total amt from orders
    private String paySlip;
    private QuotaTransactionStatus status;
    private String description;
}
