package com.colab.restaurant.model.entity;

import com.colab.restaurant.model.enums.QuotaTransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Document("quotaTransactions")
@Data
@Builder
public class QuotaTransaction {
    @Id
    private String id;
    private String shopCode;
    private String orderCode; // from customer
    private List<QuotaOrder> orders;
    private double totalAmount; // total amt from orders
    private String paySlip;
    private QuotaTransactionStatus status;
    private String description;
    private Timestamp createTime;
}
