package com.colab.restaurant.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerDTO {
    private String shopCode;
    private String name;
    private String email;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dob;
    private String phoneNumber;
    private String citizen;
    private String address;
    private String imgUrl;
}
