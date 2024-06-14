package com.colab.restaurant.service;

import com.colab.restaurant.model.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> fetchAllRestaurants();

    List<String> fetchAllActiveShopCodes();

    void registerRestaurant(RestaurantDTO requestDTO);

    RestaurantDTO updateRestaurant(String id, RestaurantDTO requestDTO);
}
