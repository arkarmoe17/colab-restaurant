package com.colab.restaurant.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quota")
@Data
public class Quota {
    @Id
    private String id;
    private String shopCode; // ref of restaurant
    private int tableNumber;
    private int categoryNumber;
    private int adminAccNumber;
    private int staffAccNumber;
    private int slipNumber;
}
