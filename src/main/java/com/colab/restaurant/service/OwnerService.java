package com.colab.restaurant.service;

import com.colab.restaurant.model.dto.OwnerDTO;
import com.colab.restaurant.model.entity.Owner;

import java.util.List;

public interface OwnerService {
    void createOwner(Owner owner);
    List<OwnerDTO> findAllByShopCode(String shopCode);
}
