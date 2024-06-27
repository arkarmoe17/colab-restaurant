package com.colab.restaurant.model.entity;

import lombok.Data;

@Data
public class QuotaOrder {
    private String orderType;
    private int quantity;
    private double amount;
}
