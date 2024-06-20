package com.colab.restaurant.model.dto;

import lombok.Data;

@Data
public class DiningTableDTO {
    private String shopCode;
    private String name;
    private String description;
    private String tableCode;
    private boolean isActive;
}
