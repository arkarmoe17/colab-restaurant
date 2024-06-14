package com.colab.restaurant.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDTO {
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

}
