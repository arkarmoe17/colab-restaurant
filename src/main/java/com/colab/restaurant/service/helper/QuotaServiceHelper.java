package com.colab.restaurant.service.helper;

import com.colab.restaurant.model.entity.Quota;
import com.colab.restaurant.model.entity.QuotaOrder;
import com.colab.restaurant.repo.QuotaRepo;
import com.colab.restaurant.utility.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuotaServiceHelper {
    private final QuotaRepo quotaRepo;

    public void updateQuotaValues(String shopCode, List<QuotaOrder> orders) {
        // check shopCode exist or not
        Quota quota = quotaRepo.findByShopCode(shopCode).orElse(new Quota());
        quota.setShopCode(shopCode);
        for (QuotaOrder order : orders) {
            log.info("orderType:{} | quantity:{} ", order.getOrderType(), order.getQuantity());
            int quantity = order.getQuantity();
            switch (order.getOrderType()) {
                case Constant.OrderType.TABLE: {
                    quota.setTableNumber(quota.getTableNumber() + quantity);
                    break;
                }
                case Constant.OrderType.CATEGORIES: {
                    quota.setCategoryNumber(quota.getCategoryNumber() + quantity);
                    break;
                }
                case Constant.OrderType.ADMIN_ACC: {
                    quota.setAdminAccNumber(quota.getAdminAccNumber() + quantity);
                    break;
                }
                case Constant.OrderType.STAFF_ACC: {
                    quota.setStaffAccNumber(quota.getStaffAccNumber() + quantity);
                    break;
                }
                case Constant.OrderType.SLIP_NO: {
                    quota.setSlipNumber(quota.getSlipNumber() + quantity);
                    break;
                }
            }
        }
        // update quota
        quotaRepo.save(quota);
        log.info("Quota:{}", quota);
    }
}
