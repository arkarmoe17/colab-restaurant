package com.colab.restaurant.model.dto;

import lombok.Data;

@Data
public class QuotaDTO {
    private String shopCode;
    private Integer tableNumber;
    private Integer categoryNumber;
    private Integer adminAccNumber;
    private Integer staffAccNumber;
    private Integer slipNumber;
}
