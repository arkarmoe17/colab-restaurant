package com.colab.restaurant.model.dto.req;

import com.colab.restaurant.model.entity.QuotaOrder;
import lombok.Data;

import java.util.List;

@Data
public class PresentQuotaReq {
    private String shopCode;
    private List<QuotaOrder> orders;
}
