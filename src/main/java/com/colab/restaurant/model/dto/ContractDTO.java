package com.colab.restaurant.model.dto;

import com.colab.restaurant.model.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ContractDTO {
    private String id;
    private String shopCode;
    private String contractFile;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date contractDate;
    private String contractPeriod;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date serviceStartDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date serviceStopDate;
    private ContractStatus status;
}
