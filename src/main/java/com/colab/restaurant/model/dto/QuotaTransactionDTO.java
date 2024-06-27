package com.colab.restaurant.model.dto;

import com.colab.restaurant.model.entity.QuotaOrder;
import com.colab.restaurant.model.enums.QuotaTransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
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
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Timestamp createTime;
}
