package com.colab.restaurant.service;

import com.colab.restaurant.model.dto.DiningTableDTO;
import com.colab.restaurant.model.entity.DiningTable;

import java.util.List;

public interface DiningTableService {
    List<DiningTableDTO> fetchingDiningTables(String shopCode, Boolean isActive);

    DiningTableDTO createDiningTable(DiningTable diningTable);
    DiningTableDTO updateDiningTable(DiningTable diningTable);
}
