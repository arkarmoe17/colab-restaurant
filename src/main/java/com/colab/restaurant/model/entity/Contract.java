package com.colab.restaurant.model.entity;

import com.colab.restaurant.model.enums.ContractStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("contracts")
@Data
public class Contract {
    private String id;
    private String shopCode;
    private String contractFile;
    private Date contractDate;
    private String contractPeriod; // 6M, 12M, ...
    private Date serviceStartDate;
    private Date serviceStopDate;
    private ContractStatus status;
}
