package com.colab.restaurant.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("owners")
@Data
@Builder
public class Owner {
    @Id
    private String id;
    private String shopCode; // ref of restaurant
    private String name;
    private String email;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dob;
    private String phoneNumber;
    private String citizen;
    private String address;
    private String imgUrl;

}
