package com.colab.restaurant.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

@Document("restaurants")
@Data
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String shopCode;
    private String email;
    private String description;
    private String address;
    private String phoneNumber;
    private String lineId; // optional
    private String logoImgUrl;
    private String posterImgUrl;
    private List<String> languages;
    private int vatRate;
    private boolean isActive;
    private Timestamp createdTime;
    private String createdBy;
    private Timestamp updatedTime;
    private String updatedBy;
}
